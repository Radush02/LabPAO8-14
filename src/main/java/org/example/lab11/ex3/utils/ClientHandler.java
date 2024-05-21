package org.example.lab11.ex3.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler extends Thread {
    private static final MessageManager messageManager = new MessageManager();
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);

    private final Socket clientSocket;
    User user;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] userInput = inputLine.split(" ", 3);
                switch (userInput[0].toUpperCase()) {
                    case "LOGIN" -> {
                        user = new User(userInput[1]);
                        messageManager.addWriter(user, out);
                        out.println("Logat cu succes.");
                    }
                    case "SUBSCRIBE" -> {
                        messageManager.subscribe(user, userInput[1]);
                        out.println("Abonat la " + userInput[1]);
                    }
                    case "UNSUBSCRIBE" -> {
                        messageManager.unsubscribe(user, userInput[1]);
                        out.println("Dezabonat de la " + userInput[1]);
                    }
                    case "NOTIFY" -> {
                        try {
                            if (userInput.length == 2) {
                                out.println("@everyone " + userInput[1]);
                                messageManager.notify(user, userInput[1]);
                            } else {
                                out.println(
                                        "Mesaj trimis pe canalul "
                                                + userInput[1]
                                                + ": "
                                                + userInput[2]);
                                messageManager.notify(user, userInput[1], userInput[2]);
                            }
                        } catch (Exception e) {
                            out.println(e.getMessage());
                        }
                    }
                }
            }
        } catch (SocketException e) {
            LOGGER.error("Clientul " + user.getName() + " a luat crash");
        } catch (IOException e) {
            LOGGER.error("Eroare", e);
        } finally {
            if (user != null) {
                messageManager.removeWriter(user);
            }
            try {
                clientSocket.close();
            } catch (IOException e) {
                LOGGER.error("Eroare", e);
            }
        }
    }
}
