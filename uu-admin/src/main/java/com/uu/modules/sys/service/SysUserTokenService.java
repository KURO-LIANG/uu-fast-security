package com.uu.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uu.modules.sys.dto.SysUserLoginDto;
import com.uu.modules.sys.entity.SysUserTokenEntity;

/**
 * 系统用户Token表
 *
 * @author KURO
 * @email clarence_liang@163.com
 * @date 2021-09-25 10:58:14
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

    SysUserLoginDto createToken(Long userId);

    void logout(Long userId);

    SysUserTokenEntity queryByToken(String token);
}

