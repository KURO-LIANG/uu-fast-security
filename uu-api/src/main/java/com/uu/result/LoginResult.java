package com.uu.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Clarence
 * @Description:
 * @Date: 2021/10/3 15:24.
 */
@Data
public class LoginResult {
    @ApiModelProperty("登录token")
    private String token;
    @ApiModelProperty("到期时间戳")
    private Long expire;
}
