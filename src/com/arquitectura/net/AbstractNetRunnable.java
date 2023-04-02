package com.arquitectura.net;

public abstract class AbstractNetRunnable implements NetRunnable {
	private boolean running = false;

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public void run() {
		start();

		running = true;

		while (running) {
			process();
		}

		running = false;
	}

	@Override
	public void stop() {
		running = false;

	}

}
