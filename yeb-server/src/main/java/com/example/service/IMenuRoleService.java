package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.MenuRole;
import com.example.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangjintao
 * @since 2021-12-13
 */
public interface IMenuRoleService extends IService<MenuRole> {

    //更新角色菜单
    RespBean updateMenuRole(Integer rid, Integer[] mids);

}
