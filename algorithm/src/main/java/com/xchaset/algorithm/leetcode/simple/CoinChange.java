package com.xchaset.algorithm.leetcode.simple;

import java.util.Arrays;

public class CoinChange {

    public static void main(String[] args) {
        int i = new CoinChange().coinChange(new int[]{1, 4, 5}, 10);
        System.out.println(i);
    }

    public int coinChange(int [] arr,int amount){
        int max = amount + 1;
        int [] dp = new int[amount + 1];
        Arrays.fill(dp,max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] <= i){
                    dp[i] = Math.min(dp[i] ,dp[i-arr[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1:dp[amount];

    }
}
