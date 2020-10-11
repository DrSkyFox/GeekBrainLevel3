package com.lesson_five;

import java.util.concurrent.Semaphore;

public class FuelStation implements Refuelable {
    Semaphore  semaphore ;

    public FuelStation(int countRefuelPlace) {
        semaphore = new Semaphore(countRefuelPlace);
    }

    public FuelStation() {
        semaphore = new Semaphore(3);
    }

    @Override
    public boolean getRefueling(Refuelable refuelable) {
        try {
            semaphore.acquire();
            System.out.println("Start refueling: " + refuelable.getName());
            Thread.sleep(6000);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public String getName() {
        return toString();
    }
}
