package vo;

import java.io.Serializable;

public class ResBaseVo implements Serializable {
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

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

    public static ResBaseVo build(Integer status, String msg, Object data) {
        return new ResBaseVo(status, msg);
    }

    public static ResBaseVo ok(Object data) {
        return new ResBaseVo(data);
    }

    public static ResBaseVo ok() {
        return new ResBaseVo(null);
    }

    public ResBaseVo() {

    }

    public static ResBaseVo build(Integer status, String msg) {
        return new ResBaseVo(status, msg);
    }

    public ResBaseVo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResBaseVo(Object data) {
        this.status = 200;
        this.msg = "OK";
    }

    public Boolean isOK() {
        return this.status == 200;
    }


}
