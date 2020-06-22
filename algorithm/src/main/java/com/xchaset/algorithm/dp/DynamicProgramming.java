package com.xchaset.algorithm.dp;

public class DynamicProgramming {

    /**
     * 求数组中不相邻数字的和最大值
     */
    public static void main(String[] args) {
        int[] arr = {3, 4, 8, 1, 5, 8};
        int sum = recursiveOpt(arr, arr.length - 1);
        System.out.println(sum);
        int sum2 = dpOpt(arr);
        System.out.println(sum2);
    }

    /**
     * 递归写法
     * @param arr
     * @param len
     * @return
     */
    private static int recursiveOpt(int[] arr, int len) {
        if (len == 0) {
            return arr[0];
        } else if (len == 1) {
            return Math.max(arr[0], arr[1]);
        } else {
            int a = recursiveOpt(arr, len - 2) + arr[len];
            int b = recursiveOpt(arr, len - 1);
            return Math.max(a,b);
        }
    }

    private static int dpOpt(int [] arr){
        int [] dp = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0],arr[1]);
        for (int i = 2; i < dp.length; i++) {
            int a = dp[i-2] + arr[i];
            int b = dp[i-1];
            dp[i] = Math.max(a,b);
        }
        return dp[arr.length - 1];
    }
}
