package com.uu.modules.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description：
 * @Date: 2021/6/16
 * @Author: liangqing 道玄
 * @Email: clarence_liang@163.com
 */
@Data
public class SysUserLoginDto {
    @ApiModelProperty("安全校验TOKEN")
    private String token;
    @ApiModelProperty("过期时间")
    private int expire;
    @ApiModelProperty("登录用户信息")
    private SysUserDto userInfo;
}
