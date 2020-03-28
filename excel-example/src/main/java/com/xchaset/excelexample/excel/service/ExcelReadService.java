package com.xchaset.excelexample.excel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.xchaset.excelexample.excel.model.CustomerInfoModel;

import java.util.List;

public class ExcelReadService {

    public static void main(String[] args) {
        new ExcelReadService().readExcel();
    }

    public void readExcel(){
        String pathName = "C:\\Users\\xiaozhaoming\\Documents\\customer.XLS";
        CustomerReadListener readListener = new CustomerReadListener();
        ExcelReaderBuilder read = EasyExcel.read(pathName, CustomerInfoModel.class, readListener);
        ExcelReader build = read.excelType(ExcelTypeEnum.XLS).build();
        build.readAll();
        List rows = readListener.getRows();
    }
}
