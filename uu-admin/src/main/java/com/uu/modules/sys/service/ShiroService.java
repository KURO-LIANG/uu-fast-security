package com.uu.modules.sys.service;


import com.uu.modules.sys.entity.SysUserEntity;
import com.uu.modules.sys.entity.SysUserTokenEntity;

import java.util.Set;

/**
 * shiro相关接口
 *
 * @author crazySea
 * @email 960236576@qq.com
 */
public interface ShiroService {
    /**
     * 获取用户权限列表
     *
     * @param userId
     * @return
     */
    Set<String> getUserPermissions(long userId);

    /**
     * 根据token查找
     *
     * @param token
     * @return
     */
    SysUserTokenEntity queryByToken(String token);

    /**
     * 根据用户ID，查询用户
     *
     * @param userId
     * @return
     */
    SysUserEntity queryUser(Long userId);
}
