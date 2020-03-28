package com.xchaset.excelexample.excel.service;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.xchaset.excelexample.excel.constant.CellCommentEnum;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.util.List;

/**
 * 批注写入处理器
 */
public class CellCommentWriteHandler implements CellWriteHandler {

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        addCellComment(cell);

    }

    /**
     * 添加批注信息
     * @param cell
     */
    private void addCellComment(Cell cell) {
        for (CellCommentEnum commentEnum : CellCommentEnum.values()) {
            int rowIndex = commentEnum.getRowIndex();
            int columnIndex = commentEnum.getColumnIndex();
            if (rowIndex == cell.getRowIndex() && columnIndex == cell.getColumnIndex()){
                Drawing<?> drawing = cell.getSheet().createDrawingPatriarch();
                Comment cellComment = drawing.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, columnIndex, 1, columnIndex + 2, 6));
                cellComment.setString(new XSSFRichTextString(commentEnum.getCellCommentStr()));
                cell.setCellComment(cellComment);
            }
        }
    }
}
