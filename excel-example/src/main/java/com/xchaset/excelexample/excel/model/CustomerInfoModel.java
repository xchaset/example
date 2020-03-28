package com.xchaset.excelexample.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class CustomerInfoModel {

    @ExcelProperty("客户代码")
    private String customerCode;

    @ExcelProperty("客户名称")
    private String name;

    @ExcelProperty("性别")
    private String gender;

    @ExcelProperty("年龄")
    private String age;

    @ExcelProperty("出生日期")
    private String birthday;

    @ExcelProperty("客户地址")
    private String address;

    @ExcelProperty("电话")
    private String tel;

    @ExcelProperty("移动电话")
    private String mobile;

    @ExcelProperty("邮编")
    private String postcode;

    @ExcelProperty("邮箱")
    private String email;

    @ExcelProperty("职业")
    private String occupation;

    @ExcelProperty("职务")
    private String job;

    @ExcelProperty("省区代码")
    private String provinceCode;

    @ExcelProperty("省区名称")
    private String provinceName;

    @ExcelProperty("市县代码")
    private String cityCode;

    @ExcelProperty("市县名称")
    private String cityName;

    @ExcelProperty("住宅区")
    private String livingAddress;
}
