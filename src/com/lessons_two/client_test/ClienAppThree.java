package com.lessons_two.client_test;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienAppThree {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF("-auth l1 p2");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    int key = 1;
                    while (key == 1) {
                        try {
                            String message = in.readUTF();
                            System.out.println(message);
                            Thread.sleep(50);
                            if (message.contains("Incorrect credentials")) {
                                out.writeUTF("-auth l3 p3");
                            }
                            if(key == 1) {
                                out.writeUTF("/changeName SetGeteer");
                                key = 0;
                            }
                            out.writeUTF("Fine! Let the rock off begin! Ah ha ha ha ha ha ha!\n" +
                                         "I'm the devil, I love metal!");
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();

            out.writeUTF("-exit");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
