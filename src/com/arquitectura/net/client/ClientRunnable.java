package com.arquitectura.net.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.arquitectura.net.AbstractNetRunnable;

public class ClientRunnable extends AbstractNetRunnable{

	private String address;
	private int port;
	private Socket socket;
	
	public ClientRunnable(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	@Override
	public void start() {
		try {
				socket = new Socket(address, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		// TODO Auto-generated method stub
		
	

	@Override
	public void process() {
		try {
				InputStream in = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void stop() {
		super.stop();
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
