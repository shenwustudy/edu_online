package com.lswstudy.commonutils;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 本类用于统一返回数据格式
 * @author lswstudy
 * @create 2021-10-08-15:19
 */
@Data
public class ResultData {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    private ResultData(){}

    public static ResultData ok(){
        ResultData resultData = new ResultData();
        resultData.setSuccess(true);
        resultData.setCode(ResultCode.SUCCESS.getCode());
        resultData.setMessage("成功");
        return resultData;
    }
    public static ResultData error(){
        ResultData resultData = new ResultData();
        resultData.setSuccess(false);
        resultData.setCode(ResultCode.FAILED.getCode());
        resultData.setMessage("失败");
        return resultData;
    }
    public ResultData success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public ResultData message(String message){
        this.setMessage(message);
        return this;
    }
    public ResultData code(Integer code){
        this.setCode(code);
        return this;
    }
    public ResultData data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public ResultData data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
