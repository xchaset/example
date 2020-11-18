package com.xchaset.algorithm.leetcode.simple;

import java.util.Arrays;

/**
 * 66. 加一
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 * <p>
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 * <p>
 * <p>
 * [7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,0,6]
 */
public class PlusOne {

    public static void main(String[] args) {
        for (int i : new PlusOne().plusOne(new int[]{0,0})) {
            System.out.print(",");
            System.out.print(i);
        }
    }

    public int[] plusOne(int[] digits) {
        int previousPlus = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] + previousPlus > 9) {
                digits[i] = 0;
                previousPlus = 1;
            } else {
                digits[i] = digits[i] + previousPlus;
                return digits;
            }
        }
        int[] ints = copy(digits);
        ints[0] = 1;
        return ints;

    }

    private int[] copy(int[] digits) {
        int[] ints = new int[digits.length + 1];
        for (int i = digits.length - 1; i >= 0; i--) {
            ints[i] = digits[i];
        }
        return ints;
    }
}
