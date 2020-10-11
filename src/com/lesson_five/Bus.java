package com.lesson_five;

public class Bus implements Runnable, Refuelable{

    private float curVolume = 40.0f;
    private float ratePerTick = 7.5f;
    private float maxVolume = 40.f;
    private static int count = 0;
    private String name;
    private FuelStation fuelStation;

    private boolean isActive = true;

    public Bus() {
        count++;
        name = "Bus #" + count;
    }

    public Bus(FuelStation fuelStation) {
        count++;
        this.fuelStation = fuelStation;
        name = "Bus #" + count;
    }

    public Bus(String name, FuelStation fuelStation) {
        count++;
        this.name = name;
        this.fuelStation = fuelStation;
    }

    public Bus(String name) {
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
                System.out.println(getName() + "dont hava a fuel going to fuel station");
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
