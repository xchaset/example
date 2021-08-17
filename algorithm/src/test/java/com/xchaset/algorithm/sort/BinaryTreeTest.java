package com.xchaset.algorithm.sort;

public class BinaryTreeTest {

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.add(133);
        bt.add(6);
        bt.add(3);
        bt.add(8);
        bt.add(2);
        bt.add(5);
        bt.add(1);
        bt.add(7);
        bt.add(9);
        bt.add(19);
        bt.add(29);
        bt.add(39);

//        bt.print();
        int treeHeight = bt.getTreeHeight(bt.getRoot());
//        System.out.println(treeHeight);
//        System.out.println(bt.getMax(bt.getRoot()));
        bt.getRoot().prePrintNode();
//        bt.getRoot().printNode();
    }
}
