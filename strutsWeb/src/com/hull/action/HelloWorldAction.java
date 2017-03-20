package com.hull.action;

/**
 * Created by Administrator on 2017/3/20.
 */
public class HelloWorldAction {
    private String msg;

    public String getMessage() {
        return msg;
    }

    public String execute(){
        msg = "我的第一个Struts应用";
        return "success";
    }

}
