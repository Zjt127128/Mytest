package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;


    //成功返回
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }

    //成功返回
    public static RespBean success(String message,Object obj){
        return new RespBean(200,message,obj);
    }

    //失败返回结果
    public static RespBean error(String message){
        return  new RespBean(500,message,null);
    }

    //失败返回结果
    public static RespBean error(String message,Object obj){
        return new RespBean(500,message,obj);
    }


}
