package com.xchaset.excelexample.excel.utils;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;

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
}
