package ru.geekbrains.java3.lesson5;

import sun.security.krb5.internal.ccache.CCacheInputStream;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Организуем гонки:
 * Все участники должны стартовать одновременно, несмотря на то, что на подготовку у каждого их них уходит разное время.
 * В тоннель не может заехать одновременно больше половины участников (условность).
 * Попробуйте все это синхронизировать.
 * Только после того, как все завершат гонку, нужно выдать объявление об окончании.
 * Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты классов из пакета util.concurrent.
 */
public class MainClass {
    public static final int CARS_COUNT = 4;
    public static final Semaphore smp = new Semaphore(CARS_COUNT / 2);
    public static final CyclicBarrier cb = new CyclicBarrier(CARS_COUNT);
    public static final CountDownLatch cl = new CountDownLatch(CARS_COUNT);
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        try {
            cl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
