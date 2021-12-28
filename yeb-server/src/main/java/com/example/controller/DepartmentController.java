package com.example.controller;


import com.example.pojo.Department;
import com.example.pojo.RespBean;
import com.example.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangjintao
 * @since 2021-12-13
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartment(){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        List<Department> department = (List<Department>) valueOperations.get("department_");
        if(CollectionUtils.isEmpty(department)){
            department=departmentService.getAllDepartment();
            valueOperations.set("department_",department);
        }
        return department;
    }

    @ApiOperation("添加一个部门")
    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }

    @ApiOperation("删除一个部门")
    @DeleteMapping("/{id}")
    public RespBean deleteDepartment(@PathVariable Integer id){
        return departmentService.deleteDepartment(id);
    }

}
