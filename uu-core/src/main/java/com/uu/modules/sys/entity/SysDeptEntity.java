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
 * 部门管理
 *
 * @author KURO
 * @email clarence_liang@163.com
 * @date 2021-06-03 18:30:20
 */
@ApiModel(value = " 部门管理")
@Data
@TableName("sys_dept")
public class SysDeptEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@ApiModelProperty(value = "")
	@TableId
	private Long deptId;
	/**
	 * 上级部门ID，一级部门为0
	 */
	@ApiModelProperty(value = "上级部门ID，一级部门为0")
	private Long parentId;
	/**
	 * 部门名称
	 */
	@ApiModelProperty(value = "部门名称")
	private String name;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer orderNum;
	/**
	 * 是否删除  -1：已删除  0：正常
	 */
	@ApiModelProperty(value = "是否删除  -1：已删除  0：正常")
	private Integer delFlag;

}
