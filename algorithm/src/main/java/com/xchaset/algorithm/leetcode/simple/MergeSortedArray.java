package com.xchaset.algorithm.leetcode.simple;
//merge-sorted-array
//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
//
//
//
// 说明：
//
//
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
//
//
//
//
// 示例：
//
//
//输入：
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//输出：[1,2,2,3,5,6]
//
//
//
// 提示：
//
//
// -10^9 <= nums1[i], nums2[i] <= 10^9
// nums1.length == m + n
// nums2.length == n
//
// Related Topics 数组 双指针
// 👍 684 👎 0

public class MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 7, 8, 0, 0, 0};
        int[] nums2 = new int[]{1,2,3};
//        int [] nums1 = new int[]{1,2,3,8,0,0,0};
//        int [] nums2 = new int[]{-3,5,6}; 7
        new MergeSortedArray().merge2(nums1, 3, nums2, 3);
        for (int sou : nums1) {
            System.out.print(sou);
        }
        System.out.println();
        for (int sou : nums2) {
            System.out.print(sou);
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            }else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
        System.out.println();
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        while (p1 >= 0 && p2 >= 0 ) {
            if (nums1[p1] > nums2[p2]){
                nums1[p] = nums1[p1];
                p1--;
            }else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
        for (int t = 0; t < p2 + 1; t++) {
            nums1[t] = nums2[t];
        }
    }
}
