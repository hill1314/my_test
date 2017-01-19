package com.hull.test.autoMethod.camel;

/**
 * Created by hull on 2017/1/19.
 */
public enum ExceptionMsgPath {
    /**
     * @Fields FRM_EXCEPTION_MSG : 异常信息{name[],path[]}
     */
    FRM_EXCEPTION_MSG(new String[]{"frm_exception_msg","app_exception_msg"},new String[]{"META-INF/errors_udmp.properties","META-INF/errors_app.properties"});

    private String[] name;
    private String[] path;

    /**
     * <p>Title:构造方法 </p>
     * <p>Description: 异常路径 </p>
     * @param name 异常名
     * @param path 路径
     */
    ExceptionMsgPath(String[] name, String[] path) {
        this.name = name;
        this.path = path;
    }

    @Override
    public String toString() {
        String str = "";
        for(int i=0;i<name.length;i++){
            str+=name[i] + ": " + path[i]+" ,";
        }
        return str;
    }

    public String[] getPath() {
        return path;
    }

    public String[] getName() {
        return name;
    }
}
