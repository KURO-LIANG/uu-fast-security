package com.uu.modules.sys.service.impl;

import com.uu.constant.SysConstant;
import com.uu.modules.sys.entity.SysMenuEntity;
import com.uu.modules.sys.entity.SysUserEntity;
import com.uu.modules.sys.entity.SysUserTokenEntity;
import com.uu.modules.sys.service.ShiroService;
import com.uu.modules.sys.service.SysMenuService;
import com.uu.modules.sys.service.SysUserService;
import com.uu.modules.sys.service.SysUserTokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == SysConstant.SUPER_ADMIN) {
            List<SysMenuEntity> menuList = sysMenuService.list(null);
            permsList = new ArrayList<>(menuList.size());
            for (SysMenuEntity menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysUserService.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        return sysUserTokenService.queryByToken(token);
    }

    @Override
    public SysUserEntity queryUser(Long userId) {
        return sysUserService.getById(userId);
    }
}
