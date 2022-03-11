package com.lswstudy.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lswstudy
 * @create 2022-01-12-14:49
 */

@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class EduException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常信息
}
