package com.xchaset.algorithm.leetcode.simple;

public class MySqrt {

    public static void main(String[] args) {
//        int i = new MySqrt().mySqrt(2147395600);
//        System.out.println(i);
//        int i1 = new MySqrt().mySqrt1(101);
//        System.out.println(i1);       //2147395600
//        int i2 = 46343;
//        System.out.println(i2*i2);
        System.out.println(new MySqrt().mySqrt2(2147395600));
    }


    public int mySqrt(int x) {
        return ((int) Math.sqrt(x));
    }

    // 暴力解法
    public int mySqrt1(int x) {
        if (x == 0) {
            return 0;
        }
        int previous = 1;
        for (int i = 1; i < x; i++) {
            if (i*i > x|| i*i < 0) { // int溢出会为负数
                return previous;
            }else if (i*i == x ){
                return i;
            }else {
                previous = i;
            }
        }
        return 1;
    }

    // 二分查找
    public int mySqrt2(int x){
        long left = 0;
        long right = x/2 + 1;
        while(left < right){
            long mid =  left + (right - left + 1) / 2;
            long l = mid * mid;
            if (l > x){
                right = mid - 1;
            }else {
                left = mid;
            }
        }
        return (int) left;
    }

}
