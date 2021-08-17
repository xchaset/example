package com.xchaset.algorithm.leetcode.simple;

/**
 * 二分搜索法
 */
public class BinarySearchSolution {

    public static void main(String[] args) {
        int [] arr = new int[]{1,3,5,7,9,11,20};
        BinarySearchSolution solution = new BinarySearchSolution();
        int i = solution.indexOf(arr, 22);
        System.out.println(i);
    }

    public int indexOf(int [] arr,int target){
        int length = arr.length;
        int low = 0;
        int high = length -1;
        while (low <= high){
            int middle = (low + high) >>> 1;
            if (arr[middle] > target) {
                high = middle - 1;
            }else if (arr[middle] < target){
                low = middle + 1;
            }else {
                return middle;
            }
        }
        return -1;
    }
}
