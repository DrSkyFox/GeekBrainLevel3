package com.lesson_eight.mytest;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;

public class MyTest {


    public static void start(Class aClass, Object object) {
        /**
         * Check counts After and Before
         */
        checkAfterSuite(aClass);
        checkBeforeSuite(aClass);

        ArrayList<Method> testMethods  = new ArrayList<>();
        Method before = null;
        Method after = null;

        for (Method method: aClass.getDeclaredMethods()) {
            if(method.getAnnotation(AfterSuite.class) != null) {
                after = method;
            }
            if (method.getAnnotation(BeforeSuite.class) !=null) {
                before =method;
            }
            if(method.getAnnotation(Test.class) != null) {
                testMethods.add(method);
            }
        }

        testMethods.sort(Comparator.comparingInt(method -> method.getAnnotation(Test.class).priority()));
        testMethods.add(0, before);
        testMethods.add(after);

        /**
         * Call methods
         */
        for (Method method: testMethods
             ) {
            invokeMethods(method, object);
        }
    }

    private static void invokeMethods(Method method, Object object) {
        if (Modifier.isPrivate(method.getModifiers())) {
            method.setAccessible(true);
            System.out.println(String.format("Method: %s is accessible now", method.getName()));
        }
        try {
            method.invoke(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * Check After
     * @param aClass
     */
    private static void checkAfterSuite(Class aClass) {
        int cAfter = 0;
        for (Method method: aClass.getDeclaredMethods()) {
            if (method.getAnnotation(AfterSuite.class) != null) {
                cAfter++;
            }
        }
        if(cAfter == 0) {
            throw new RuntimeException(String.format("Annotation @AfterSuite not found in claas %s", aClass.getName()));
        }
        if(cAfter > 1) {
            throw new RuntimeException(String.format("More than one @AfterSuite annotation is defined in the %s class", aClass.getName()));
        }
    }

    /**
     * Check Before
     * @param aClass
     */
    private static void checkBeforeSuite(Class aClass) {
        int cBefore = 0;
        for (Method method: aClass.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                cBefore++;
            }
        }
        if(cBefore == 0) {
            throw new RuntimeException(String.format("Annotation @BeforeSuite not found in class %s", aClass.getName()));
        }
        if(cBefore > 1) {
            throw new RuntimeException(String.format("More than one @BeforeSuite annotation is defined in the %s class", aClass.getName()));
        }
    }


}
