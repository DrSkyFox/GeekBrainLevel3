package com.lesson_five;

public class Track implements Runnable, Refuelable{

    private float curVolume = 60.0f;
    private float ratePerTick = 15.0f;
    private float maxVolume = 60.f;
    private static int count = 0;
    private String name;
    private FuelStation fuelStation;

    private boolean isActive = true;

    public Track() {
        count++;
        name = "Track #" + count;
    }

    public Track(FuelStation fuelStation) {
        count++;
        this.fuelStation = fuelStation;
        name = "Track #" + count;
    }

    public Track(String name, FuelStation fuelStation) {
        count++;
        this.name = name;
        this.fuelStation = fuelStation;
    }

    public Track(String name) {
        count++;
        this.name = name;
    }

    @Override
    public void run() {
        doDrive();
    }

    private void doDrive() {
        int max_circle = 3;
        int curCircle = 0;
        System.out.println("Start drive " + getName());
        while(isActive) {
            try {
                System.out.println(getName() + " fuel level" + curVolume);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(curVolume - ratePerTick < 0) {
                System.out.println(getName() + " dont have a fuel going to fuel station");
                getRefueling(fuelStation);
                curCircle++;
                if(curCircle == max_circle) {
                    isActive = false;
                    break;
                }
                System.out.println("Continue drive");

            }
            curVolume = curVolume - ratePerTick;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean getRefueling(Refuelable refuelable)  {
        if(refuelable.getRefueling(this)) {
            curVolume = maxVolume;
            System.out.println("Full tank");
            return true;
        }
        return false;
    }
}
