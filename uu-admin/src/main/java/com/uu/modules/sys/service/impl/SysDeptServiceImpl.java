/**
 * Copyright (c) 2016-2019 浩浩商城 All rights reserved.
 *
 * https://www.slowcom.com
 *
 * 版权所有，侵权必究！
 */

package com.uu.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uu.common.annotation.DataFilter;
import com.uu.modules.sys.dao.SysDeptDao;
import com.uu.modules.sys.entity.SysDeptEntity;
import com.uu.modules.sys.service.SysDeptService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {

	@Override
	@DataFilter(subDept = true, user = false, tableAlias = "t1")
	public List<SysDeptEntity> queryList(Map<String, Object> params){
		return baseMapper.queryList(params);
	}

	@Override
	public List<Long> queryDetpIdList(Long parentId) {
		return baseMapper.queryDetpIdList(parentId);
	}

	@Override
	public List<Long> getSubDeptIdList(Long deptId){
		//部门及子部门ID列表
		List<Long> deptIdList = new ArrayList<>();

		//获取子部门ID
		List<Long> subIdList = queryDetpIdList(deptId);
		getDeptTreeList(subIdList, deptIdList);

		return deptIdList;
	}

	/**
	 * 递归
	 */
	private void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList){
		for(Long deptId : subIdList){
			List<Long> list = queryDetpIdList(deptId);
			if(list.size() > 0){
				getDeptTreeList(list, deptIdList);
			}

			deptIdList.add(deptId);
		}
	}
}
