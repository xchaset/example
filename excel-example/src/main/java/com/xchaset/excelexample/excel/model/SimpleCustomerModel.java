package com.xchaset.excelexample.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SimpleCustomerModel {

    @ExcelProperty(value = {"客户信息","客户代码"})
    private String customerCode;

    @ExcelProperty(value = {"客户信息","客户名称"})
    private String name;

    @ExcelProperty(value = {"客户信息","性别"})
    private String gender;

    @ExcelProperty(value = {"出生信息","年龄"})
    private String age;

    @ExcelProperty(value = {"出生信息","出生日期"})
    private String birthday;

    @ExcelProperty(value = {"地址信息","客户地址"})
    private String address;

    @ExcelProperty(value = {"联系方式","电话"})
    private String tel;

    @ExcelProperty(value = {"联系方式","移动电话"})
    private String mobile;

    @ExcelProperty(value = {"联系方式","邮编"})
    private String postcode;

    @ExcelProperty(value = {"联系方式","邮箱"})
    private String email;

    @ExcelProperty(value = {"工作信息","职业"})
    private String occupation;

    @ExcelProperty(value = {"工作信息","职务"})
    private String job;

    @ExcelProperty(value = {"地址信息","省区代码"})
    private String provinceCode;

    @ExcelProperty(value = {"地址信息","省区名称"})
    private String provinceName;

    @ExcelProperty(value = {"地址信息","市县代码"})
    private String cityCode;

    @ExcelProperty(value = {"地址信息","市县名称"})
    private String cityName;

    @ExcelProperty(value = {"地址信息","住宅区"})
    private String livingAddress;
}
