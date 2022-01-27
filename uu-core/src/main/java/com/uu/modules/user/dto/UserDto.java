package com.uu.modules.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uu.utils.DateTimeUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description：
 * @Date: 2021/6/7
 * @Author: liangqing 道玄
 * @Email: clarence_liang@163.com
 */
@Data
public class UserDto {


    /**
     * 用户id
     */
    @ApiModelProperty("用户ID")
    private Long userId;
    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickName;
    /**
     * 用户头像
     */
    @ApiModelProperty("用户头像")
    private String avatar;
    /**
     * 性别： 0-未知，1-男，2-女
     */
    @ApiModelProperty("性别： 0-未知，1-男，2-女")
    private Integer gender;
    /**
     * 生日
     */
    @ApiModelProperty("生日")
    @JsonFormat(pattern = DateTimeUtils.DATE_PATTERN)
    private LocalDateTime birthday;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;
    /**
     * 省份
     */
    @ApiModelProperty("省份")
    private String province;
    /**
     * 城市
     */
    @ApiModelProperty("城市")
    private String city;
    /**
     * 国家
     */
    @ApiModelProperty("国家")
    private String country;

}
