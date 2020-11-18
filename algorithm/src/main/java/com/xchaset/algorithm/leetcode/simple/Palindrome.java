package com.xchaset.algorithm.leetcode.simple;

/**
 * 9. 回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 * <p>
 * 你能不将整数转为字符串来解决这个问题吗？
 */
public class Palindrome {

    public static void main(String[] args) {
        boolean palindrome = new Palindrome().isPalindrome2(2345432);
        System.out.println(palindrome);

    }

    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        String[] split = s.split("");

        for (int i = 0; i < split.length/2; i++) {
            String s1 = split[i];
            String s2 = split[split.length - 1 - i];
            if (!s1.equals(s2)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 121
     * 是否回文数字，不使用字符串处理
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
        if (x < 0) return false;
        int help = 1;
        int tmp = x;
        // 通过循环得到一个数和x位数相同，首位为1 的数，比如2332，则help为1000
        // x乘以10，help除以10，结束条件为tmp >= 10
        while (tmp >= 10){
            help *= 10;
            tmp /=10;
        }
        while (x != 0){
            if (x % 10 != x/help){
                return false;
            }
            // 去头去尾
            x = x % help / 10;
            // 去头去尾后除100
            help /= 100;
        }
        return true;
    }
}
