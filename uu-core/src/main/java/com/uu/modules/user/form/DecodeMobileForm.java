package com.uu.modules.user.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description：
 * @Date: 2021/7/21
 * @Author: liangqing 道玄
 * @Email: clarence_liang@163.com
 */
@Data
public class DecodeMobileForm {
    @ApiModelProperty(value = "加密数据",required = true)
    @NotBlank(message = "缺少加密数据参数")
    private String encryptedData;
    @ApiModelProperty(value = "偏移量IV",required = true)
    @NotBlank(message = "缺少偏移量IV参数")
    private String iv;
    @ApiModelProperty(value = "授权码",required = true)
    @NotBlank(message = "缺少授权码参数")
    private String code;
}
