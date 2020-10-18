package com.lesson_six;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

public class SomeClass {
    private static Logger logger = Logger.getLogger(SomeClass.class.getName());

    public static int[] getLastAfterFour(int[] inArray) {
        logger.info(Arrays.toString(inArray));
        int pos = checkFour(inArray);
        if(pos == -1) {
            throw new RuntimeException("Array haven't 4");
        }
        logger.info("POS: " + pos + "inArray length" + inArray.length);
        logger.info("New Array Size: " + (inArray.length-pos-1));

        if(pos == inArray.length-1) {
            return null;
        }

        int[] outArray = new int[inArray.length-pos-1];

       for (int i = 0; i< outArray.length; i++) {
           logger.info(String.format("Id= %s ElementVal = %s", i, inArray[i+pos]));
           outArray[i] = inArray[i+pos+1];
       }
        logger.info("outArray" +Arrays.toString(outArray));
        return outArray;
    }



    private static int checkFour(int[] inArray) {
        if(inArray == null) {
            return -1;
        }

        int pos = -1;

        for (int i = inArray.length-1; i>=0; i--) {
            if (inArray[i] ==  4) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public static boolean checkOneFour(int[] inArray) {
        HashSet<Integer> integers = new HashSet<>();
        logger.info("Input array" + Arrays.toString(inArray));
        for (int i = 0 ; i<inArray.length; i++) {
            integers.add(inArray[i]);
        }
        logger.info(String.format("Contains %s is %s; Contains %s is %s", 1, integers.contains(1), 4, integers.contains(4)));
        return (integers.contains(1) && integers.contains(4));
    }



}
