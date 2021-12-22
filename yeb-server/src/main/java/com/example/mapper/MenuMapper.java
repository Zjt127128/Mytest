package com.example.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Menu;
import com.example.pojo.MenuRole;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangjintao
 * @since 2021-12-13
 */
public interface MenuMapper extends BaseMapper<Menu> {

    //根据用户id查询菜单列表
    List<Menu> getMenusByAdminId(Integer id);

    //根据角色查询菜单列表
    List<Menu> getMenusWithRole();

    //查询所有菜单
    List<Menu> getAllMenus();


}
