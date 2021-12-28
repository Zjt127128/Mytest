package com.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pojo.Employee;
import com.example.pojo.RespBean;
import com.example.pojo.RespPageBean;
import com.example.service.IEmployeeService;
import com.example.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private JavaMailSenderImpl mailSender;

    //获取所有员工
    @Override
    public RespPageBean getEmployee(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        Page<Employee> page = new Page<>(currentPage,size);
        IPage<Employee> employeeIPage = employeeMapper.getEmployee(page, employee, beginDateScope);
        RespPageBean respPageBean=new RespPageBean(employeeIPage.getTotal(),employeeIPage.getRecords());
        return respPageBean;
    }

    //获取工号
    @Override
    public RespBean maxWorkID() {
        Integer integer = employeeMapper.getmaxWorkID()+1;
        return RespBean.success("当前值为：",integer);
    }

    //添加员工
    @Override
    public RespBean addEmp(Employee employee) {
        //处理合同期限，保留两位小数
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days/365.00)));

        if(1==employeeMapper.insert(employee)){
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("zjt12712850662021@163.com");
            simpleMailMessage.setTo(employee.getEmail());
            simpleMailMessage.setSubject("欢迎加入梦天门");
            simpleMailMessage.setText("欢迎"+employee.getName()+"您成为我们的一员");
            simpleMailMessage.setSentDate(new Date());
            mailSender.send(simpleMailMessage);
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    //获取员工
    @Override
    public List<Employee> getEmployee1(Integer id) {
        return employeeMapper.getEmployee1(id);

    }

    @Override
    public RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size) {
        //开启分页
        Page<Employee> page = new Page<>(currentPage,size);
        IPage<Employee> employeeIPage=employeeMapper.getEmployeeWithSalary(page);
        RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(),employeeIPage.getRecords());
        return respPageBean;
    }
}
