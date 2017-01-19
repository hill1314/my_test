/**
 * GIT Confidential
 * Licensed Materials - Property of GIT
 * Copyright (c) 1998-2016 Global InfoTech Corp. All Rights Reserved.
 * Global InfoTech, Inc. owns the copyright and other intellectual
 * property rights in this software.
 *
 * The source code for this program is not published.
 * Duplication or use of the Software is not permitted
 * except as expressly provided in a written agreement
 * between your company and Global InfoTech, Inc.
 */

package com.hull.test.autoMethod.cfg;

/** 
 * @description 
 * @author git_man git_man@git.com.cn 
 * @date 2016年9月1日 下午2:28:23  
*/
public class ParamCfgModel {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量含义) 
     */ 
     
     private static final long serialVersionUID = 1L;

     private String stgCod;//序列号

     private String execNum;//方法执行序号

     private String methodName;//方法名

     private String methodRmk;//方法备注

     private String paramNum;//参数序号

     private String paramTyp;//参数类型

     private String paramName;//参数名称

     private String pmCfCod;//参数来源标识

     private String className;//接口类名
     
     private String comments;//备注
     
     private String tableName;//查询的表名

     public ParamCfgModel(){
         
     }
     
     public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getStgCod() {
         return stgCod;
     }
     
     public void setStgCod(String stgCod) {
         this.stgCod = stgCod;
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



    public String getExecNum() {
        return execNum;
    }

    public void setExecNum(String execNum) {
        this.execNum = execNum;
    }

    public String getParamNum() {
        return paramNum;
    }

    public void setParamNum(String paramNum) {
        this.paramNum = paramNum;
    }

    public String getParamTyp() {
         return paramTyp;
     }

     public void setParamTyp(String paramTyp) {
         this.paramTyp = paramTyp;
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

     public String getComments() {
         return comments;
     }

     public void setComments(String comments) {
         this.comments = comments;
     }
}
