package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Department;
import com.example.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangjintao
 * @since 2021-12-13
 */
public interface IDepartmentService extends IService<Department> {

    //获取所有部门
    List<Department> getAllDepartment();


    //添加一个部门
    RespBean addDepartment(Department department);

    //删除一个部门
    RespBean deleteDepartment(Integer id);
}
