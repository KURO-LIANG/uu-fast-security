package com.uu.modules.user.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @Description：
 * @Date: 2021/6/7
 * @Author: liangqing 道玄
 * @Email: clarence_liang@163.com
 */
@Data
public class AppLoginForm {

    @ApiModelProperty(value = "APP端微信openId",required = true)
    @NotBlank(message = "缺少APP端微信openId参数")
    private String appOpenId;
    /**
     * 开放平台统一id
     */
    @ApiModelProperty(value = "开放平台统一id")
    @NotBlank(message = "缺少开放平台统一id参数")
    private String unionId;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称",required = true)
    @NotBlank(message = "缺少用户昵称参数")
    private String nickName;
    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像",required = true)
    @NotBlank(message = "缺少用户头像参数")
    private String avatar;
    /**
     * 性别： 0-未知，1-男，2-女
     */
    @ApiModelProperty(value = "性别： 0-未知，1-男，2-女",required = true)
    @Min(value = 0,message = "缺少性别参数")
    private Integer gender;
    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    private LocalDateTime birthday;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;
    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    private String province;
    /**
     * 城市
     */
    @ApiModelProperty(value = "城市")
    private String city;
    /**
     * 国家
     */
    @ApiModelProperty(value = "国家")
    private String country;

}
