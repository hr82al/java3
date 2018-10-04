package ru.geekbrains.java3.lesson4;
/**
 * 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
 * Используйте wait/notify/notifyAll.
 * 2. На серверной стороне сетевого чата реализовать управление потоками через ExecutorService.
 */
public class Main {

    private static volatile char[] chars = {'A', 'B', 'C'};
    private static volatile int currentChar = 0;

    public synchronized char getCurrentChar() {
        return chars[currentChar];
    }

    public synchronized void nextChar() {
        if (++currentChar == chars.length) {
            currentChar = 0;
        }
    }

    public static void main(String[] args) {
        parralelThreads();
    }

    private static void parralelThreads() {
        Main waitObject = new Main();
        for (int i = 0; i < chars.length; i++) {
            final int j = i;
            new Thread(() -> {
                waitObject.printChar(chars[j]);
            }).start();
        }
    }

    private synchronized void printChar(char aChar) {
        for (int i = 0; i < 5; i++) {

            while (aChar != getCurrentChar()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(aChar);
            nextChar();
            notifyAll();
        }
    }
}
