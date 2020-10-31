package com.xchaset.algorithm.leetcode.simple;

import java.util.HashSet;
import java.util.Set;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = new String[]{"Comparator","Comparat","Comparator"};
        String longestCommonPrefix = new LongestCommonPrefix().longestCommonPrefix(strs);
        System.out.println(longestCommonPrefix);
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int minLength = strs[0].length();
        for (String str : strs) {
            int length = str.length();
            if (length < minLength){
                minLength = length;
            }
        }
        String result = "";
        for (int i = 0; i < minLength; i++) {
            Set<Character> stringSet = new HashSet<>();
            for (String str : strs) {
                char c = str.charAt(i);
                stringSet.add(c);
            }
            if (stringSet.size() == 1){
                result = result + stringSet.iterator().next();
            }
            if (stringSet.size() > 1){
                return result;
            }
        }

        return result;
    }
}
