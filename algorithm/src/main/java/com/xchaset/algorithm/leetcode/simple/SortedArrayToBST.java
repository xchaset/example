package com.xchaset.algorithm.leetcode.simple;

import com.xchaset.algorithm.leetcode.simple.DeleteDuplicates.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 108. 将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定有序数组: [-10,-3,0,5,9],
 * <p>
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 */
public class SortedArrayToBST {


    public static void main(String[] args) {
        SortedArrayToBST bst = new SortedArrayToBST();
        int[] arr = new int[]{-10, -3, 0, 5, 9};
        TreeNode treeNode = bst.sortedArrayToBST(arr);

        ListNode listNode = new ListNode(-10,new ListNode(-3,new ListNode(0,new ListNode(5,new ListNode(9)))));
        int[] ints = bst.list2Array(listNode);
        for (int anInt : ints) {
            System.out.println(anInt);
        }


    }

    public TreeNode sortedArrayToBST(int[] nums) {
        int length = nums.length;
        return buildSorted(nums,0,length -1 );
    }

    private TreeNode buildSorted(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        int middle = (low + high) >>> 1;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = buildSorted(nums,low,middle-1);
        root.right = buildSorted(nums,middle + 1,high);
        return root;
    }

    public TreeNode sortedListToBST(ListNode head) {
        int [] arr = list2Array(head);
        return buildBST(arr,0,arr.length - 1);
    }

    public int [] list2Array(ListNode head){
        if (head == null){
            return new int[0];
        }
        List<Integer> list = new ArrayList<>();
        list.add(head.val);
        ListNode temp = head;
        while (temp.next != null){
            list.add(temp.next.val);
            temp = temp.next;
        }
        int [] arr = new int[list.size()];
        for(int i=0;i< list.size();i++){
            arr[i] = list.get(i);
        }
        return arr;
    }

    public TreeNode buildBST(int [] nums,int low,int high){
        if(low > high){
            return null;
        }
        int middle = (low + high) >>> 1;
        int rootvalue = nums[middle];
        TreeNode root = new TreeNode(rootvalue);
        root.left = buildBST(nums,low,middle -1 );
        root.right = buildBST(nums,middle + 1,high);
        return root;
    }
}
