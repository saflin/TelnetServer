package com.cisco.telnetapp;

import com.teamyefos.telnetapp.Operation;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * I test the {@link Operation} class.
 */
public class OperationTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public OperationTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(OperationTest.class);
    }


    public void testEmptyOperation() {
        Operation op = new Operation("");
        assertNull(op.getArgs());
        assertEquals(Operation.type.UNKNOWN, op.getCommand());
    }


    public void testLsOperation() {
        Operation op = new Operation("ls");
        assertNull(op.getArgs());
        assertEquals(Operation.type.LS, op.getCommand());
    }


    public void testLsWithTooManyParameter() {
        Operation op = new Operation("ls -la");
        assertNull(op.getArgs());
        assertEquals(Operation.type.UNKNOWN, op.getCommand());
        assertEquals("too many arguments for ls command", op.getError());
    }


    public void testMkdirOperation() {
        Operation op = new Operation("mkdir directory1");
        assertEquals(Operation.type.MKDIR, op.getCommand());
        assertEquals("directory1", op.getArgs());
    }


    public void testMkdirWithTooManyParameter() {
        Operation op = new Operation("mkdir directory1 directory2");
        assertNull(op.getArgs());
        assertEquals(Operation.type.UNKNOWN, op.getCommand());
        assertEquals("too many arguments for mkdir command, this version of the server only allows one directory creation per command", op.getError());
    }


    public void testPwdOperation() {
        Operation op = new Operation("pwd");
        assertEquals(Operation.type.PWD, op.getCommand());
        assertNull(op.getArgs());
    }


    public void testPwdWithTooManyParameter() {
        Operation op = new Operation("pwd something");
        assertNull(op.getArgs());
        assertEquals(Operation.type.PWD, op.getCommand());
        assertEquals("too many arguments for pwd command", op.getError());
    }

    public void testCdOperation() {
        Operation op = new Operation("cd directory");
        assertEquals(Operation.type.CD, op.getCommand());
        assertEquals("directory", op.getArgs());
    }


    public void testCdWithTooManyParameters() {
        Operation op = new Operation("cd directory1 directory2");
        assertNull(op.getArgs());
        assertEquals(Operation.type.UNKNOWN, op.getCommand());
        assertEquals("too many arguments for cd command", op.getError());
    }

    public void testCdWithInsuficientParameters() {
        Operation op = new Operation("cd");
        assertNull(op.getArgs());
        assertEquals(Operation.type.UNKNOWN, op.getCommand());
        assertEquals("insuficient number of args for cd command", op.getError());
    }
}
