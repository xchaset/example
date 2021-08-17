package com.xchaset.algorithm.leetcode.simple;

import com.xchaset.algorithm.leetcode.simple.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 113. 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class PathSumSecond {


    List<List<Integer>> list = new ArrayList<>();
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root,sum);
        return list;
    }

    private void dfs(TreeNode treeNode, int sum) {
        if (treeNode == null) {
            return;
        }
        path.offerLast(treeNode.val);
        sum = sum - treeNode.val;
        if (treeNode.right == null && treeNode.left == null && sum == 0){
            list.add(new ArrayList<>(path));
        }
        dfs(treeNode.left,sum);
        dfs(treeNode.right,sum);
        path.pollLast();
    }

}
