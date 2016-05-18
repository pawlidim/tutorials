package de.pawlidi.tutorials.exec;

import org.apache.commons.exec.LogOutputStream;

public class ProcessStringOutput extends LogOutputStream {

	private StringBuilder processOutput;

	public ProcessStringOutput() {
		super();
		this.processOutput = new StringBuilder();
	}

	@Override
	protected void processLine(String line, int logLevel) {
		processOutput.append(line);
		processOutput.append("\n");
	}

	public String getOutput() {
		return processOutput.toString();
	}
}
