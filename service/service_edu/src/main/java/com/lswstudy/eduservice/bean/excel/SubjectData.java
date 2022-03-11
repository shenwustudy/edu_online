package com.lswstudy.eduservice.bean.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author lswstudy
 * @create 2022-01-12-14:04
 */
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
