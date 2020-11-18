package com.xchaset.algorithm.leetcode.simple;

//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
//
// 注意：给定 n 是一个正整数。
//
// 示例 1：
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶
//
// 示例 2：
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
//
// Related Topics 动态规划
// 👍 1337 👎 0

import org.springframework.util.StopWatch;

//climbing-stairs	题目标记	示例:two-sum
//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
//
// 注意：给定 n 是一个正整数。
//
// 示例 1：
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶
//
// 示例 2：
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
//
// Related Topics 动态规划
// 👍 1337 👎 0
//	题目描述
public class ClimbStairs {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("climb1");
        int i = new ClimbStairs().climbStairs5(44);
        System.out.println(i);

        stopWatch.stop();
        stopWatch.start("climb2");
        int i1 = new ClimbStairs().climbStairs2(44);
        stopWatch.stop();
        System.out.println(i1);

        stopWatch.start("dp");
        System.out.println(new ClimbStairs().climbStairs3(44));
        stopWatch.stop();


        stopWatch.start("dp2");
        System.out.println(new ClimbStairs().climbStairs4(44));
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());



    }

    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public int climbStairs2(int n) {
        int[] m = new int[n + 1];
        return climbStairsWithMem(n, m);
    }

    public int climbStairsWithMem(int n, int[] mem) {
        if (mem[n] > 0) return mem[n];
        if (n == 1) {
            mem[n] = 1;
        }else if (n == 2){
            mem[n] = 2;
        }else {
            mem[n] = climbStairsWithMem(n - 1, mem) + climbStairsWithMem(n - 2, mem);
        }

        return mem[n];

    }


    public int climbStairs3(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int [] dp = new int[n+1];
        dp[1] =1;
        dp[2] =2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public int climbStairs4(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int first = 1;
        int second = 2;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

    public int climbStairs5(int n) {
        int first = 0;
        int second = 0;
        int result = 1;
        for (int i = 1; i <= n; i++) {
            first = second;
            second = result;
            result = first + second;
        }
        return result;
    }
}
