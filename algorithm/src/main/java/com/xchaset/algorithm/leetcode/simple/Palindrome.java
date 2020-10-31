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
        boolean palindrome = new Palindrome().isPalindrome(0);
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
}
