package com.uu.modules.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.uu.utils.DateTimeUtils;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 *
 * @author KURO
 * @email clarence_liang@163.com
 * @date 2021-06-07 13:48:35
 */
@Data
@TableName("tb_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	private Long userId;

	/**
	 * 乐观锁
	 */
	private Integer version;
	/**
	 * 删除标记 0-正常，1-删除
	 */
	private Integer delFlag;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 用户头像
	 */
	private String avatar;
	/**
	 * 上次登录时间
	 */
	@JsonFormat(pattern = DateTimeUtils.DATE_TIME_PATTERN)
	private LocalDateTime lastLoginTime;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = DateTimeUtils.DATE_TIME_PATTERN)
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = DateTimeUtils.DATE_TIME_PATTERN)
	private LocalDateTime updateTime;
	/**
	 * 更新人
	 */
	private String updateBy;

}
