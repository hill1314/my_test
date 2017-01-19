package com.hull.test.autoMethod.cfg;

/**
 * Created by hull on 2017/1/19.
 */

 /* 配置表结构
    STG_COD	VARCHAR2(50 CHAR)	Y			执行策略标识
    EXEC_NUM	VARCHAR2(2 CHAR)	Y			方法执行序号
    METHOD_NAME	VARCHAR2(50 CHAR)	Y			方法名
    METHOD_RMK	VARCHAR2(200 CHAR)	Y			方法备注
    PARAM_NUM	VARCHAR2(2 CHAR)	Y			参数序号
    PARAM_TYP	VARCHAR2(200)	Y			参数类型
    PARAM_NAME	VARCHAR2(50 CHAR)	Y			参数名称
    PM_CF_COD	VARCHAR2(200 CHAR)	Y			参数来源标识
    CLASS_NAME	VARCHAR2(200 CHAR)	Y			接口类名
    COMMENTS	VARCHAR2(200 CHAR)	Y			备注*/

public class ParamCfg {
     private String stgCod;
     private int exceNum;
     private String methodName;
     private String methodRmk;
     private int paramNum;
     private String paramType;
     private String paramName;
     private String pmCfCod;
     private String className;
     private String comments;

    public String getStgCod() {
        return stgCod;
    }

    public void setStgCod(String stgCod) {
        this.stgCod = stgCod;
    }

    public int getExceNum() {
        return exceNum;
    }

    public void setExceNum(int exceNum) {
        this.exceNum = exceNum;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodRmk() {
        return methodRmk;
    }

    public void setMethodRmk(String methodRmk) {
        this.methodRmk = methodRmk;
    }

    public int getParamNum() {
        return paramNum;
    }

    public void setParamNum(int paramNum) {
        this.paramNum = paramNum;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getPmCfCod() {
        return pmCfCod;
    }

    public void setPmCfCod(String pmCfCod) {
        this.pmCfCod = pmCfCod;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
