package com.teamyefos.telnetapp;

/**
 * I define operations which can be performed in a file system.
 *
 */
public interface FileSystemManipulator {
	
	public String getPath();
	public String listFiles();
	public String getWorkingDirectory();
	public String createDir(String dirName);
	public String changeDir(String dirName);
	

}
