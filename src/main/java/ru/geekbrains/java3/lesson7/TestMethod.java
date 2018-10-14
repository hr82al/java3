package ru.geekbrains.java3.lesson7;

import java.lang.reflect.Method;

public class TestMethod {
    public Method method;
    public int priority;

    public TestMethod(Method method, int priority) {
        this.method = method;
        this.priority = priority;
    }
}
