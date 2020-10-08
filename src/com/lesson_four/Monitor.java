package com.lesson_four;

public class Monitor {
    public volatile char ch = 'A';
    private int max_count = 5;

    public Monitor() {
    }

    public Monitor(int max_count) {
        this.max_count = max_count;
    }

    synchronized public void printA() {
        for (int i = 0; i <max_count; i++) {
            while (ch != 'A') {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("%s said A \n", Thread.currentThread().getName());
            ch = 'B';
            this.notifyAll();
        }
    }


    synchronized public void printB() {
        for (int i = 0; i <max_count; i++) {
            while (ch != 'B') {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("%s said B \n", Thread.currentThread().getName());
            ch = 'C';
            this.notifyAll();
        }
    }

    synchronized public void printC() {
        for (int i = 0; i <max_count; i++) {
            while (ch != 'C') {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("%s said C \n", Thread.currentThread().getName());
            ch = 'A';
            this.notifyAll();
        }
    }




}
