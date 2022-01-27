package com.uu.modules.sys.dto;

import com.uu.modules.sys.entity.SysMenuEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @Description：
 * @Date: 2021/6/17
 * @Author: liangqing 道玄
 * @Email: clarence_liang@163.com
 */
@Data
public class SysNavDto {
    @ApiModelProperty(value = "菜单列表",required = true)
    private List<SysMenuEntity> menuList;
    @ApiModelProperty(value = "权限列表",required = true)
    private Set<String> permissions;
}
