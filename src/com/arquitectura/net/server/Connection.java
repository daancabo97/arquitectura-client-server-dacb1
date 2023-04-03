package com.arquitectura.net.server;

import java.io.IOException;
import java.io.ObjectInputStream;
//import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.arquitectura.net.AbstractNetRunnable;

public abstract class Connection extends AbstractNetRunnable {

	protected Socket socket;

	public Connection(Socket socket) {
		this.socket = socket;
	} 
	
	@Override
	public void start() {
		// TODO Auto-generated method stub 

	}

	
	
	@Override
	public void process() {
		try {
			
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			Object obj = in.readObject();

			receiveMessage(obj);

		} catch (IOException e) {
			running = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public abstract void receiveMessage(Object obj);

	public void sendMessage(Object obj) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		super.stop();
		
		try {
			
			if (!socket.isClosed())
				socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}