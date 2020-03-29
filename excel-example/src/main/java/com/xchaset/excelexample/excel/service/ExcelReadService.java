package com.xchaset.excelexample.excel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.xchaset.excelexample.excel.model.CustomerInfoModel;

import java.util.List;

public class ExcelReadService {


    public List readExcel(String pathName){
        // 读取的监听器
        CustomerReadListener readListener = new CustomerReadListener();
        ExcelReaderBuilder readerBuilder = EasyExcel.read(pathName, CustomerInfoModel.class, readListener);
        ExcelReader reader = readerBuilder.excelType(ExcelTypeEnum.XLS).build();
        reader.readAll();
        // 返回的数据
        return readListener.getRows();
    }
}
