package com.uu.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单管理
 *
 * @author KURO
 * @email clarence_liang@163.com
 * @date 2021-06-03 18:30:20
 */
@ApiModel(value = " 菜单管理")
@Data
@TableName("sys_menu")
public class SysMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	@TableId
	@ApiModelProperty("菜单id")
	private Long menuId;

	/**
	 * 父菜单ID，一级菜单为0
	 */
	@ApiModelProperty("父菜单ID，一级菜单为0")
	private Long parentId;

	/**
	 * 父菜单名称
	 */
	@TableField(exist = false)
	@ApiModelProperty("父菜单名称")
	private String parentName;

	/**
	 * 菜单名称
	 */
	@ApiModelProperty("菜单名称")
	private String name;

	/**
	 * 菜单URL
	 */
	@ApiModelProperty("菜单URL")
	private String url;

	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	@ApiModelProperty("授权(多个用逗号分隔，如：user:list,user:create)")
	private String perms;

	/**
	 * 类型     0：目录   1：菜单   2：按钮
	 */
	@ApiModelProperty("类型     0：目录   1：菜单   2：按钮")
	private Integer type;

	/**
	 * 菜单图标
	 */
	@ApiModelProperty("菜单图标")
	private String icon;

	/**
	 * 排序
	 */
	@ApiModelProperty("排序")
	private Integer orderNum;

	/**
	 * ztree属性
	 */
	@ApiModelProperty("ztree属性")
	@TableField(exist = false)
	private Boolean open;

	@TableField(exist = false)
	@ApiModelProperty("菜单id")
	private List<?> list;
	/**
	 * 乐观锁
	 */
	@Version
	@ApiModelProperty(hidden = true)
	private Integer version;

}
