package com.hull.test.autoMethod.camel;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by hull on 2017/1/19.
 */
public class FrameworkException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** 异常编码 */
    private String errCode;

    /**
     * @param errCode 错误信息
     */
    public FrameworkException(String errCode) {
        super(ExceptionMessageHelper.getExMsg(errCode));
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     *
     * @param cause 异常来源
     */
    public FrameworkException(Throwable cause) {
        super("无自定义消息异常 : " + cause.getMessage(), cause);
    }

    /**
     * 构造一个异常信息
     *
     * @param errCode 异常代码
     * @param message 异常信息
     */
    public FrameworkException(String errCode, String message) {
        super(ExceptionMessageHelper.getExMsg(errCode) + ":" + message);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     *
     * @param errCode 异常代码
     * @param cause 异常来源
     */
    public FrameworkException(String errCode, Throwable cause) {
        super(ExceptionMessageHelper.getExMsg(errCode), cause);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     *
     * @param errCode 异常代码
     * @param params 要替换到异常常量信息中的自定义消息
     */
    public FrameworkException(String errCode, Object[] params) {
        super(ExceptionMessageHelper.getExMsg(errCode, params));
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
    public FrameworkException(String errCode, String message, Throwable cause) {
        super(ExceptionMessageHelper.getExMsg(errCode) + ":" + message, cause);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     *
     * @param errCode 异常代码
     * @param params 要替换到异常常量信息中的自定义消息
     * @param cause 异常来源
     */
    public FrameworkException(String errCode, Object[] params, Throwable cause) {
        super(ExceptionMessageHelper.getExMsg(errCode, params), cause);
        this.errCode = errCode;
        this.getMessage();
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }

    /**
     * @description 获取本地异常描述
     * @version
     * @title
     * @see java.lang.Throwable#toString()
     * @return  本地异常描述
     */
    public String toString() {

        String s = getClass().getName();
        String message = getLocalizedMessage();
        if (null != getCause() && getCause() instanceof FrameworkException) {
            String causeMessage = getCause().getLocalizedMessage();
            if (causeMessage == null) {
                causeMessage = "";
            }
            return (message != null) ? message : "" + causeMessage;
        }
        return (message != null) ? (s + ": " + message) : s;
    }

    /**
     * @description 打印异常堆栈
     * @version
     * @title
     * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
     * @param s PrintStream输入流
     */
    public void printStackTrace(PrintStream s) {
        synchronized (s) {
            if (null != getCause() && getCause() instanceof FrameworkException) {
                s.println(this);
                return;
            }
        }
        super.printStackTrace(s);
    }

    /**
     * @description 打印异常堆栈
     * @version
     * @title
     * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
     * @param s  PrintWriter
     */
    public void printStackTrace(PrintWriter s) {
        synchronized (s) {
            if (null != getCause() && getCause() instanceof FrameworkException) {
                s.println(this);
                return;
            }
        }
        super.printStackTrace(s);
    }

    /**
     * @description 获取异常信息
     * @version
     * @title
     * @see java.lang.Throwable#getMessage()
     * @return 异常信息
     */
    public String getMessage() {
        String causeMessage = "";
        if (null != getCause()) {
            causeMessage = getCause().getLocalizedMessage();
        }
        String messge = super.getMessage();
        if (null == messge) {
            messge = causeMessage;
        } else {
            if (null != causeMessage && !"".equals(causeMessage.trim())) {
                messge = messge + "-" + causeMessage;
            }
        }
        return messge;
    }
}
