package com.uu.modules.sys.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description：
 * @Date: 2021/6/25
 * @Author: liangqing 道玄
 * @Email: clarence_liang@163.com
 */
@Data
public class AboutUsDto {
    @ApiModelProperty("关于我们内容")
    private String paramValue;
}
