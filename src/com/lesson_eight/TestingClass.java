package com.lesson_eight;

import com.lesson_eight.mytest.AfterSuite;
import com.lesson_eight.mytest.BeforeSuite;
import com.lesson_eight.mytest.Test;

public class TestingClass {
    @Test(priority = 3)
    public void method1() {
        System.out.println("Call m1 with priority 3");
    }
    @Test(priority = 1)
    public void method2() {
        System.out.println("Call m2 with priority `1`");
    }
    @Test(priority = 4)
    public void method3() {
        System.out.println("Call m3 with priority 4");
    }
    @Test(priority = 2)
    private void method4() {
        System.out.println("Call m4 with priority 2");
    }
    @AfterSuite
    public void methodAfter() {
        System.out.println("Call m_after");
    }
    @BeforeSuite
    public void methodBefore() {
        System.out.println("Call m_before");
    }

}
