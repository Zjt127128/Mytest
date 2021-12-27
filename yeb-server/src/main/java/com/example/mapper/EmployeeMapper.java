package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.pojo.Employee;
import com.example.pojo.RespBean;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangjintao
 * @since 2021-12-13
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    //获取所有员工
    IPage<Employee> getEmployee( Page<Employee> page, @Param("employee") Employee employee, @Param("beginDateScope") LocalDate[] beginDateScope);

    //获取工号
    Integer getmaxWorkID();

    //获取员工
    List<Employee> getEmployee1(Integer id);


    
    
}
