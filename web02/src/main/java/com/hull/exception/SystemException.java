package com.hull.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class SystemException extends RuntimeException implements JCFThrowable{

    /**
     * 序列ID
     */
    private static final long serialVersionUID = 8097165605262344967L;

    /**
     * 错误编码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 错误模块
     */
    private int modelCode;

    /**
     * 系统遗异常构建
     *
     * @param modelCode  模块编码
     * @param t          异常源信息
     * @param errCode    错误代码
     * @param errMsgPara 错误消息参数
     */
    public SystemException(int modelCode, Throwable t, String errCode, String... errMsgPara) {
        super(t);
        String errorMessage = ExceptionConfig.mm(modelCode, errCode, errMsgPara);
        this.errCode = errCode;
        this.errMsg = errorMessage;
        this.modelCode = modelCode;
    }

    /**
     * 系统异常
     *
     * @param errCd      错误代码
     * @param errMsgPara 错误信息
     * @param t          异常源
     */
    @Deprecated
    public SystemException(Throwable t, String errCd, String... errMsgPara) {
        this(0, t, errCd, errMsgPara);
    }

    /**
     * 系统异常构造函数
     *
     * @param errCd 错误信息
     */
    @Deprecated
    public SystemException(String errCd) {
        this(0, null, errCd, null);
    }

    /**
     * 系统异常构造函数
     *
     * @param modelCode 模块代码
     * @param errCd     错误信息
     */
    public SystemException(int modelCode, String errCd) {
        this(modelCode, null, errCd, null);
    }

    /**
     * 系统异常构造函数
     *
     * @param errCd 错误信息
     * @param t     异常源
     */
    @Deprecated
    public SystemException(Throwable t, String errCd) {
        this(0, t, errCd, null);
    }

    /**
     * 系统异常构造函数
     *
     * @param modelCode 模块代码
     * @param errCd     错误信息
     * @param t         异常源
     */
    public SystemException(int modelCode, Throwable t, String errCd) {
        this(modelCode, t, errCd, null);
    }

    /**
     * 系统异常构造函数
     *
     * @param errCd      错误编码
     * @param errMsgPara 错误参数
     */
    @Deprecated
    public SystemException(String errCd, String... errMsgPara) {
        this(0, null, errCd, errMsgPara);
    }

    /**
     * 系统异常构造函数
     *
     * @param modelCode  错误模块
     * @param errCd      错误信息
     * @param errMsgPara 异常源
     */
    public SystemException(int modelCode, String errCd, String... errMsgPara) {
        this(modelCode, null, errCd, errMsgPara);
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this <tt>Throwable</tt> instance
     *         (which may be <tt>null</tt>).
     */
    @Override
    public String getMessage() {
        return ExceptionConfig.getModelName(modelCode) + "-" + errCode + ":" + errMsg;
    }

    /**
     * 获取堆栈信息
     * @return
     */
    public final String getBackStacks() {
        if (this.getCause() == null) {
            return "";
        } else {
            return getStackTrace(this.getCause());
        }
    }

    /**
     * 输出异常堆栈信息
     * @param e 异常
     * @return 堆栈信息
     */
    public static final String getStackTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);
        e.printStackTrace(out);
        return sw.toString();
    }

    /**
     * 获取错误编码
     *
     * @return 错误编码
     */
    @Override
    public String getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrDtlMsg() {
        return getMessage();
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
