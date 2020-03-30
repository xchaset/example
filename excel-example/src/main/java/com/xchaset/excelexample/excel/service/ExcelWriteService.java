package com.xchaset.excelexample.excel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.xchaset.excelexample.excel.model.SimpleCustomerModel;

import java.util.List;

public class ExcelWriteService {

    public static void main(String[] args) {
        new ExcelWriteService().writeExcel();
    }

    public void writeExcel(){

        // 读取的excel路径，此处主要是为获取数据源。
        String readPathName = "C:\\Users\\xiaozhaoming\\Documents\\customer.XLS";
        List rows = new ExcelReadService().readExcel(readPathName);

        // 写入的文件路径
        String writePathName = "C:\\Users\\xiaozhaoming\\Documents\\write.xlsx";

        ExcelWriterBuilder writerBuilder = EasyExcel.write(writePathName, SimpleCustomerModel.class);
        writerBuilder.excelType(ExcelTypeEnum.XLSX);
        // 注册写入处理器
        writerBuilder.registerWriteHandler(new CellCommentWriteHandler());
        writerBuilder.registerWriteHandler(new CellStyleWriteHandler());


        ExcelWriter writer = writerBuilder.build();
        WriteSheet writeSheet = new WriteSheet();
        writeSheet.setSheetName("客户信息");
        writer.write(rows, writeSheet);

        //可以写入多个sheet页
        WriteSheet writeSheet2 = new WriteSheet();
        writeSheet2.setSheetName("客户信息2");
        writer.write(rows,writeSheet2);

        // finish才会写入到文件中
        writer.finish();

    }


}
