package com.uu.modules.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Date: 2021/8/3
 * @Author: liangqing 道玄
 * @Email: clarence_liang@163.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoCountDto {
    @ApiModelProperty("城市用户量列表")
    private List<GeoCityDto> dataList;
    @ApiModelProperty("城市经纬度列表")
    private Map<String,double[]> longAndLati;
}
