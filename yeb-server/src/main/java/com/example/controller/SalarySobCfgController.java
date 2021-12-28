package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.pojo.Employee;
import com.example.pojo.RespBean;
import com.example.pojo.RespPageBean;
import com.example.pojo.Salary;
import com.example.service.IEmployeeService;
import com.example.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {
    @Autowired
    private ISalaryService salaryService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private RedisTemplate redisTemplate;


    @ApiOperation("获取所有的工资帐套")
    @GetMapping("/salaries")
    public List<Salary> getAllSalary(){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        List<Salary> salary_ = (List<Salary>) valueOperations.get("salary_");
        if(CollectionUtils.isEmpty(salary_)){
            salary_=salaryService.list();
            valueOperations.set("salary_",salary_);
        }
        return salary_;
    }

    @ApiOperation("获取员工的工资帐套")
    @GetMapping("/")
    public RespPageBean getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "10") Integer size){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        RespPageBean employee_salary = (RespPageBean) valueOperations.get("employee_salary");
        if(CollectionUtils.isEmpty((Collection<RespPageBean>) employee_salary)){
            employee_salary= employeeService.getEmployeeWithSalary(currentPage,size);
            valueOperations.set("employee_salary",employee_salary);
        }
        return employee_salary;

    }

    @ApiOperation("更新员工帐套")
    @PutMapping("/")
    public RespBean updateSalarySob(Integer eid,Integer sid){
        if (employeeService.update(new UpdateWrapper<Employee>().set("salaryId",sid).eq("id",eid))){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
