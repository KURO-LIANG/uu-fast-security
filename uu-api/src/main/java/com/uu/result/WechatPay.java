package com.uu.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description：
 * @Date: 2021/10/3
 * @Author: liangqing 道玄
 * @Email: clarence_liang@163.com
 */
@Data
public class WechatPay {
    @ApiModelProperty("公众号appId")
    private String appId;
    @ApiModelProperty("随机字符串")
    private String nonceStr;
    @ApiModelProperty("时间戳")
    private String time;
    @ApiModelProperty("预支付id")
    private String prepayId;
    @ApiModelProperty("签名")
    private String signature;
    @ApiModelProperty("签名类型")
    private String signType;
    @ApiModelProperty("H5支付链接，仅限微信站外支付时，返回此支付链接")
    private String webUrl;
}
