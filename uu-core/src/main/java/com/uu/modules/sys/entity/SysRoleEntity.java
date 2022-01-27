package com.uu.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.uu.utils.DateTimeUtils;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色
 *
 * @author KURO
 * @email clarence_liang@163.com
 * @date 2021-06-03 18:30:20
 */
@ApiModel(value = " 角色")
@Data
@TableName("sys_role")
public class SysRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	@TableId
	private Long roleId;

	/**
	 * 角色名称
	 */
	@NotBlank(message = "角色名称不能为空")
	private String roleName;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建者ID
	 */
	private Long createUserId;
	/**
	 * 部门ID
	 */
	@NotNull(message = "部门不能为空")
	private Long deptId;
	/**
	 * 部门名称
	 */
	@TableField(exist = false)
	private String deptName;

	@TableField(exist = false)
	private List<Long> menuIdList;

	@TableField(exist = false)
	private List<Long> deptIdList;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = DateTimeUtils.DATE_TIME_PATTERN)
	private LocalDateTime createTime;
	/**
	 * 乐观锁
	 */
	@Version
	private Integer version;

}
