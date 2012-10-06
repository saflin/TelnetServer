package com.teamyefos.telnetapp;


/**
 * I represent an Operation which can be executed.
 *
 */
public class Operation {
	
	private static final String TOO_MANY_ARGS_ERROR = "too many arguments for %s command";
	private static final String INSUFICIENT_NUMBER_OF_ARGS_ERROR = "insuficient number of args for %s command";
	private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown Command";
	
	private static final String LS_WORD = "ls";
	private static final String MKDIR_WORD = "mkdir";
	private static final String PWD_WORD = "pwd";
	private static final String CD_WORD = "cd";
	private static final String EXIT_WORD = "exit";
	
	public enum type {
	    LS, MKDIR, PWD, CD, EXIT, UNKNOWN
	}
	
	private String args;
	private type command;
	private String error;
	
	public Operation(String line) {
		this.clasifyOperation(line);
	}

	
	private void clasifyOperation (String operationLine) {
		String[] splitedLine = operationLine.split("\\s+");
		if (splitedLine[0].equalsIgnoreCase(EXIT_WORD)) {
			this.setCommand(type.EXIT);
		} else if (splitedLine[0].equalsIgnoreCase(LS_WORD)) {
			if(splitedLine.length == 1) {
				this.setCommand(type.LS);
			} else {
				this.setUnknownCommand(String.format(TOO_MANY_ARGS_ERROR, LS_WORD));
			}
		} else if (splitedLine[0].equalsIgnoreCase(MKDIR_WORD)) {
			if(splitedLine.length == 2) {
				this.setCommand(type.MKDIR);
				this.args = splitedLine[1];
			} else {
				this.setUnknownCommand(String.format(TOO_MANY_ARGS_ERROR + ", this version of the server only allows one directory creation per command", MKDIR_WORD));
			}
		} else if (splitedLine[0].equalsIgnoreCase(PWD_WORD)) {
			if(splitedLine.length == 1) {
				this.setCommand(type.PWD);
			} else {
				this.setUnknownCommand(String.format(TOO_MANY_ARGS_ERROR, PWD_WORD));
			}
			this.setCommand(type.PWD);
		} else if (splitedLine[0].equalsIgnoreCase(CD_WORD)) {
			if(splitedLine.length == 2) {
				this.setCommand(type.CD);
				this.args = splitedLine[1];
			} else if(splitedLine.length < 2){
				this.setUnknownCommand(String.format(INSUFICIENT_NUMBER_OF_ARGS_ERROR, CD_WORD));
			} else if (splitedLine.length > 2) {
				this.setUnknownCommand(String.format(TOO_MANY_ARGS_ERROR, CD_WORD));
			}  
		} else {
			this.setUnknownCommand(UNKNOWN_COMMAND_MESSAGE);
		}
	}

	private void setUnknownCommand (String error){
		this.command = type.UNKNOWN;
		this.args = null;
		this.error = error;
	}
	
	public void setArgs(String args) {
		this.args = args;
	}

	public String getArgs() {
		return args;
	}

	public void setCommand(type command) {
		this.command = command;
	}

	public type getCommand() {
		return command;
	}


	public void setError(String error) {
		this.error = error;
	}


	public String getError() {
		return error;
	}
}
