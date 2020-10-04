package com.lessons_three;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class ConnectionSet {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public ConnectionSet(Socket socket) {
        this.socket = socket;
    }

    public ConnectionSet(String host, int port) {
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            logger.info("Cant open connect");
            logger.info(e.getMessage());
        }
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            logger.info("Cant get in/out stream");
            logger.info(e.getMessage());
        }
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public Socket getSocket() {
        return socket;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
