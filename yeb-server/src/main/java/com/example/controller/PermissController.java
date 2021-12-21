package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pojo.*;
import com.example.service.IMenuRoleService;
import com.example.service.IMenuService;
import com.example.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation("获取所有角色")
    @GetMapping("/")
    public List<Role> allRoles(){
        return roleService.list();
    }

    @ApiOperation("添加一个角色")
    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role){
        if(role.getName().startsWith("ROLE_")){
            if(roleService.save(role)){
                return RespBean.success("增加成功");
            }
            return RespBean.error("增加失败，请重试");
        }
        return RespBean.error("名称不合法，请重新输入");
    }

    @ApiOperation("更新一个角色")
    @PutMapping("/")
    public RespBean updateRole(Role role){
        if(roleService.updateById(role)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败，请重试");
    }

    @ApiOperation("删除一个角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRole(@PathVariable Integer rid){
        if(roleService.removeById(rid)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败，请重试");
    }

    @ApiOperation("删除多个角色")
    @DeleteMapping("/")
    public RespBean deleteRoles(Integer[] ids){
        if(roleService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("批量删除成功");
        }
        return RespBean.error("删除失败，请重试");
    }

    @ApiOperation("查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> allMenus(){
        return menuService.getAllMenus();
    }

    @ApiOperation("根据角色id查询菜单id")
    @PostMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid){
        List<MenuRole> list = menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", rid));
        List<Integer> id = list.stream().map(MenuRole::getMid).collect(Collectors.toList());
        return id;
    }
}
