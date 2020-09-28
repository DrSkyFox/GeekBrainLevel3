package com.lessons_one;

import java.util.ArrayList;

public final class MyArrays {
    private MyArrays() {
    }

    public static  <T> void sweep(T[] t, int i, int j) {

        if(i >t.length && j >t.length) {
            return;
        }

        T t1 = t[j];
        t[j] = t[i];
        t[i] = t1;
    }


    public static  <T> ArrayList<T> toArrayList(T[] t) {
        if(t == null) {
            return null;
        }

        ArrayList<T> arrayList = new ArrayList<>();

        for (int i = 0; i < t.length; i++) {
            arrayList.add(t[i]);
        }

        return arrayList;
    }


}
