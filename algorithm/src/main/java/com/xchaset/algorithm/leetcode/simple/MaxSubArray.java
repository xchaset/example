package com.xchaset.algorithm.leetcode.simple;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class MaxSubArray {

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        int i = maxSubArray.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(i);
        System.out.println(maxSubArray.maxSubArray(new int[]{-2,1}));
        System.out.println(maxSubArray.maxSubArray(new int[]{-2}));
        System.out.println(maxSubArray.maxSubArray(new int[]{-1,0,-2}));
    }


    public int maxSubArray2(int[] nums) {
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            max = Math.max(max,sum);
            for (int j = i+1; j < nums.length; j++) {
                sum += nums[j];
                max = Math.max(sum,max);
            }
            max = Math.max(sum,max);
        }
        return max;
    }

    // -2,1,-3,4,-1,2,1,-5,4
    public int maxSubArray(int[] nums){
        int max = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            }else {
                sum = num;
            }
            max = Math.max(sum,max);
        }
        return max;
    }
}
