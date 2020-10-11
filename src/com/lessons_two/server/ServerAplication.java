package com.lessons_two.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerAplication {

    public static void main(String[] args) {

        ExecutorService executorService =Executors.newFixedThreadPool(10);
        new Server(executorService);
    }
}
