package com.arquitectura.net.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Obtener los flujos de entrada y salida del socket
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            // Leer los mensajes del cliente y enviarles una respuesta
            while (true) {
                String message = input.readLine();
                if (message == null) {
                    break;
                }
                System.out.println("Mensaje recibido del cliente: " + message);
                output.println("Respuesta del servidor: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cerrar los flujos y el socket
            try {
                input.close();
                output.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}