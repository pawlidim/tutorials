package de.pawlidi.tutorials.exec;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;

/**
 * Process executor provide method to execute external processes.
 * 
 * @author PAWLIDIM
 *
 */
public class ProcessExecutor {

	private Executor executor;

	/** Default constructor to construct new process executer object. */
	public ProcessExecutor() {
		super();
		executor = new DefaultExecutor();
	}

	/**
	 * Execute given command with arguments.
	 * 
	 * @param command
	 *            to execute
	 * @param args
	 *            command arguments can be null
	 * @return process return value, -1 for fail
	 * 
	 * @throws ExecuteException
	 * @throws IOException
	 */
	public int executeCommand(final String command, String... args) throws ExecuteException, IOException {
		int exitValue = -1;

		// create command line without any arguments
		final CommandLine commandLine = new CommandLine(command);

		if (args != null && args.length != 0) {
			// add command arguments
			commandLine.addArguments(args);
		}

		// create process watchdog with timeout 60000 milliseconds
		ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);

		// set watchdog and starting synchronous child process
		executor.setWatchdog(watchdog);

		exitValue = executor.execute(commandLine);
		return exitValue;
	}

	/**
	 * Execute given command with arguments.
	 * 
	 * @param command
	 *            to execute
	 * @param args
	 *            command arguments can be null
	 * @return process return string output, null otherwise
	 * 
	 * @throws ExecuteException
	 * @throws IOException
	 */
	public String executeCommandWithOutput(final String command, String... args) throws ExecuteException, IOException {
		int exitValue = -1;

		// create command line without any arguments
		final CommandLine commandLine = new CommandLine(command);

		if (args != null && args.length != 0) {
			// add command arguments
			commandLine.addArguments(args);
		}

		// create process watchdog with timeout 60000 milliseconds
		ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);

		// set watchdog and starting synchronous child process
		executor.setWatchdog(watchdog);

		// create string output for executor and add as pump handler
		ProcessStringOutput processOutput = new ProcessStringOutput();
		executor.setStreamHandler(new PumpStreamHandler(processOutput, processOutput));

		exitValue = executor.execute(commandLine);

		if (!executor.isFailure(exitValue)) {
			return processOutput.getOutput();
		}
		return null;
	}

}
