package com.xchaset.excelexample.excel.service;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.style.AbstractCellStyleStrategy;
import org.apache.poi.ss.usermodel.*;

/**
 * 单元格样式写入处理器
 */
public class CellStyleWriteHandler extends AbstractCellStyleStrategy {
    @Override
    protected void initCellStyle(Workbook workbook) {
    }

    @Override
    protected void setHeadCellStyle(Cell cell, Head head, Integer relativeRowIndex) {
        Workbook workbook = cell.getSheet().getWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());
        cell.setCellStyle(cellStyle);
    }


    @Override
    protected void setContentCellStyle(Cell cell, Head head, Integer relativeRowIndex) {
        Workbook workbook = cell.getSheet().getWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        if ("*".equals(cell.getStringCellValue())) {
            //隐藏行
            cell.getRow().setZeroHeight(true);
        }
        cell.setCellStyle(cellStyle);

    }
}
