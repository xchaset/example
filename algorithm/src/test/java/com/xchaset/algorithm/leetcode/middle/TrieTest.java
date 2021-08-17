package com.xchaset.algorithm.leetcode.middle;


import org.junit.jupiter.api.Test;

public class TrieTest {

    @Test
    public void insert() {
        Trie trie = new Trie();
        trie.insert("apple");
        boolean app = trie.search("app");
        System.out.println(app);
        System.out.println(trie.startsWith("appl"));
        System.out.println(trie.startsWith("applw"));
    }

    @Test
    public void search() {
    }

    @Test
    public void startsWith() {
    }
}