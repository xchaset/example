package com.xchaset.algorithm.leetcode.simple;

/**
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class StrStr {

    public static void main(String[] args) {
        int i = new StrStr().strStr("abc", "c");
        int c = "abc".indexOf("c");
        System.out.println(c);
        System.out.println(i);
    }

    // abcde  cde
    // 01234  234
    public int strStr(String haystack, String needle) {
        if (null == needle || "".equals(needle) || haystack.equals(needle)) {
            return 0;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            String substring = haystack.substring(i, i + needle.length());
            if (substring.equals(needle)) return i;
        }
        return -1;
    }
}
