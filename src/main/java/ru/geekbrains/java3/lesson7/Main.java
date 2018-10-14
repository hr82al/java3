package ru.geekbrains.java3.lesson7;

public class Main {

    public static void main(String[] args) {
        TestClass.start(Main.class);
    }

    @Test
    public void method1(){
        System.out.println("I'm method 1.");
    }

    @Test(priority = 5)
    public void method2(){
        System.out.println("I'm method 2.");
    }

    @BeforeSuite
    public void before() {
        System.out.println("I'm before method.");
    }

    @AfterSuite
    public void after() {
        System.out.println("I'm after method.");
    }

/*    @BeforeSuite
    public void excessBefore() {
        System.out.println("I'm excessive before method");
    }

    @AfterSuite
    public void excessAfter(){
        System.out.println("I'm excessive after method");
    }*/
}
