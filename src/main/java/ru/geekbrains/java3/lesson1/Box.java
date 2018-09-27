package ru.geekbrains.java3.lesson1;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    List<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public void add(T fruit) {
        fruits.add(fruit);
    }


    public float getWeight() {
        if (fruits.size() != 0) {
            float weigt = 0;
            for (T fruit : fruits) {
                weigt += fruit.getWeight();
            }
            return weigt;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        String str;
        if (fruits.size() != 0) {
            str = "В коробке " +
                    "содержится " + fruits.size() + " " + fruits.get(0).getClass().getSimpleName() + '.';
        } else {
            str = "Коробка пустая.";
        }
        return str;
    }

    public boolean compare(Box<?> box2) {
        return (this.getWeight() - box2.getWeight()) < 0.0001;
    }

    public void move(Box<T> box3) {
        box3.fruits = this.fruits;
        this.fruits = new ArrayList<>();
    }
}
