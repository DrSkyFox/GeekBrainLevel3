package com.lesson_five;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RageRoad {
    public static void main(String[] args) {
        FuelStation fuelStation = new FuelStation();
        Runnable[] runnables = {
                new Bus(fuelStation),
                new Bus(fuelStation),
                new Bus(fuelStation),
                new Bus(fuelStation),
                new Car(fuelStation),
                new Car(fuelStation),
                new Car(fuelStation),
                new Car(fuelStation),
                new Track(fuelStation),
                new Track(fuelStation),
        };
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (Runnable run: runnables
             ) {
            executorService.submit(run);
        }

        executorService.shutdown();
    }
}
