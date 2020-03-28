package com.xchaset.excelexample.excel.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CellCommentEnum {

    CUSTOMER_CODE_COMMENT(1,0,"客户代码"),
    CUSTOMER_NAME_COMMENT(1,1,"客户名称"),
    ADDRESS_COMMENT(1,5,"工作单位地址"),
    LIVING_ADDRESS_COMMENT(1,16,"住宅地址");


    private int rowIndex;

    private int columnIndex;

    private String cellCommentStr;

}
