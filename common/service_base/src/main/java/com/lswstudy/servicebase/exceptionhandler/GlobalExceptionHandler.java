package com.lswstudy.servicebase.exceptionhandler;

import com.lswstudy.commonutils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lswstudy
 * @create 2021-10-08-21:35
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultData error(Exception e){
        e.printStackTrace();
        return ResultData.error().message("执行了全局异常处理");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody //为了返回数据
    public ResultData error(ArithmeticException e) {
        e.printStackTrace();
        return ResultData.error().message("执行了ArithmeticException异常处理..");
    }

    //自定义异常
    @ExceptionHandler(EduException.class)
    @ResponseBody //为了返回数据
    public ResultData error(EduException e) {
        log.error(e.getMessage());
        e.printStackTrace();

        return ResultData.error().code(e.getCode()).message(e.getMsg());
    }
}
