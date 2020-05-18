package fun.huanghai.guifan.vo;

public class ResultVO<T> {

    /**
     * 状态码,比如1000代表响应成功
     */
    private int code;

    /**
     * 响应信息,用来说明响应情况
     */
    private String msg;

    /**
     * 响应具体数据
     */
    private T data;

    public ResultVO(T data) {
        this(ResultCode.SUCCESS,data);
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
