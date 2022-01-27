package com.uu.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.uu.modules.sys.entity.SysCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 *
 * @author crazySea
 * @email 960236576@qq.com
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptchaEntity> {

}
