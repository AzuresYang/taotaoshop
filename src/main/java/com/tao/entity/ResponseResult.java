package com.tao.entity;

/**
 * Created by 28029 on 2018/3/26.
 */
public class ResponseResult {
    int status;
    String msg;
    String data;


    ResponseResult(int status, String msg, String data)
    {
        this.status = status;
        this.msg = msg;
        this.data = data;

    }

    public static ResponseResult ok(String data)
    {
        return new ResponseResult(200,"ok",data);
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
