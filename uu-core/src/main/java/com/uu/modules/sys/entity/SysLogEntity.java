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
 * 系统日志
 *
 * @author KURO
 * @email clarence_liang@163.com
 * @date 2021-06-03 18:30:20
 */
@ApiModel(value = " 系统日志")
@Data
@TableName("sys_log")
public class SysLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@ApiModelProperty(value = "")
	@TableId
	private Long id;
	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名")
	private String username;
	/**
	 * 用户操作
	 */
	@ApiModelProperty(value = "用户操作")
	private String operation;
	/**
	 * 请求方法
	 */
	@ApiModelProperty(value = "请求方法")
	private String method;
	/**
	 * 请求参数
	 */
	@ApiModelProperty(value = "请求参数")
	private String params;
	/**
	 * 执行时长(毫秒)
	 */
	@ApiModelProperty(value = "执行时长(毫秒)")
	private Long time;
	/**
	 * IP地址
	 */
	@ApiModelProperty(value = "IP地址")
	private String ip;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createDate;

}
