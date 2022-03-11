package com.lswstudy.educenter.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lswstudy
 * @create 2022-02-25-13:17
 */
@Data
@ApiModel(value="注册对象", description="用于封装注册信息")
public class RegisterVo {

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;
}
