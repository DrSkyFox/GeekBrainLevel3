package com.lessons_one;

import java.util.Arrays;

public class Main {



    public static void main(String[] args) {
        task3();
    }

    private static void task3() {
        Box<Orange> orangeBox = new Box(Orange.class);
        orangeBox.put(new Orange());
        orangeBox.put(new Orange());
        orangeBox.put(new Orange());
        orangeBox.put(new Orange());

        System.out.println("Box 1 mass: " +  orangeBox.getBoxMass());

        Box<Orange>  orangeBox1 = new Box(Orange.class);
        orangeBox1.put(new Orange());
        orangeBox1.put(new Orange());
        orangeBox1.put(new Orange());

        System.out.println("Box 2 mass: " +  orangeBox1.getBoxMass());

        System.out.println("Compare Boxes: " + orangeBox.compareTo(orangeBox1));

        orangeBox1.put(new Orange());

        System.out.println("Compare Boxes: " + orangeBox.compareTo(orangeBox1));

        Box<Apple> appleBox = new Box(Apple.class);
        appleBox.put(new Apple());
        appleBox.put(new Apple());
        appleBox.put(new Apple());
        appleBox.put(new Apple());

        try {
            orangeBox.transferAllTo(appleBox);
        } catch (DifferentBoxFruitsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Box mass 1 :" + orangeBox.getBoxMass());
        System.out.println("Box mass 3 :" + orangeBox1.getBoxMass());

        orangeBox.transferAllTo(orangeBox1);
        System.out.println("Box mass 1 :" + orangeBox.getBoxMass());
        System.out.println("Box mass 3 :" + orangeBox1.getBoxMass());
    }


}
