package com.lessons_three;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Chat implements ChatInterface {
    Logger logger = Logger.getLogger(this.getClass().getName());
    private String chatName;
    private DataOutputStream out;
    private DataInputStream in;
    private List<String> messages;
    private FileOutputStream fileOutputStream;
    private Scanner scanner;


    public Chat(String chatName) {
        this.chatName = chatName;
        ConnectionSet connectionSet = new ConnectionSet("localhost", 8888);
        out = connectionSet.getDataOutputStream();
        in = connectionSet.getDataInputStream();

        init();
    }

    private void init() {
        messages = new ArrayList<>();
        scanner = new Scanner(System.in);
        readFileMsg(100);
        System.out.println(messages.toString());
        try {
            fileOutputStream = new FileOutputStream("test.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        threadReadMsg();
        threadSendMsg();
    }

    private void readFileMsg(int count_lines) {
        StringBuilder builder = new StringBuilder();
        char ch;
        int readLines = 0;
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("test.txt", "r")) {
            long len = randomAccessFile.length() - 1;

            for (long p = len; p >= 0; p--) {
                randomAccessFile.seek(p);
                ch = (char) randomAccessFile.read();
                if (ch == '\n') {
                    readLines++;
                    if (readLines == count_lines) {
                        break;
                    }
                }
                builder.append(ch);
            }
            builder.reverse();

            messages = List.of(builder.toString().split("\n"));
        } catch (FileNotFoundException e) {
            try {
                new File("test.txt").createNewFile();
            } catch (IOException ioException) {
                logger.info(e.getMessage());
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void sendMessage(String msg) {
        messages.add(msg);
    }

    private void threadReadMsg() {
        Thread threadReadStream = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        readMsgFromStream(in.readUTF());
                    } catch (IOException e) {
                        readMsgFromStream("Stream is out: " + e.getMessage());
                    }
                }
            }
        });
        threadReadStream.start();
    }

    private void threadSendMsg() {
        Thread threadSendStream = new Thread(new Runnable() {
            @Override
            public void run() {
                String str = null;
                while (true) {
                    try {
                        str = scanner.nextLine();
                        out.writeUTF(str);
                        readMsgFromStream(str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadSendStream.start();
    }

    synchronized private void readMsgFromStream(String msg) {
        messages.add(msg);
        try {
            fileOutputStream.write((msg+"\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(msg);
    }


    @Override
    public void changeNickName(String newName) {

    }

    @Override
    public void initChat() {

    }
}
