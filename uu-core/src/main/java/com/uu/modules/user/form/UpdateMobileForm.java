package com.uu.modules.user.form;

import com.uu.constant.Constant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Description：
 * @Date: 2021/7/22
 * @Author: liangqing 道玄
 * @Email: clarence_liang@163.com
 */
@Data
public class UpdateMobileForm {
    @ApiModelProperty(value = "新手机号",required = true)
    @NotBlank(message = "请填写新手机号")
    @Pattern(regexp = Constant.PHONE_REG,message = "手机号格式不正确")
    private String mobile;

    @ApiModelProperty(value = "旧手机验证码")
    private String oldVerCode;

    @ApiModelProperty(value = "新手机验证码",required = true)
    @NotBlank(message = "新手机验证码已失效！")
    private String newVerCode;
}
