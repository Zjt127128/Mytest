package com.example.controller;


import com.example.pojo.Position;
import com.example.pojo.RespBean;
import com.example.service.IPositionService;
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
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    private IPositionService positionService;
    @Autowired
    private RedisTemplate redisTemplate;
    @ApiOperation("获取职位信息")
    @GetMapping("/")
    public List<Position> getPosition(){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        List<Position> position_ = (List<Position>) valueOperations.get("position_");
        if(CollectionUtils.isEmpty(position_)){
            position_=positionService.list();
            valueOperations.set("position_",position_);
        }
        return position_;
    }

    @ApiOperation("增加职位信息")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());
        if(positionService.save(position)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation("更新职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        if(positionService.updateById(position)){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation("删除职位信息")
    @DeleteMapping ("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        if(positionService.removeById(id)){
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation("批量删除职位信息")
    @DeleteMapping("/")
    public RespBean deletePositions(Integer[] ids){
        if(positionService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("批量删除成功");
        }
        return RespBean.error("批量删除失败");
    }
}
