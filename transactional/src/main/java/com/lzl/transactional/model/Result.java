package com.lzl.transactional.model;

/**
 * <p>
 * <b>创建日期：</b> 2021/10/9
 * </p>
 *
 * @author liaozhilong
 */
public class Result<T> {
    public static final int SUCCESS_STATUS = 0;
    public static final int UNKNOWN_EXCEPTION_STATUS = -1;
    public static final int ILLEGAL_ARGUMENT_STATUS = -2;
    public static final Result<Boolean> SUCCESS = new Result(0, "ok", true);
    public static final Result<Boolean> FAILURE = new Result(-1, "failure", false);

    private int status = 0;

    private String message = "ok";

    private T data;

    public Result() {}

    public Result(T data) {
        this.data = data;
    }

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return this.getStatus() == 0;
    }

    public String toString() {
        return "DSFResult [status=" + this.status + ", message=" + this.message + ", data=" + this.data + "]";
    }

}
