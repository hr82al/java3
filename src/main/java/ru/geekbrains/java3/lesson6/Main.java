package ru.geekbrains.java3.lesson6;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static int[] getElements(int[] arr) {
        int i;
        final int MARKER = 4;
        for (i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == MARKER) break;
        }
        if (i < 0) {
            throw new RuntimeException("\"" + MARKER + "\" not found in input array.");
        }
        int arrLenngth = arr.length - i - 1;
        int[] result = new int[arrLenngth];
        System.arraycopy(arr, i + 1, result, 0, arrLenngth);
        return result;
    }
    public static boolean checkArray(int[] arr) {
        final int[] MARKERS = new int[]{1, 4};
        Set<Integer> sArr = Arrays.stream(arr).boxed().collect(Collectors.toSet());
        for (final int MARKER : MARKERS) {
            if (!sArr.contains(MARKER)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = getElements(new int[]{4, 3,2,3,5,2 ,4,1});
        System.out.println(Arrays.toString(arr));
    }
}
