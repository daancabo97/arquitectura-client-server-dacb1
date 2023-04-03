package com.arquitectura.net.server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import com.arquitectura.net.AbstractNetRunnable;

public abstract class ServerRunnable extends AbstractNetRunnable {

	private ServerSocket serverSocket;
	private int port;

	public ServerRunnable(int port) { 
		this.port = port;
	}

	@Override
    public void start() {
        try { 
            serverSocket = new ServerSocket(port);
            System.out.println("Servidor iniciado. Puerto: " + port);
        } catch (BindException e) {
            System.err.println("Error: el puerto " + port + " ya está en uso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
	public void process() {
	    try {
	        if (serverSocket != null) {
	            Socket client = serverSocket.accept();
	            System.out.println("Cliente aceptado: " + client.toString());
	            connectionAccepted(client);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }   
	}


	
	@Override
	public void stop() {
	    super.stop();
	    try {
	        serverSocket.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    System.out.println("Servidor detenido");
	}


	public abstract void connectionAccepted(Socket client);

}