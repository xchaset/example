package com.xchaset.algorithm.leetcode.simple;

import java.util.HashMap;
import java.util.Map;
/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 通过次数131,453提交次数191,782
 */

public class BuildTree {

    Map<Integer, Integer> map;

    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] preOrder, int[] middleOrder) {
        int length = preOrder.length;
        map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            map.put(middleOrder[i], i);
        }
        return myBuildTree(preOrder, 0, length - 1, 0, length - 1);

    }

    //    前序遍历 preorder = [3,9,20,15,7]
//    中序遍历 inorder = [9,3,15,20,7]
    private TreeNode myBuildTree(int[] preOrder, int preOrderLeft, int preOrderRight, int middleOrderLeft, int middleOrderRight) {
        if (preOrderLeft > preOrderRight) {
            return null;
        }
        int preOrderRoot = preOrderLeft;
        int middleOrderRoot = map.get(preOrder[preOrderRoot]);
        TreeNode rootNode = new TreeNode(preOrder[preOrderRoot]);
        int leftSubTreeSize = middleOrderRoot - middleOrderLeft;
        rootNode.left = myBuildTree(preOrder, preOrderLeft + 1, preOrderLeft + leftSubTreeSize, middleOrderLeft, middleOrderRoot - 1);
        rootNode.right = myBuildTree(preOrder, preOrderLeft + leftSubTreeSize + 1, preOrderRight, middleOrderRoot + 1, middleOrderRight);

        return rootNode;
    }

}
