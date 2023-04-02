package com.arquitectura.net;

public interface NetRunnable extends Runnable{	
	boolean isRunning();
	
	void start();	
	void process();	
	void stop();
}
