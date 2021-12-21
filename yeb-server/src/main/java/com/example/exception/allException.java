package com.example.exception;

import com.example.pojo.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class allException {
    @ExceptionHandler(SQLException.class)
    public RespBean mySQLException(SQLException sqlException){
        if(sqlException instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("该字段与其他表关联，无法删除！");
        }
        return RespBean.error("数据库异常，请注意！");
    }

}
