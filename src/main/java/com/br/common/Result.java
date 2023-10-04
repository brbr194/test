package com.br.common;

public class Result {

    private static final String SUCCESS = "0";
    private static final String ERROR = "-1";

    private String code;//状态码
    private String msg;//错误信息
    private Object data;//数据

    public Result() {
    }

    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 获取
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }

    public String toString() {
        return "Result{code = " + code + ", msg = " + msg + ", data = " + data + "}";
    }

    public static Result success(){//成功
        Result result = new Result();
        result.setCode(SUCCESS);
        return result;
    }

    public static Result success(Object data){//成功且传递信息
        Result result = new Result();
        result.setCode(SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result error(String msg) {//错误且返回信息
        Result result = new Result();
        result.setCode(ERROR);
        result.setMsg(msg);
        return result;
    }

}
