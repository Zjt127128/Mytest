package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangjintao
 * @since 2021-12-13
 */
public interface IMenuService extends IService<Menu> {

    //根据用户id查询菜单列表
    List<Menu> getMenusByAdminId();

    //根据角色获取菜单列表
    List<Menu> getMenusWithRole();
}
