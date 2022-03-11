package com.lswstudy.demp.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author lswstudy
 * @create 2022-01-12-11:09
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {
    //一行一行的读取excel表格的内容
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("xxxx" + demoData);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头:" + headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
