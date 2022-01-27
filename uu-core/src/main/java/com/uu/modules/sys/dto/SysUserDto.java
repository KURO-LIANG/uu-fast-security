package com.uu.modules.sys.dto;

import lombok.Data;

/**
 * @Description：
 * @Date: 2021/6/18
 * @Author: liangqing 道玄
 * @Email: clarence_liang@163.com
 */
@Data
public class SysUserDto {
    private Long userId;
    private String username;
    private String email;
    private String mobile;
    private Integer status;
}
