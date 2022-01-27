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
 * 数据字典表
 *
 * @author KURO
 * @email clarence_liang@163.com
 * @date 2021-06-03 18:30:20
 */
@ApiModel(value = " 数据字典表")
@Data
@TableName("sys_dict")
public class SysDictEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@ApiModelProperty(value = "")
	@TableId
	private Long id;
	/**
	 * 字典名称
	 */
	@ApiModelProperty(value = "字典名称")
	private String name;
	/**
	 * 字典类型
	 */
	@ApiModelProperty(value = "字典类型")
	private String type;
	/**
	 * 字典码
	 */
	@ApiModelProperty(value = "字典码")
	private String code;
	/**
	 * 字典值
	 */
	@ApiModelProperty(value = "字典值")
	private String value;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer orderNum;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;
	/**
	 * 删除标记  -1：已删除  0：正常
	 */
	@ApiModelProperty(value = "删除标记  -1：已删除  0：正常")
	private Integer delFlag;

}
