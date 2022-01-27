package com.uu.modules.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 微信用户表
 *
 * @author KURO
 * @email clarence_liang@163.com
 * @date 2021-06-07 13:48:35
 */
@Data
@TableName("tb_user_wechat")
public class UserWechatEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 微信用户id
	 */
	@TableId
	private Long wechatId;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 用户头像
	 */
	private String avatar;
	/**
	 * 性别： 0-未知，1-男，2-女
	 */
	private Integer gender;
	/**
	 * 生日
	 */
	private LocalDateTime birthday;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 小程序微信openid
	 */
	private String miniOpenId;
	/**
	 * APP微信openid
	 */
	private String appOpenId;
	/**
	 * 开放平台统一id
	 */
	private String unionId;
	/**
	 * 上次登录时间
	 */
	private LocalDateTime lastLoginTime;
	/**
	 * 上次登录ip地址
	 */
	private String lastLoginIp;
	/**
	 * 上次登录设备信息
	 */
	private String lastLoginInfo;
	/**
	 * 经纬度  经度,纬度
	 */
	private String longAndLati;
	/**
	 * 乐观锁
	 */
	private Integer version;
	/**
	 * 删除标记 0-正常，1-删除
	 */
	private Integer delFlag;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 更新人
	 */
	private String updateBy;

}
