package com.uu.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.uu.utils.DateTimeUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统更新表
 *
 * @author KURO
 * @email clarence_liang@163.com
 * @date 2020-12-19 16:50:12
 */
@ApiModel(value = "系统更新日志表")
@Data
@TableName("sys_update_log")
public class SysUpdateLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@ApiModelProperty(value = "")
	@TableId
	private Long id;
	/**
	 * 更新版本
	 */
	@ApiModelProperty(value = "更新版本")
	private String updateVersion;
	/**
	 * 更新内容
	 */
	@ApiModelProperty(value = "更新内容")
	private String content;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	@JsonFormat(pattern = DateTimeUtils.DATE_TIME_PATTERN)
	private LocalDateTime createTime;
	/**
	 * 更新用户ID
	 */
	@ApiModelProperty(value = "更新用户ID")
	private Long sysUserId;
	/**
	 * 更新用户
	 */
	@ApiModelProperty(value = "更新用户")
	private String sysUserName;

}
