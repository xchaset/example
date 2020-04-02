package com.xchaset.excelexample.excel.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExcelUtilTest {

    @Test
    public void createPivotTable() {
        String filePath = "C:\\Users\\xiaozhaoming\\Desktop\\origin_data_mircosoft_copy.xlsx";
        String outfilePath = "C:\\Users\\xiaozhaoming\\Desktop\\origin_data_mircosoft_copy_pivot.xlsx";

        ExcelUtil.createPivotTable(filePath,outfilePath);
    }
}