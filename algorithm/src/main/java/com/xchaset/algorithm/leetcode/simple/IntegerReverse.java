package com.xchaset.algorithm.leetcode.simple;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IntegerReverse {

    public static void main(String[] args) {
        int reverse = new IntegerReverse().reverse(1534236469);
        System.out.println(reverse);
    }

    public int reverse(int x) {
        if (x== 0) {
            return 0;
        }
        String string = String.valueOf(x);
        StringBuilder append = new StringBuilder();
        boolean startsWith = string.startsWith("-");
        if (startsWith) {
            append.append("-");
        }
        String s = string.replaceAll("-", "");
        String[] split = s.split("");
        for (int i = split.length - 1; i >= 0; i--) {
            String str = split[i];
            if (!(str.equals("0") && (append.toString().equals("") || append.toString().equals("-")))) {
                append.append(str);
            }
        }
        Integer integer;
        try{
            integer = Integer.valueOf(append.toString());

        }catch (Exception e){
            return 0;
        }
        return integer;
    }
}
