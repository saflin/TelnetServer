package com.teamyefos.telnetapp;

import java.io.File;
import java.io.IOException;

/**
 * I am a implementation of {@link FileSystemManipulator} 
 * I perform an action based on the {@link Operation} given to me
 * 
 */
public class OperationExecutor implements FileSystemManipulator {
	private File currentDir; 
	
	public OperationExecutor () {
		currentDir = this.createTmpFolder();
	}
	
	public String performOperation(Operation operation) {
		if(operation.getCommand() == Operation.type.LS) {
			return this.listFiles();
		} else if(operation.getCommand() == Operation.type.PWD) {
			return this.getWorkingDirectory();
		}else if(operation.getCommand() == Operation.type.MKDIR) {
			return this.createDir(operation.getArgs());
		}else if(operation.getCommand() == Operation.type.CD) {
			return this.changeDir(operation.getArgs());
		} else if(operation.getCommand() == Operation.type.UNKNOWN) {
			return operation.getError();
		}
		return null;
	}

	@Override
	public String listFiles() {
		StringBuilder files = new StringBuilder("");		
		for(File f : new File(this.getPath()).listFiles()) {
			files.append(f.getName() + "\n");
		}
		return files.toString();
	}
	
	@Override
	public String getPath() {
		try {
			return this.currentDir.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
			return this.currentDir.getAbsolutePath();
		}
	}
	
	@Override
	public String createDir(String dirName) {
		try{
			File theDir = new File(this.getPath(),dirName);
			if (theDir.exists()) {
				return "Directory " + theDir + "already exists";
			} 
			return theDir.mkdir() == true ? "Directory: " + dirName + " created" : 
											"Imposible to create " + dirName + "!"; 
		}catch (Exception e){
			return ("Error: " + e.getMessage());
		}
	}
	
	@Override
	public String getWorkingDirectory() {
		return this.getPath();
	}

	@Override
	public String changeDir(String dirName) {
		File changeDir = new File (this.getPath(), dirName); 
		if (changeDir.exists()) {
			this.currentDir = changeDir;
			return "Changed to " + dirName;
		} else {
			return "Directory " + dirName + " does not exist";
		}
	}
	
	private File createTmpFolder() {
		File tempDir = new File("tmp");
		if (!tempDir.exists()) {
			System.out.println("creating  temp directory: " + tempDir);
			tempDir.mkdir();
		}
		return tempDir;
	}
}
