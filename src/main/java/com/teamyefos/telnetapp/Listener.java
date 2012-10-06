package com.teamyefos.telnetapp;

import static com.teamyefos.telnetapp.Operation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * I listen to user requests and I use a {@link OperationExecutor} to execute them and present the results.
 */
public class Listener implements Runnable {

    private Socket socket;

    public Listener(Socket s) {
        this.socket = s;
    }

    @Override
    public void run() {
        InputStream inps = null;
        OutputStream outs = null;
        try {
            inps = this.socket.getInputStream();
            outs = this.socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner in = new Scanner(inps);
        PrintWriter out = new PrintWriter(outs, true);
        out.println("Server running...");

        boolean done = false;

        OperationExecutor opExecutor = new OperationExecutor();
        while (!done && in.hasNextLine()) {
            String line = in.nextLine();
            Operation op = new Operation(line);
            if (op.getCommand() == type.EXIT) {
                out.println("[Connection closed]");
                done = true;
            } else {
                out.println(opExecutor.performOperation(op));
            }
        }
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }
}
