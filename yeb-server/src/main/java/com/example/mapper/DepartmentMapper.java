package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Department;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangjintao
 * @since 2021-12-13
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    //获取所有部门
    List<Department> getAllDepartment(Integer parentId);

    //添加一个部门
    void addDepartment(Department department);

    //删除一个部门
    void deleteDepartment(Department department);
}
