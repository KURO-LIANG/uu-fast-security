package com.uu.modules.user.result;

import com.uu.modules.user.dto.UserDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description：
 * @Date: 2021/6/7
 * @Author: liangqing 道玄
 * @Email: clarence_liang@163.com
 */
@Data
public class WechatLoginResult {
    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "过期时间，时间戳")
    private Long expire;

    @ApiModelProperty(value = "用户信息")
    private UserDto userInfo;

    @ApiModelProperty(value = "微信小程序openId")
    private String miniOpenId;
    @ApiModelProperty(value = "APP端微信openId")
    private String appOpenId;
    /**
     * 开放平台统一id
     */
    @ApiModelProperty(value = "开放平台统一id")
    private String unionId;
}
