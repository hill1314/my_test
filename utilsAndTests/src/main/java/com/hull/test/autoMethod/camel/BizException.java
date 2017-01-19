package com.hull.test.autoMethod.camel;

/**
 * Created by hull on 2017/1/19.
 */
public class BizException extends FrameworkException{
    /** UUID */
    private static final long serialVersionUID = 1L;

    /** 异常编码 */
    private String errCode;

    /**
     * 构造一个异常信息
     *
     * @param errCode String错误信息
     */
    public BizException(String errCode) {
        super(errCode);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     *
     * @param errCode 异常代码
     * @param message 异常信息
     */
    public BizException(String errCode, String message) {
        super(errCode,message);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     *
     * @param errCode 异常代码
     * @param cause 异常来源
     */
    public BizException(String errCode, Throwable cause) {
        super(errCode, cause);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     *
     * @param errCode 异常代码
     * @param params 要替换到异常常量信息中的自定义消息
     */
    public BizException(String errCode, Object[] params) {
        super(errCode, params);
        this.errCode = errCode;
        this.getMessage();
    }

    /**
     * 构造一个异常信息
     *
     * @param errCode 异常代码
     * @param message 异常信息
     * @param cause 异常来源
     */
    public BizException(String errCode, String message, Throwable cause) {
        super(errCode, message, cause);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     *
     * @param errCode 异常代码
     * @param params 要替换到异常常量信息中的自定义消息
     * @param cause 异常来源
     */
    public BizException(String errCode, Object[] params, Throwable cause) {
        super(errCode, params, cause);
        this.errCode = errCode;
        this.getMessage();
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
