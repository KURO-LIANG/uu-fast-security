/**
 * Copyright (c) 2016-2019 浩浩商城 All rights reserved.
 *
 * https://www.slowcom.com
 *
 * 版权所有，侵权必究！
 */

package com.uu.modules.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uu.common.utils.Constant;
import com.uu.constant.RedisKeys;
import com.uu.modules.sys.dao.SysMenuDao;
import com.uu.modules.sys.entity.SysMenuEntity;
import com.uu.modules.sys.entity.SysRoleMenuEntity;
import com.uu.modules.sys.service.SysMenuService;
import com.uu.modules.sys.service.SysRoleMenuService;
import com.uu.modules.sys.service.SysUserService;
import com.uu.result.Result;
import com.uu.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Resource
	private IRedisService iRedisService;

	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenuEntity> menuList = queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}

		List<SysMenuEntity> userMenuList = new ArrayList<>();
		for(SysMenuEntity menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId) {
		return baseMapper.queryListParentId(parentId);
	}

	@Override
	public List<SysMenuEntity> queryNotButtonList() {
		return baseMapper.queryNotButtonList();
	}

	@Override
	public List<SysMenuEntity> getUserMenuList(Long userId) {
		//系统管理员，拥有最高权限
		if(userId == Constant.SUPER_ADMIN){
			return getAllMenuList(null);
		}

		//用户菜单列表
		List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}

	@Override
	public void delete(List<Long> menuIdList){
		//删除菜单
		this.removeByIds(menuIdList);

		//删除菜单与角色关联
		sysRoleMenuService.remove(
				new LambdaQueryWrapper<SysRoleMenuEntity>()
						.in(SysRoleMenuEntity::getMenuId,menuIdList)
		);

		for (Long menuId : menuIdList) {
			iRedisService.hdel(
					RedisKeys.menu,
					String.valueOf(menuId)
			);
		}
	}

    @Override
    public Result<List<SysMenuEntity>> getAllMenu() {
		List<SysMenuEntity> sysMenuEntityList;
		//获取缓存数据
		Map<String, String> menuMap = iRedisService.hGetAll(RedisKeys.menu);
		if (menuMap.size() > 0) {
			sysMenuEntityList = menuMap.values()
					.stream().map(s -> JSONObject.parseObject(s, SysMenuEntity.class)).collect(Collectors.toList());
		}else{
			sysMenuEntityList = baseMapper.getAllMenu();

			sysMenuEntityList.forEach(sysMenuEntity -> iRedisService.hset(
					RedisKeys.menu,
					String.valueOf(sysMenuEntity.getMenuId()),
					JSONObject.toJSONString(sysMenuEntity)
			));
		}

		return new Result<List<SysMenuEntity>>().ok(sysMenuEntityList);
    }

    /**
	 * 获取所有菜单列表
	 */
	private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);

		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList){
		List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

		for(SysMenuEntity entity : menuList){
			//目录
			if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}

		return subMenuList;
	}
}
