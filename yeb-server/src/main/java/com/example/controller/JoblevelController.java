package com.example.controller;


import com.example.pojo.Joblevel;
import com.example.pojo.RespBean;
import com.example.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {
    @Autowired
    private IJoblevelService joblevelService;
    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("查询所有职称")
    @GetMapping("/")
    public List<Joblevel> allJoblevels(){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        List<Joblevel> joblevel_ = (List<Joblevel>) valueOperations.get("joblevel_");
        if(CollectionUtils.isEmpty(joblevel_)){
            joblevel_=joblevelService.list();
            valueOperations.set("joblevel_",joblevel_);
        }
        return joblevel_;
    }

    @ApiOperation("增加一个职称")
    @PostMapping("/")
    public RespBean addJoblevel(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        if(joblevelService.save(joblevel)){
            return RespBean.success("增加成功");
        }
        return RespBean.error("增加失败，请重试");
    }

    @ApiOperation("更新一个职称")
    @PutMapping("/")
    public RespBean updateJoblevel(Joblevel joblevel){
        if(joblevelService.updateById(joblevel)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败，请重试");
    }

    @ApiOperation("删除一个职称")
    @DeleteMapping("/{id}")
    public RespBean deleteJoblevel(@PathVariable Integer id){
        if(joblevelService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败，请重试");
    }

    @ApiOperation("删除多个职称")
    @DeleteMapping("/")
    public RespBean deleteJoblevels(Integer[] ids){
        if(joblevelService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("批量删除成功");
        }
        return RespBean.error("删除失败，请重试");
    }

}
