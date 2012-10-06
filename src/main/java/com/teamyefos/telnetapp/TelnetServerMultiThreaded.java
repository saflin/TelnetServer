package com.teamyefos.telnetapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * I am a telnet server which uses a {@link ExecutorService} to manage concurrent requests
 */
public class TelnetServerMultiThreaded {

	private static final int PORT = 8889;
	private static final int NTHREADS = 25;
	private static final ExecutorService executorService = Executors.newFixedThreadPool(NTHREADS);

	public static void main(String[] args) throws IOException { 
		ServerSocket socket = new ServerSocket(PORT);
		try {
			for(;;) { 
				Runnable listener = new Listener(socket.accept());
				executorService.execute(listener);
			} 
		}catch (IOException ex) {
			executorService.shutdown();
		}
	}
}
