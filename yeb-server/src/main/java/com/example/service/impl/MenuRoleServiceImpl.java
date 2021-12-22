package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pojo.MenuRole;
import com.example.mapper.MenuRoleMapper;
import com.example.pojo.RespBean;
import com.example.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangjintao
 * @since 2021-12-13
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {
    @Autowired
    private MenuRoleMapper menuRoleMapper;

    //更新角色菜单
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if(mids==null||0==mids.length){
            return RespBean.success("更新成功");
        }
        Integer integer = menuRoleMapper.insertRecord(rid, mids);
        if(0==integer){
            return RespBean.error("更新失败");
        }
        return RespBean.success("更新成功");
    }
}
