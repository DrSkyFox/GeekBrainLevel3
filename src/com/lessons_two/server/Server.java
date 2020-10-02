package com.lessons_two.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private Set<ClientHandler> clientHandlers;
    private AuthenticationService authenticationService;
    private ExecutorService executorService = Executors.newFixedThreadPool(3);

    public Server() {
        this.clientHandlers = new HashSet<>();
        this.authenticationService = new AuthenticationService();
        start(8888);
    }

    public Set<ClientHandler> getClientHandlers() {
        return clientHandlers;
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    private void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            listenClients(serverSocket);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during server start-up");
        }
    }

    private void listenClients(ServerSocket serverSocket) throws IOException {
        while (true) {
            System.out.println("Server is looking for a client...");
            Socket client = serverSocket.accept();
            System.out.println("Client accepted: " + client);
            new ClientHandler(client, this, executorService);
        }
    }

    public synchronized void broadcast(ClientHandler clientHandler, String incomingMessage) {
        for (ClientHandler ch : clientHandlers) {
            if (!ch.equals(clientHandler)) {
                ch.sendMessage(String.format("%s said : %s", clientHandler.getName(), incomingMessage));
            }
        }
    }

    public synchronized void broadcast(String incomingMessage) {
        for (ClientHandler ch : clientHandlers) {
            ch.sendMessage(incomingMessage);
        }
    }


    public synchronized void broadcast(ClientHandler clientHandler, String nameClient, String incomingMessage) {
        for (ClientHandler ch: clientHandlers
             ) {
            if(ch.getName().equals(nameClient)) {
                ch.sendMessage(String.format("%s send u msg: %s", clientHandler.getName(), incomingMessage));
                break;
            }
        }
    }

    public synchronized void changeName(ClientHandler clientHandler, String nameClient) {
        clientHandler.changeName(nameClient);
    }

    public synchronized void subscribe(ClientHandler client) {
        clientHandlers.add(client);
    }

    public synchronized void unsubscribe(ClientHandler client) {
        clientHandlers.remove(client);
    }

    public boolean checkLogin(String name) {
        for (ClientHandler ch : clientHandlers) {
            if (ch.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}