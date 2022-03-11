package com.lswstudy.demp.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author lswstudy
 * @create 2022-01-12-10:39
 */
@Data
public class DemoData {

    //@ExcelProperty("")来设置excel表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer stuNumber;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String stuName;


}
