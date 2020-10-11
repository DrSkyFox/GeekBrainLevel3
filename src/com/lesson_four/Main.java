package com.lesson_four;

public class Main {


    public static void main(String[] args) {
        Monitor monitor =new Monitor();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 is " + Thread.currentThread().getName() + " started");
                monitor.printA();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 is " +Thread.currentThread().getName() + " started");
                monitor.printB();
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t3 is " +Thread.currentThread().getName() + " started");
                monitor.printC();
            }
        });


        t1.start();
        t2.start();
        t3.start();




    }


}
