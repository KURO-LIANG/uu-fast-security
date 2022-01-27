package com.uu.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.uu.utils.DateTimeUtils;
import com.uu.validator.group.AddGroup;
import com.uu.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统用户
 *
 * @author KURO
 * @email clarence_liang@163.com
 * @date 2021-06-03 18:30:20
 */
@ApiModel(value = " 系统用户")
@Data
@TableName("sys_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId
	private Long userId;

	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String username;

	/**
	 * 密码
	 */
	@NotBlank(message = "密码不能为空", groups = AddGroup.class)
	private String password;

	/**
	 * @Description: 备注
	 **/
	private String remark;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 邮箱
	 */
	@NotBlank(message = "邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@Email(message = "邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
	private String email;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 状态  0：禁用   1：正常
	 */
	private Integer status;

	/**
	 * 角色ID列表
	 */
	@TableField(exist = false)
	private List<Long> roleIdList;

	/**
	 * 创建者ID
	 */
	private Long createUserId;
	/**
	 * 部门ID
	 */
	@ApiModelProperty(value = "部门ID")
	@NotNull(message = "部门不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private Long deptId;

	/**
	 * 部门名称
	 */
	@ApiModelProperty(value = "部门名称")
	@TableField(exist = false)
	private String deptName;

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
