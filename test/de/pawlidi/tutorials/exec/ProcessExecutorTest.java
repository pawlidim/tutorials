
package de.pawlidi.tutorials.exec;

import java.io.IOException;

import org.apache.commons.exec.ExecuteException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProcessExecutorTest {

	private ProcessExecutor executor;

	@Before
	public void setUp() throws Exception {
		executor = new ProcessExecutor();
	}

	@After
	public void tearDown() throws Exception {
		executor = null;
	}

	/**
	 * Test method for
	 * {@link de.pawlidi.tutorials.exec.ProcessExecutor#executeCommand(java.lang.String, java.lang.String[])}
	 * .
	 * 
	 * @throws IOException
	 * @throws ExecuteException
	 */
	@Test
	public void testExecuteCommand() throws ExecuteException, IOException {
		int returnValue = executor.executeCommand("cmd");
		Assert.assertEquals(returnValue, 0);
	}

	/**
	 * Test method for
	 * {@link de.pawlidi.tutorials.exec.ProcessExecutor#executeCommandWithOutput(java.lang.String, java.lang.String[])}
	 * .
	 * 
	 * @throws IOException
	 * @throws ExecuteException
	 */
	@Test
	public void testExecuteCommandWithOutput() throws ExecuteException, IOException {
		String returnValue = executor.executeCommandWithOutput("cmd");
		Assert.assertNotNull(returnValue);
	}

}
