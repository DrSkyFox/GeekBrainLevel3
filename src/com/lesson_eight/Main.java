package com.lesson_eight;

import com.lesson_eight.mytest.MyTest;

public class Main {
    public static void main(String[] args) {
        TestingClass testingClass = new TestingClass();
        MyTest.start(testingClass.getClass(), testingClass);
    }
}
