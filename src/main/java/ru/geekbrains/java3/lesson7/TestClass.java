package ru.geekbrains.java3.lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class TestClass {
    static void start(Class testClass) {
        List<TestMethod> testMethods = new ArrayList<>();
        Method beforeMethod = null;
        Method afterMethod = null;

        for (Method testMethod : testClass.getDeclaredMethods()) {
            if (testMethod.getAnnotation(Test.class) != null) {
                testMethods.add(new TestMethod(testMethod, testMethod.getAnnotation(Test.class).priority()));
            } else if (testMethod.getAnnotation(BeforeSuite.class) != null) {
                if(beforeMethod == null) {
                    beforeMethod = testMethod;
                } else {
                    throw new RuntimeException("@BeforeSuit annotation must be only one.");
                }
            } else if (testMethod.getAnnotation(AfterSuite.class) != null) {
                if (afterMethod == null) {
                    afterMethod = testMethod;
                } else {
                    throw new RuntimeException("@AfterSuite annotation must be only one.");
                }
            }
        }

        testMethods.sort(Comparator.comparingInt(value -> value.priority));
        try {
            Object instance = testClass.newInstance();
            if (beforeMethod != null) {
                System.out.println("BeforeSuite test :\"" + beforeMethod.getName() + "\".");
                beforeMethod.invoke(instance);
            }
            for (TestMethod testMethod : testMethods) {
                System.out.println("Test method:\"" + testMethod.method.getName() +
                        " with priotorty " + testMethod.priority + "\".");
                testMethod.method.invoke(instance);
            }
            if (afterMethod != null) {
                System.out.println("AfterSuite test :\"" + afterMethod.getName() + "\".");
                afterMethod.invoke(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
