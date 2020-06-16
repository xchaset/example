package com.xchaset.algorithm.tree;

public class QuickSort {

    public static void quickSort(int[] arr, int low, int high) {
        int pivot, i, j, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        pivot = arr[low];
        while (i < j) {
            while (pivot <= arr[j] && i < j) {
                j--;
            }
            while (pivot >= arr[i] && i < j) {
                i++;
            }
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        arr[low] = arr[i];
        arr[i] = pivot;
        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);
    }

    public static void main(String[] args) {
        int [] arr= {4, 5, 7, 2, 4, 88, 9};
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
