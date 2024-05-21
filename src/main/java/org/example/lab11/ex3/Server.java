package org.example.lab11.ex3;

import java.io.*;
import java.net.*;
import org.example.lab11.ex3.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {

    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server pornit la port 8080");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            LOGGER.error("Eroare", e);
        }
    }
}
