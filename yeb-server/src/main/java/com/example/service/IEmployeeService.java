package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Employee;
import com.example.pojo.RespBean;
import com.example.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangjintao
 * @since 2021-12-13
 */
public interface IEmployeeService extends IService<Employee> {

    //获取所有员工
    RespPageBean getEmployee(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    //获取工号
    RespBean maxWorkID();

    //添加员工
    RespBean addEmp(Employee employee);

    //获取员工（不用分页）
    List<Employee> getEmployee1(Integer id);
}
