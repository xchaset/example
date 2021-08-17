package com.xchaset.algorithm.leetcode.simple;

import org.apache.tomcat.util.buf.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class TwentySix {

    private static String [] arr = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

    public static void main(String[] args) {
//        String index = getIndex(676);
        for (int i = 0; i < 18279; i++) {
            System.out.print("i="+i+" ");
            System.out.println(getIndex(i).toUpperCase());
        }
    }

    // 0 - A
    // 1 - B
    // 25 -Z
    // 26 -AA  26/26 = 1 26%26 = 0
    // 701-ZZ  701/26=27 701%26=0
    // 702-AAA  702/26 = 27  27/27=1  27%27=0   702%26 = 0
    // 18278 -AAAA  18278/26=703 703/28=25  18278%26=0
    public static String getIndex(int index){
        int temp = index;
        List<Integer> list = new ArrayList<>();
        while (temp >= 26){
            int mod = temp % 26;
            list.add(mod);
            temp = temp / 26;
        }
        list.add(temp);
        List<String> join = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i==0) {
                join.add(arr[list.get(i)]);

            }else {
                join.add(arr[list.get(i)-1]);
            }
        }
        Collections.reverse(join);
        String collect = join.stream().collect(Collectors.joining());
        return collect;
    }
}
