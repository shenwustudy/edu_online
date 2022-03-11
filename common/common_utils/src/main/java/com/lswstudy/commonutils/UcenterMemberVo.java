package com.lswstudy.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lswstudy
 * @create 2022-02-27-14:23
 */
@Data
public class UcenterMemberVo {
    @ApiModelProperty(value = "会员id")
    private String id;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;
}
