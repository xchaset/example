package com.xchaset.excelexample.excel.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取监听器
 */
public class CustomerReadListener extends AnalysisEventListener {


    private List<Object> list = new ArrayList<>();

    @Override
    public void invoke(Object data, AnalysisContext context) {
        list.add((data));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
//        list.clear();
    }

    public List getRows(){
        return list;
    }
}
