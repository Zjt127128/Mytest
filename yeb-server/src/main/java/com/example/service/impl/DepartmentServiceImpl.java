package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pojo.Department;
import com.example.mapper.DepartmentMapper;
import com.example.pojo.RespBean;
import com.example.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangjintao
 * @since 2021-12-13
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    //获取所有部门
    @Override
    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartment(-1);
    }


    //添加一个部门
    @Override
    public RespBean addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDepartment(department);
        if(1==department.getResult()){
            return RespBean.success("添加成功",department);
        }
        return RespBean.error("添加失败");
    }

    //删除一个部门
    @Override
    public RespBean deleteDepartment(Integer id) {
        Department department1 = new Department();
        department1.setId(id);
        departmentMapper.deleteDepartment(department1);
        if(1==department1.getResult()){
            return RespBean.success("删除成功",department1);
        }
        if(-2==department1.getResult()){
            return RespBean.error("删除失败，该部门下还有子部门",department1);
        }
        if(-1==department1.getResult()){
            return RespBean.error("删除失败，该部门下还有员工");
        }
        return null;
    }


}
