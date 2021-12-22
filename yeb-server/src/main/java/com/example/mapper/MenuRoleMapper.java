package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.MenuRole;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangjintao
 * @since 2021-12-13
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    //更新角色菜单
    Integer insertRecord(Integer rid, Integer[] mids);

}
