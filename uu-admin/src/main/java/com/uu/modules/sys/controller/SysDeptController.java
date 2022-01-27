/**
 * Copyright (c) 2016-2019 浩浩商城 All rights reserved.
 *
 * https://www.slowcom.com
 *
 * 版权所有，侵权必究！
 */

package com.uu.modules.sys.controller;

import com.uu.common.annotation.DataFilter;
import com.uu.constant.SysConstant;
import com.uu.modules.sys.entity.SysDeptEntity;
import com.uu.modules.sys.service.SysDeptService;
import com.uu.result.R;
import com.uu.result.Result;
import com.uu.validator.ValidatorUtils;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 部门管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends AbstractController {
	@Autowired
	private SysDeptService sysDeptService;

	/**
	 * 获取所有列表
	 */
	@ApiOperation(value = "获取所有列表")
	@ApiImplicitParams({
	})
	@GetMapping("/list")
	@RequiresPermissions("sys:dept:list")
	@DataFilter(user = false, subDept = true)
	public Result<List<SysDeptEntity>> list(@ApiIgnore @RequestParam Map<String, Object> params) {
		List<SysDeptEntity> list = sysDeptService.queryList(params);

		return new Result<List<SysDeptEntity>>().ok(list);
	}

	/**
	 * 选择部门(添加、修改菜单)
	 */
	@ApiOperation(value = "部门管理-选择部门列表", response = SysDeptEntity.class)
	@GetMapping("/select")
	@RequiresPermissions("sys:dept:select")
	@DataFilter(subDept = true, user = false)
	public Result<List<SysDeptEntity>> select(Map<String, Object> params) {
		List<SysDeptEntity> deptList = sysDeptService.queryList(params);

		//添加一级部门
		if (getUserId() == SysConstant.SUPER_ADMIN) {
			SysDeptEntity root = new SysDeptEntity();
			root.setDeptId(0L);
			root.setName("一级部门");
			root.setParentId(-1L);
			deptList.add(root);
		}

		return new Result<List<SysDeptEntity>>().ok(deptList);
	}

	/**
	 * 获取某个信息详情
	 */
	@ApiOperation(value = "部门管理-获取某个信息详情", response = SysDeptEntity.class)
	@GetMapping("/info/{deptId}")
	@RequiresPermissions("sys:dept:info")
	public Result<SysDeptEntity> info(@PathVariable("deptId") Long deptId){
		SysDeptEntity sysDept = sysDeptService.getById(deptId);

		return new Result<SysDeptEntity>().ok(sysDept);
	}

	/**
	 * 新增信息
	 */
	@ApiOperation(value = "部门管理-新增信息")
	@PostMapping("/save")
	@RequiresPermissions("sys:dept:save")
	public R save(@RequestBody SysDeptEntity sysDept){
		ValidatorUtils.validateEntity(sysDept);
		sysDeptService.save(sysDept);

		return R.ok();
	}

	/**
	 * 修改信息
	 */
	@ApiOperation(value = "部门管理-修改信息")
	@PutMapping("/update")
	@RequiresPermissions("sys:dept:update")
	public R update(@RequestBody SysDeptEntity sysDept){
		ValidatorUtils.validateEntity(sysDept);
		sysDeptService.updateById(sysDept);

		return R.ok();
	}

	/**
	 * 删除信息
	 */
	@ApiOperation(value =" 部门管理-删除信息")
	@DeleteMapping("/delete")
	@RequiresPermissions("sys:dept:delete")
	public R delete(@RequestBody Long[] deptIds){
		if (deptIds == null || deptIds.length == 0) {
			return R.error("请输入要删除的记录id");
		}
		sysDeptService.removeByIds(Arrays.asList(deptIds));

		return R.ok();
	}

}
