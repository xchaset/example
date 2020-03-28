package com.xchaset.excelexample.excel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.xchaset.excelexample.excel.model.CustomerInfoModel;
import com.xchaset.excelexample.excel.model.SimpleCustomerModel;

import java.util.List;

public class ExcelWriteService {

    public static void main(String[] args) {
        new ExcelWriteService().writeExcel();
    }

    public void writeExcel(){

        String pathName = "C:\\Users\\xiaozhaoming\\Documents\\customer.XLS";
        CustomerReadListener readListener = new CustomerReadListener();
        ExcelReaderBuilder read = EasyExcel.read(pathName, CustomerInfoModel.class, readListener);
        ExcelReader build = read.excelType(ExcelTypeEnum.XLS).build();
        build.readAll();
        List rows = readListener.getRows();

        String writePathName = "C:\\Users\\xiaozhaoming\\Documents\\documents5.xlsx";

        ExcelWriterBuilder writerBuilder = EasyExcel.write(writePathName, SimpleCustomerModel.class);
        writerBuilder.excelType(ExcelTypeEnum.XLSX);
        writerBuilder.registerWriteHandler(new CellCommentWriteHandler());
        writerBuilder.registerWriteHandler(new CellStyleWriteHandler());
        ExcelWriter writer = writerBuilder.build();
        WriteSheet writeSheet = new WriteSheet();
        writeSheet.setSheetName("客户信息");
        writer.write(rows, writeSheet);
        writer.finish();

    }
}
