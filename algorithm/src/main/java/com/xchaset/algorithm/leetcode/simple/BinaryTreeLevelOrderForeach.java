package com.xchaset.algorithm.leetcode.simple;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * <p>
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class BinaryTreeLevelOrderForeach {

    public static void main(String[] args) {
        BinaryTreeLevelOrderForeach instance = new BinaryTreeLevelOrderForeach();
    }

    // 层序遍历，广度优先BFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        List<List<Integer>> lists = new ArrayList<>();
        Queue<TreeNode> nodeQueue = new LinkedBlockingQueue<>();
        nodeQueue.offer(root);
        while (nodeQueue.size() > 0) {
            int size = nodeQueue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode1 = nodeQueue.poll();
                level.add(treeNode1.val);
                if (treeNode1.left != null) {
                    nodeQueue.offer(treeNode1.left);
                }
                if (treeNode1.right != null) {
                    nodeQueue.offer(treeNode1.right);
                }
            }
            lists.add(level);
        }
        return lists;
    }


    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(level);
        }
        return res;

    }

}
