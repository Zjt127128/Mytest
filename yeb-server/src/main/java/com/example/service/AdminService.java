package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Admin;
import com.example.pojo.RespBean;
import com.example.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangjintao
 * @since 2021-12-13
 */
public interface AdminService extends IService<Admin> {
    //登录之后返回token
    RespBean login(String username, String password,String code, HttpServletRequest request);

    //根据用户名获取用户
    Admin getAdminByUserName(String username);

    //根据用户ID查询角色列表
    List<Role> getRoles(Integer adminId);

    //获取所有操作员
    List<Admin> getAllAdmin(String keywords);

    //更新操作员角色
    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    //更新密码
    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);
}
