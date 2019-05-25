package com.xsl.user.vo;

import java.io.Serializable;

public class ResBaseVo implements Serializable {
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

	// 补充数据
    private Object exParam;

    public static ResBaseVo build(Integer status, String msg, Object exParam) {
        return new ResBaseVo(status, msg, exParam);
    }

    public static ResBaseVo ok(Object exParam) {
        return new ResBaseVo(exParam);
    }

    public static ResBaseVo ok() {
        return new ResBaseVo(null);
    }

    public static ResBaseVo build(Integer status, String msg) {
        return new ResBaseVo(status, msg, null);
    }

    public ResBaseVo(Integer status, String msg, Object exParam) {
        this.status = status;
        this.msg = msg;
        this.exParam = exParam;
    }

    public ResBaseVo(Object exParam) {
        this.status = 200;
        this.msg = "OK";
        this.exParam = exParam;
    }

    public ResBaseVo() {
        this.status = 200;
        this.msg = "OK";
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getExParam() {
        return exParam;
    }

    public void setExParam(Object exParam) {
        this.exParam = exParam;
    }
}
