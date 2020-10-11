package com.lessons_three;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class Chat implements ChatInterface {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private ConnectionSet connectionSet;
    private String chatName;
    private DataOutputStream out;
    private DataInputStream in;
    private Vector<String>  messages;
    private FileOutputStream fileOutputStream;
    private Scanner scanner;

    public Chat(String chatName) {
        this.chatName = chatName;
        connectionSet = new ConnectionSet("localhost", 8888);
        out = connectionSet.getDataOutputStream();
        in = connectionSet.getDataInputStream();

        init();
    }

    private void init() {
        messages = new Vector<>();
        scanner = new Scanner(System.in);
        readFileMsg(100);
        System.out.println(messages.toString());
        try {
            fileOutputStream = new FileOutputStream("test.txt",true);
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
            messages.addAll(List.of(builder.toString().split("\n")));
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
                String str = null;
                while (true) {
                    try {
                        str = in.readUTF();
                        readMsgFromStream(in.readUTF());
                        if (str.contains("exit")) {
                            break;
                        }
                    } catch (IOException e) {
                        System.out.println("Stream is out: " + e.getMessage());
                        break;
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
                        if(str.equals("exit")) {
                            connectionSet.close();
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
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

    private void close() {
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void changeNickName(String newName) {

    }

    @Override
    public void initChat() {

    }
}
