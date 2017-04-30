package com.hull.tomcat.servlet;

import com.hull.tomcat.http.GPRequest;
import com.hull.tomcat.http.GPResponse;
import com.hull.tomcat.http.GPServlet;

/**
 * Created by Administrator on 2017/4/30.
 */
public class AddServlet extends GPServlet{
    @Override
    public void doGet(GPRequest request, GPResponse response) throws Exception{
        response.wirte("add servlet doGet ");
    }

    @Override
    public void doPost(GPRequest request, GPResponse response) throws Exception{
        response.wirte("add servlet doPost ");
    }
}
