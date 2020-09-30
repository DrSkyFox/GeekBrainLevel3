package com.lessons_one;


import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class Box<T extends Fruit> implements Comparable<Box>, BoxInterface<T> {
    Logger logger = Logger.getLogger(this.getClass().getName());
    private float boxMass;
    private Set<T> fruitSet = new HashSet<>();
    private final Class<T> type;

    public Box(Class<T> type) {
        this.type = type;
    }

    public Box(Set<T> fruitSet, Class<T> type) {
        this.fruitSet = fruitSet;
        for (Fruit fruit: fruitSet
             ) {
            boxMass = boxMass + fruit.getMass();
        }
        this.type = type;
    }

    public float getBoxMass() {
        return boxMass;
    }

    public void setBoxMass(float boxMass) {
        this.boxMass = boxMass;
    }




    public void transferAllTo(Box b) {
        logger.info("transferAllTo: " + String.valueOf(type !=b.type));
        if(type !=b.type) {
            throw  new DifferentBoxFruitsException("Different class type");
        }
        b.getFruitSet().addAll(this.fruitSet);
        b.setBoxMass(b.getBoxMass() + this.boxMass);
        this.fruitSet.clear();
        boxMass = 0f;
    }

    public Set<T> getFruitSet() {
        return fruitSet;
    }

    public void setFruitSet(Set<T> fruitSet) {
        this.fruitSet = fruitSet;
    }

    private Object getType() {
        T type = (T) new Fruit();
        System.out.println(type.getClass().getName());
        return type;
    }

    @Override
    public void put(T t) {
        fruitSet.add(t);
        boxMass = boxMass + t.getMass();
    }


    @Override
    public int compareTo(Box box) {
        if(this.boxMass == box.getBoxMass()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
