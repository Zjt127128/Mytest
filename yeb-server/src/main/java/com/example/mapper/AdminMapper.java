package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Admin;
import com.example.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangjintao
 * @since 2021-12-13
 */
public interface AdminMapper extends BaseMapper<Admin> {

    //通过用户id查询菜单列表
    List<Menu> getMenusByAdminId(Integer id);

    //查询所有操作员
    List<Admin> getAllAdmin(@Param("id") Integer id, @Param("keywords") String keywords);


}
