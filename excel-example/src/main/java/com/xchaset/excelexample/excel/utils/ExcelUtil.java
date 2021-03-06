package com.xchaset.excelexample.excel.utils;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    private ExcelUtil() {
    }

    /**
     * 多个sheet页写入
     *
     * @param filePath      写出文件路径
     * @param dataMap       sheetName:数据集  的Map 集合
     * @param writeHandlers 写入处理器
     */
    public static void writeToExcel(String filePath, Map<String, List> dataMap, List<WriteHandler> writeHandlers) {
        if (!FileUtil.exist(filePath)) {
            FileUtil.mkParentDirs(filePath);
        }
        ExcelWriterBuilder writerBuilder = EasyExcel.write(filePath);
        writerBuilder.excelType(ExcelTypeEnum.XLSX);
        for (WriteHandler writeHandler : writeHandlers) {
            writerBuilder.registerWriteHandler(writeHandler);
        }
        ExcelWriter writer = writerBuilder.build();

        for (Map.Entry<String, List> entry : dataMap.entrySet()) {
            String sheetName = entry.getKey();
            List rows = entry.getValue();
            WriteSheet writeSheet = new WriteSheet();
            writeSheet.setNeedHead(true);
            writeSheet.setClazz(rows.get(0).getClass());
            writeSheet.setSheetName(sheetName);
            writer.write(rows, writeSheet);
        }
        writer.finish();
    }


    /**
     * poi 透视表的创建
     * @param filePath 原始数据文件路径
     * @param outFilePath 输出文件路径
     */
    public static void createPivotTable(String filePath,String outFilePath){
        File file = new File(filePath);
        // 创建XSSFWorkbook
        XSSFWorkbook workbook;
        try (FileInputStream fileInputStream = new FileInputStream(file);){
             workbook = XSSFWorkbookFactory.createWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // 获取透视表数据源
        XSSFSheet buyerReview = workbook.getSheet("Review");
        // 创建一个新的sheet也。用于存放透视表
        XSSFSheet overallReport = workbook.createSheet("pivot sheet");
        // 获取透视表的最后一行行号
        int lastRowNum = buyerReview.getLastRowNum();
        // 透视表数据源数据区域
        AreaReference area = new AreaReference("A1:K" + lastRowNum, SpreadsheetVersion.EXCEL2007);
        // 透视表显示起始位置
        CellReference position = new CellReference("A2");

        XSSFPivotTable pivotTable = overallReport.createPivotTable(area, position, buyerReview);
        // 添加行标签
        pivotTable.addRowLabel(0);
        pivotTable.addRowLabel(8);

        // 添加列标签,行标签会横向展示
        pivotTable.addColLabel(10);
        // 添加行数据统计函数
        pivotTable.addColumnLabel(DataConsolidateFunction.COUNT,10,"FieldName");

        // 写出到文件
        try(FileOutputStream fileOutputStream = new FileOutputStream(new File(outFilePath))){
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
