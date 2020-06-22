package com.xchaset.algorithm.sort;

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {-57,55,79,49,-10,80,82,-20,58,76,-25,-30,1,1,12,-73,-24,38,50,6,56,91,-48,56,-14,6,27,-26,0,76,4,-5,42,-71,-65,-64,16,-25,-12,-28,18,-1,26,-75,-45,91,76,51,23,-10,24,2,34,-78,-12,-23,-28,35,67,-3,-21,-15,-51,-60,19,-5,-25,-20,52,-63,1,-58,-50,42,8,-64,3,17,-25,75,26,-49,60,40,18,95};

        System.out.print("排序前:  ");
        print(arr);

        sort(arr);

        System.out.print("排序后:  ");
        print(arr);
    }



    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        // 原地构造堆
        buildHeap(arr);
        int len = arr.length;
        while (len > 1) {
            // 构造好堆后，交换第一个元素和最后一个元素
            swap(arr, 0, len - 1);
            // 数组减一
            len--;
            // 重新构造堆
            heapfy(arr, 0, len);

        }
    }


    private static void buildHeap(int[] arr) {
        // 第i个节点的左右节点分别为2i+1,2i+2,父节点为(i-1)/2
        // 最后一个非叶子节点开始调整。最后一个节点的索引位置为arr.length -1
        // 则最后一个非叶子节点的索引为 (arr.length-1 -1)/2
        for (int i = (arr.length - 2) / 2; i >= 0; i--) {
            heapfy(arr, i, arr.length);
        }

    }

    private static void heapfy(int[] arr, int i, int length) {
        while (true) {
            int maxPosition = i;
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;
            if (leftChild < length && arr[leftChild] > arr[maxPosition]) {
                maxPosition = leftChild;
            }

            if (rightChild < length && arr[rightChild] > arr[maxPosition]) {
                maxPosition = rightChild;
            }

            if (maxPosition == i) {
                break;
            } else {
                swap(arr, i, maxPosition);
                i = maxPosition;
            }
        }
    }


    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 打印数组
    private static void print(int[] arr) {
        if (arr == null) return;

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
