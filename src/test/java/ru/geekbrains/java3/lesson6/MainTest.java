package ru.geekbrains.java3.lesson6;

import org.junit.Test;
import ru.geekbrains.java3.lesson6.Main;

import static org.junit.Assert.*;

public class MainTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void getElements() {
        assertArrayEquals(Main.getElements(new int[] {1, 2, 3, 4, 5, 6, 7}), new int[] {5, 6, 7});
        assertArrayEquals(Main.getElements(new int[] {4, 2, 3, 5, 6, 7}), new int[] {2, 3, 5, 6, 7});
        assertArrayEquals(Main.getElements(new int[] {4, 2, 3, 5, 6, 4}), new int[] {});
        assertArrayEquals(Main.getElements(new int[] {1, 2, 4, 4, 2, 3, 4, 1, 7}), new int[] {1, 7});
    }

    @Test
    public void checkArray() {
        assertTrue(Main.checkArray(new int[]{1,4,1,4,4,4,4,1,4}));
        assertTrue(Main.checkArray(new int[]{1,4,4,1,4}));
        assertFalse(Main.checkArray(new int[]{4,4,4}));
        assertFalse(Main.checkArray(new int[]{1,1,1,1,1,1,1,1,1}));
    }
}