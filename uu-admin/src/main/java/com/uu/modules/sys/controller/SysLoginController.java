/**
 * Copyright (c) 2016-2019 浩浩商城 All rights reserved.
 *
 * https://www.slowcom.com
 *
 * 版权所有，侵权必究！
 */

package com.uu.modules.sys.controller;


import com.uu.modules.sys.dto.SysUserDto;
import com.uu.modules.sys.dto.SysUserLoginDto;
import com.uu.modules.sys.entity.SysUserEntity;
import com.uu.modules.sys.form.SysLoginForm;
import com.uu.modules.sys.service.SysCaptchaService;
import com.uu.modules.sys.service.SysUserService;
import com.uu.modules.sys.service.SysUserTokenService;
import com.uu.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录相关
 *
 * @author Mark sunlightcs@gmail.com
 */
@Api(tags = "后台登录")
@RestController
public class SysLoginController  extends AbstractController {
	@Value("${spring.profiles.active}")
	private String env;
	@Autowired
	private SysCaptchaService sysCaptchaService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;

	@ApiOperation(value = "获取登陆验证码（图像）", notes = "直接响应图片流")
	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, String uuid)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

		//获取图片验证码
		BufferedImage image = sysCaptchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
	}

	/**
	 * 登录
	 */
	@ApiOperation(value = "管理员登陆")
	@PostMapping("/sys/login")
	public Result<SysUserLoginDto> login(@RequestBody SysLoginForm form) {
		if ("prod".equals(env) || !"888888".equals(form.getCaptcha())) {
			boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
			if (!captcha) {
				return new Result<SysUserLoginDto>().error("验证码不正确");
			}
		}

		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

		//账号不存在、密码错误
		if (user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
			return new Result<SysUserLoginDto>().error("账号或密码不正确");
		}

		//账号锁定
		if (user.getStatus() == 0) {
			return new Result<SysUserLoginDto>().error("账号已被锁定,请联系管理员");
		}

		//生成token，并保存到数据库
		SysUserLoginDto sysUserLoginDto = sysUserTokenService.createToken(user.getUserId());

		SysUserDto sysUserDto = new SysUserDto();
		BeanUtils.copyProperties(user,sysUserDto);

		sysUserLoginDto.setUserInfo(sysUserDto);
		return new Result<SysUserLoginDto>().ok(sysUserLoginDto);
	}

	/**
	 * 退出
	 */
	@ApiOperation(value = "操作员退出登陆")
	@GetMapping("/sys/logout")
	public Result<Object> logout() {
		sysUserTokenService.logout(getUserId());
		return new Result<>().ok();
	}

}
