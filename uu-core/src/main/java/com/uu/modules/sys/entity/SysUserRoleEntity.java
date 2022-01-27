package com.uu.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Data;

/**
 * 用户与角色对应关系
 *
 * @author KURO
 * @email clarence_liang@163.com
 * @date 2021-06-03 18:30:20
 */
@ApiModel(value = " 用户与角色对应关系")
@Data
@TableName("sys_user_role")
public class SysUserRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@ApiModelProperty(value = "")
	@TableId
	private Long id;
	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID")
	private Long userId;
	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色ID")
	private Long roleId;

}
