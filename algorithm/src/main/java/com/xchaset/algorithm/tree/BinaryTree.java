package com.xchaset.algorithm.tree;

public class BinaryTree {

    private TreeNode root;


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

    private class TreeNode{
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
