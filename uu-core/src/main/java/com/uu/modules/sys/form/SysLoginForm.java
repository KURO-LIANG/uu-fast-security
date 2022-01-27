package com.uu.modules.sys.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录表单
 *
 * @author crazySea
 * @email 960236576@qq.com
 */
@Data
public class SysLoginForm {
    @ApiModelProperty(value = "登录名",required = true)
    @NotBlank(message = "请填写账号名称")
    private String username;
    @ApiModelProperty(value = "密码",required = true)
    @NotBlank(message = "请填写密码")
    private String password;
    @ApiModelProperty(value = "验证码",required = true)
    @NotBlank(message = "请填写验证码")
    private String captcha;
    @ApiModelProperty(value = "随机uuid",required = true)
    @NotBlank(message = "缺少随机uuid参数")
    private String uuid;


}
