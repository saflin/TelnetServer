TelnetServer
=====

Notes to run the app:
 * java jar target/telnetapp-1.0-SNAPSHOT.jar
 * in a separate terminal run: telnet localhost 8889

Implemented commands:
 * ls -- list directories and files, it doesn't accept any parameters.
 * pwd -- shows the working directory
 * mkdir directory -- creates a directory, only accepts one directory
 * cd directory -- changes to the specified directory
 * exit -- closes the connection 