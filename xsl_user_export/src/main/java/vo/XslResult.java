package vo;



/**
 * @version V1.0
 * @ClassName:XslResult
 * @Description:悬赏令自定义响应结构
 * @author:何林鸿
 * @date: 2018年7月29日
 */
public class XslResult {


    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static XslResult build(Integer status, String msg, Object data) {
        return new XslResult(status, msg, data);
    }

    public static XslResult ok(Object data) {
        return new XslResult(data);
    }

    public static XslResult ok() {
        return new XslResult(null);
    }

    public XslResult() {

    }

    public static XslResult build(Integer status, String msg) {
        return new XslResult(status, msg, null);
    }

    public XslResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public XslResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



}
