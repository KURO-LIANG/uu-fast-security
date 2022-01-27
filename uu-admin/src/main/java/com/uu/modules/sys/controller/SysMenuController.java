package com.uu.modules.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.uu.common.annotation.SysLog;
import com.uu.constant.RedisKeys;
import com.uu.constant.SysConstant;
import com.uu.exception.SCException;
import com.uu.modules.sys.dto.SysNavDto;
import com.uu.modules.sys.entity.SysMenuEntity;
import com.uu.modules.sys.service.ShiroService;
import com.uu.modules.sys.service.SysMenuService;
import com.uu.result.R;
import com.uu.result.Result;
import com.uu.service.IRedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 菜单管理
 *
 * @author KURO
 * @email clarence_liang@163.com
 * @date 2021-06-03 18:30:20
 */
@Api(tags = {"菜单管理"})
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController  {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private ShiroService shiroService;
    @Resource
    private IRedisService iRedisService;

    /**
     * 导航菜单
     */
    @ApiOperation(value = "系统菜单-导航菜单")
    @GetMapping("/nav")
    public Result<SysNavDto> nav() {
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
        Set<String> permissions = shiroService.getUserPermissions(getUserId());

        SysNavDto sysNavDto = new SysNavDto();
        sysNavDto.setMenuList(menuList);
        sysNavDto.setPermissions(permissions);

        return new Result<SysNavDto>().ok(sysNavDto);
    }

    /**
     * 所有菜单列表
     */
    @ApiOperation(value = "系统菜单-所有菜单列表")
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public Result<List<SysMenuEntity>> list() {

        return sysMenuService.getAllMenu();
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @ApiOperation(value = "系统菜单-选择菜单列表")
    @GetMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public Result<List<SysMenuEntity>> select() {
        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenuEntity root = new SysMenuEntity();
        root.setMenuId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);

        return new Result<List<SysMenuEntity>>().ok(menuList);
    }

    /**
     * 菜单信息
     */
    @ApiOperation(value = "系统菜单-获取某个菜单信息")
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public Result<SysMenuEntity> info(@PathVariable("menuId") Long menuId) {
        SysMenuEntity menu = sysMenuService.getById(menuId);
        return new Result<SysMenuEntity>().ok(menu);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "系统菜单-保存菜单信息")
    @SysLog("保存菜单")
    @PostMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public R save(@RequestBody SysMenuEntity menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.save(menu);

        //增加缓存
        iRedisService.hset(
                RedisKeys.menu,
                String.valueOf(menu.getMenuId()),
                JSONObject.toJSONString(menu)
        );
        return R.ok();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "系统菜单-修改菜单信息")
    @SysLog("修改菜单")
    @PutMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public R update(@RequestBody SysMenuEntity menu) {
        //数据校验
        verifyForm(menu);

        sysMenuService.updateById(menu);
        //增加缓存
        iRedisService.hset(
                RedisKeys.menu,
                String.valueOf(menu.getMenuId()),
                JSONObject.toJSONString(menu)
        );
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "系统菜单-删除菜单信息")
    @SysLog("删除菜单")
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public R delete(@RequestBody Long menuId) {
        if (menuId <= 31) {
            return R.error("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);

        List<Long> ids = menuList.stream().map(SysMenuEntity::getMenuId).collect(Collectors.toList());
        ids.add(menuId);

        sysMenuService.delete(ids);

        return R.ok();
    }

    @ApiOperation("清除菜单缓存接口")
    @GetMapping("/clearCache")
    @RequiresPermissions("sys:menu:list")
    public Result<Object> clearCache(){
        Set<String> menuIdSet = iRedisService.hGetKeys(RedisKeys.menu);
        menuIdSet.forEach(id -> iRedisService.hdel(RedisKeys.menu,id));
        return new Result<>().ok();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenuEntity menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new SCException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new SCException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == SysConstant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new SCException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = SysConstant.MenuType.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenuEntity parentMenu = sysMenuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == SysConstant.MenuType.CATALOG.getValue() ||
                menu.getType() == SysConstant.MenuType.MENU.getValue()) {
            if (parentType != SysConstant.MenuType.CATALOG.getValue()) {
                throw new SCException("上级菜单只能为目录类型");
            }
            return;
        }

        //按钮
        if (menu.getType() == SysConstant.MenuType.BUTTON.getValue()) {
            if (parentType != SysConstant.MenuType.MENU.getValue()) {
                throw new SCException("上级菜单只能为菜单类型");
            }
        }
    }
}
