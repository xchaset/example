package com.xchaset.algorithm.leetcode.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 *
 *  * 中序遍历 inorder = [1,9,4,3,15,20,7]
 *  * 后序遍历 postorder = [1,4,9,15,7,20,3]
 *  * 返回如下的二叉树：
 *  *
 *  *      3
 *  *    /   \
 *  *   9    20
 *  *  / \  /  \
 *  * 1  4 15   7
 */
public class BuildTreeFromInOrderPostOrder {

    public static void main(String[] args) {
        int [] inOrder = new int[]{9,3,15,20,7};
        int [] postOrder = new int[]{9,15,7,20,3};
        TreeNode treeNode = new BuildTreeFromInOrderPostOrder().buildTree(inOrder, postOrder);
        inOrderForEach(treeNode);
    }

    public static void inOrderForEach(TreeNode treeNode){
        if (treeNode == null) {
            return;
        }
        inOrderForEach(treeNode.left);
        System.out.println(treeNode.val);
        inOrderForEach(treeNode.right);
    }

    Map<Integer,Integer> integerMap = new HashMap<>();

    public TreeNode buildTree(int [] inorder,int [] postorder){
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            integerMap.put(inorder[i],i);
        }
        return myBuildTree(postorder,0,n-1,0,n-1);
    }

    /**
     *  *  * 中序遍历 inorder = [1,9,4,3,15,20,7]
     *  *  * 后序遍历 postorder = [1,4,9,15,7,20,3]
     *  *  * 返回如下的二叉树：
     *  *  *
     *  *  *      3
     *  *  *    /   \
     *  *  *   9    20
     *  *  *  / \  /  \
     *  *  * 1  4 15   7
     */
    private TreeNode myBuildTree(int[] postOrder, int is, int ie, int ps, int pe) {
        if (is > ie  || ps > pe) {
            return null;
        }
        // 根节点
        int rootVal = postOrder[pe];
        int rootIndex = integerMap.get(rootVal);
        int leftSubTreeSize = rootIndex - is;
        TreeNode treeNode = new TreeNode(rootVal);
        treeNode.left = myBuildTree(postOrder,is,rootIndex -1,ps,ps+leftSubTreeSize-1);
        treeNode.right = myBuildTree(postOrder,rootIndex + 1,ie,ps + leftSubTreeSize,pe-1);
        return treeNode;
    }
}
