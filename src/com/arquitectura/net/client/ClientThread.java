package com.arquitectura.net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Obtener los flujos de entrada y salida del socket
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            // Leer los mensajes del usuario y enviarlos al servidor
            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String userInput = userIn.readLine();
                output.println(userInput);
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