package ru.geekbrains.java3.lesson1;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.jetbrains.annotations.NotNull;
import org.omg.CORBA.ORB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Задача 1
        String[] arrayStrings = new String[]{"Меркурий", "Уран", "Венера", "Юпитер", "Нептун"};
        Character[] arrayCahracters = new Character[]{'h', 'e', 'l', 'l', 'o'};
        Double[] arrayDoubles = new Double[]{3.14, 2.71};

        System.out.printf("Задача 1. \n");
        checkSwap(arrayStrings, 0, 2);
        checkSwap(arrayCahracters, 1, 4);
        checkSwap(arrayDoubles, 0, 1);

        // Задача 2
        System.out.println("Задача 2. \n");
        System.out.println(arrayToArrayList(arrayStrings));
        System.out.println(arrayToArrayList(arrayCahracters));
        System.out.println(arrayToArrayList(arrayDoubles));

        //Задача 3
        System.out.println("");
        Box<Orange> box1 = new Box<Orange>();
        for (int i = 0; i < 10; i++) {
            box1.add(new Orange());
        }
        System.out.println(box1);
        System.out.println("Вес коробке 1: " + box1.getWeight());
        Box<Apple> box2 = new Box<>();
        for (int i = 0; i < 15; i++) {
            box2.add(new Apple());
        }
        System.out.println(box2);
        System.out.println("Вес коробке 2: " + box2.getWeight());
        System.out.println("Сравниваем коробки: " + box1.compare(box2));
        Box<Apple> box3 = new Box<>();
        box2.move(box3);
        System.out.println("После того как яблоки пересыпали из коробки 2 в коробку 3");
        System.out.println(box2);
        System.out.println(box3);

    }

    private static <T>void checkSwap(@NotNull final T[] array, final int element_1, final int element_2) {
        if (element_1 > array.length || element_2 > array.length) return;
        System.out.println("Меняем местами элементы массива типа: " + array[0].getClass().getName());
        System.out.println("Массив до замены:");
        System.out.println(Arrays.toString(array));
        swap(array, element_1, element_2);
        System.out.println("После замены:");
        System.out.println(Arrays.toString(array));
        System.out.println("");
    }

    private static <T> void swap(@NotNull final T[] array, final int element_1, final int element_2) {
        if (element_1 > array.length || element_2 > array.length) return;
        T temp = array[element_1];
        array[element_1] = array[element_2];
        array[element_2] = temp;
    }

    public static <T> ArrayList<T> arrayToArrayList(T[] a) {
        return new ArrayList<T>(Arrays.asList(a));
    }
}
