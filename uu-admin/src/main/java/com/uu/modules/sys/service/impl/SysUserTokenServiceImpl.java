package com.uu.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uu.modules.sys.dao.SysUserTokenDao;
import com.uu.modules.sys.dto.SysUserLoginDto;
import com.uu.modules.sys.entity.SysUserTokenEntity;
import com.uu.modules.sys.service.SysUserTokenService;
import com.uu.utils.TokenGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
    /**
     * 登陆token过期时间：12小时后过期
     */
    private final static int EXPIRE = 12;
    private final static int EXPIRE_TIME = 12 * 3600;

    @Override
    public SysUserLoginDto createToken(Long userId) {

        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        LocalDateTime now = LocalDateTime.now();
        //过期时间
        LocalDateTime expireTime = now.plusHours(EXPIRE);

        //判断是否生成过token
        SysUserTokenEntity tokenEntity = this.getById(userId);
        if (tokenEntity == null) {
            tokenEntity = new SysUserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            baseMapper.insert(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            this.updateById(tokenEntity);
        }

        SysUserLoginDto sysUserLoginDto = new SysUserLoginDto();
        sysUserLoginDto.setExpire(EXPIRE_TIME);
        sysUserLoginDto.setToken(token);
        return sysUserLoginDto;
    }

    @Override
    public void logout(Long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //修改token
        SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        this.updateById(tokenEntity);
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        return baseMapper.queryByToken(token);
    }

}
