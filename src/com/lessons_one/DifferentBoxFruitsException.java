package com.lessons_one;

public class DifferentBoxFruitsException extends RuntimeException{
    private static String msg = "Boxes have different friuts";

    public DifferentBoxFruitsException() {
    }

    public DifferentBoxFruitsException(Throwable cause) {
        super(cause);
    }

    public DifferentBoxFruitsException(String message) {
        super(message);
    }

    public DifferentBoxFruitsException(String message, Throwable cause) {
        super(message, cause);
    }



    public static String getMsg() {
        return msg;
    }
}
