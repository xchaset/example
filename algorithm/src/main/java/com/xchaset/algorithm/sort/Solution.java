package com.xchaset.algorithm.sort;

import ch.qos.logback.core.util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        LocalDateTime yesterdayStart = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MIN);//当天零点
        LocalDateTime yesterdayEnd = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX);//当天零点
        System.out.println(yesterdayEnd);
        System.out.println(yesterdayStart);

    }



}
