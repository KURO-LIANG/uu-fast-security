package com.uu.modules.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description：
 * @Date: 2021/8/3
 * @Author: liangqing 道玄
 * @Email: clarence_liang@163.com
 */
@Data
public class GeoCityDto {
    @ApiModelProperty("省份")
    private String province;
    @ApiModelProperty("城市")
    private String city;
    @ApiModelProperty("用户量")
    private Integer value;
    @ApiModelProperty("经纬度  经度,纬度")
    private String longAndLati;
}
