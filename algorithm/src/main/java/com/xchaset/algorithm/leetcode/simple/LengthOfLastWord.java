package com.xchaset.algorithm.leetcode.simple;

/**
 * 58. 最后一个单词的长度
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 *
 * 如果不存在最后一个单词，请返回 0 。
 *
 * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
 *
 *
 *
 * 示例:
 *
 * 输入: "Hello World"
 * 输出: 5
 */
public class LengthOfLastWord {
    public static void main(String[] args) {
        LengthOfLastWord lengthOfLastWord = new LengthOfLastWord();
        String a_ = "a bbb";
        int hello_world = lengthOfLastWord.lengthOfLastWord(a_);
        System.out.println(hello_world);
        System.out.println(lengthOfLastWord.lengthOfLastWord2(a_));
    }

    public int lengthOfLastWord(String s) {
        String trim = s.trim();
        int i = trim.lastIndexOf(" ");
        if (i < 0){
            return trim.length();
        }
        return trim.substring(i).length()-1;
    }

    public int lengthOfLastWord2(String s){
        String trim = s.trim();
        for (int length = trim.length(); length > 0; length--) {
            char c = trim.charAt(length - 1);
            if ((String.valueOf(c)).equals(" ")) {
                return trim.length() - length;
            }
        }
        return trim.length();
    }
}
