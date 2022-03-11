package com.lswstudy.commonutils;

/**
 * 定义状态码，20000代表是成功，20001代表是失败
 * @author lswstudy
 * @create 2021-10-08-15:10
 */
public enum ResultCode {
    SUCCESS(20000),
    FAILED(20001);

    private final Integer code;

    private ResultCode(Integer code){
        this.code = code;
    }

    public Integer getCode(){ return code; }
}
