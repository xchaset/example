package com.xchaset.algorithm.sort;

public class BinaryTree {

    private TreeNode root;

    public TreeNode getRoot(){
        return root;
    }

    public void add(int value){
        if (root == null) {
            root = new TreeNode(value);
        }else {
            root.addNode(value);
        }
    }

    public void print(){
        root.printNode();
    }

    public int getMax(TreeNode treeNode){
        if (treeNode == null) {
            return -1;
        }else {
            int leftVal = getMax(treeNode.left);
            int rightVal = getMax(treeNode.right);
            int val = treeNode.value;
            return Math.max(Math.max(leftVal,rightVal),val);
        }
    }

    public int getTreeHeight(TreeNode treeNode){
        if (treeNode == null){
            return 0;
        }
        if (treeNode.left == null && treeNode.right == null) {
            return 0;
        } else {
            int leftHeight = getTreeHeight(treeNode.left);
            int rightHeight = getTreeHeight(treeNode.right);
            return Math.max(leftHeight,rightHeight) + 1;
        }
    }



    public class TreeNode{
        private int value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value){
            this.value = value;
        }

        public void addNode(int value){
            if (value < this.value) {
                if (left == null) {
                    left = new TreeNode(value);
                }else {
                    left.addNode(value);
                }
            }else {
                if (right == null){
                    right = new TreeNode(value);
                }else {
                    right.addNode(value);
                }
            }
        }

        public void printNode(){
            if (this.left != null) {
                this.left.printNode();
            }
            System.out.println(this.value);
            if (this.right != null){
                this.right.printNode();
            }
        }


    }
}
