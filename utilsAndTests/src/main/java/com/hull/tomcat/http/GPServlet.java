package com.hull.tomcat.http;

/**
 * Created by Administrator on 2017/4/30.
 */
public abstract class GPServlet {

    public void service(GPRequest request,GPResponse response) throws Exception{
        if("GET".equalsIgnoreCase(request.getMethod())){
            doGet(request,response);
        }else if("POST".equalsIgnoreCase(request.getMethod())){
            doPost(request,response);
        }
    }

    public abstract void doGet(GPRequest request,GPResponse response) throws Exception;

    public abstract void doPost(GPRequest request,GPResponse response) throws Exception;
}
