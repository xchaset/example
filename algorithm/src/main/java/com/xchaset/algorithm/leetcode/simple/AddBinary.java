package com.xchaset.algorithm.leetcode.simple;

/**
 * 67. 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * <p>
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * <p>
 * 提示：
 * <p>
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddBinary {
    public static void main(String[] args) {
        System.out.println(new AddBinary().addBinary("1", "11"));
        System.out.println(new AddBinary().addBinary("1010", "1011"));
    }

    // 11111
    // 01111
//    输入: a = "10101",
//          b = "11011"
    public String addBinary(String a, String b) {
        String shortOne;
        String longerOne;

        if (a.length() < b.length()) {
            shortOne = a;
            longerOne = b;
        }else {
            shortOne = b;
            longerOne = a;
        }
        int abs = Math.abs(longerOne.length() - shortOne.length());
        String shortOnePrefix = "";
        for (int i = 0; i < abs; i++) {
            shortOnePrefix = shortOnePrefix + "0";
        }
        shortOne = shortOnePrefix + shortOne;
        StringBuilder sb = new StringBuilder();
        int temp = 0;
        for (int i = longerOne.length() - 1; i >= 0; i--) {
            char l = longerOne.charAt(i);
            char s = shortOne.charAt(i);
            Integer shortInt = Integer.valueOf(String.valueOf(l));
            Integer longerInt = Integer.valueOf(String.valueOf(s));
            if (shortInt + longerInt + temp == 0) {
                sb = sb.append("0");
                temp = 0;
            }else if (shortInt + longerInt + temp== 1){
                sb.append("1");
                temp = 0;
            }else if (shortInt + longerInt+ temp == 2){
                sb.append("0");
                temp = 1;
            }else if (shortInt + longerInt+ temp > 2){
                sb.append("1");
                temp = 1;
            }

        }
        if (temp > 0) {
            sb.append("1");
        }
        return sb.reverse().toString();

    }
}
