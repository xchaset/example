package com.xchaset.algorithm.leetcode.simple;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 */
public class ValidSymbol {


    public static void main(String[] args) {
        boolean valid = new ValidSymbol().isValid("}");
        System.out.println(valid);
    }

    public boolean isValid(String s) {
        if (s == null || "".equals(s) ) {
            return false;
        }
        Map<String, String> map = new HashMap<>();
        map.put(")", "(");
        map.put("}", "{");
        map.put("]", "[");
        Stack<String> stringStack = new Stack<>();
        String[] split = s.split("");
        for (String s1 : split) {
            if (map.keySet().contains(s1) && stringStack.isEmpty()) {
                return false;
            }
            if (map.values().contains(s1)) {
                stringStack.push(s1);
            } else {
                String pop = stringStack.pop();
                String anotherSymbol = map.get(s1);
                if (!pop.equals(anotherSymbol)) {
                    stringStack.push(pop);
                    stringStack.push(s1);
                }
            }

        }
        return stringStack.isEmpty();
    }


    public boolean isValid2(String s) {
        Stack<Character>stack = new Stack<>();
        for(char c: s.toCharArray()){
            if(c=='(')stack.push(')');
            else if(c=='[')stack.push(']');
            else if(c=='{')stack.push('}');
            else if(stack.isEmpty()||c!=stack.pop())return false;
        }
        return stack.isEmpty();
    }
}
