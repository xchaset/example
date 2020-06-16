package com.xchaset.algorithm.tree;

import java.util.Map;
import java.util.TreeMap;

public class BubbleSort {

    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j+1] = temp;
                }

            }

        }
    }

    public static void main(String[] args) {
        Map tree = new TreeMap<>();
        int []array = new int[]{34,345,3,344};
        new BubbleSort().sort(array);

        for (int i : array) {
            System.out.println(i);
        }
    }
}
