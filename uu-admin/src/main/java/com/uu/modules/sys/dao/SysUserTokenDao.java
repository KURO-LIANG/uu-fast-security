package com.uu.modules.sys.dao;

import com.uu.modules.sys.entity.SysUserTokenEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token表
 *
 * @author KURO
 * @email clarence_liang@163.com
 * @date 2021-09-25 10:58:14
 */
@Mapper
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);
}
