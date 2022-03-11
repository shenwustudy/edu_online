package com.lswstudy.demp.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lswstudy
 * @create 2022-01-12-10:42
 */
public class TestEasyExcel {
    public static void main(String[] args) {
//        //测试easyExcel的写操作
//        //1.设置文件路径以及文件名称
//        String fileName = "D:\\write.xlsx";
//
//        //2.调用easyExcel中的方法来实现写操作
//        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());

        //测试easyExcel的读操作
        String fileName = "D:\\write.xlsx";

        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();

    }

    //返回list集合
    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setStuNumber(i);
            data.setStuName("student" + i);
            list.add(data);
        }
        return list;
    }
}
