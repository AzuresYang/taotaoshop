package com.tao.entity;

import java.util.*;


/**
 * Created by 28029 on 2018/3/26.
 */
public class ResponseResult {
    int status;
    String msg;
     Object data;


    public ResponseResult(){};
    ResponseResult(int status, String msg, Object data)
    {
        this.status = status;
        this.msg = msg;
        this.data = data;

    }

    public static ResponseResult ok(Object obj)
    {
        return new ResponseResult(200,"ok", obj);
    }
    public static ResponseResult ok()
    {
        return new ResponseResult(200,"ok",null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
