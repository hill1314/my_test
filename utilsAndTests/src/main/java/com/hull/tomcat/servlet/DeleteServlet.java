package com.hull.tomcat.servlet;

import com.hull.tomcat.http.GPRequest;
import com.hull.tomcat.http.GPResponse;
import com.hull.tomcat.http.GPServlet;

/**
 * Created by Administrator on 2017/4/30.
 */
public class DeleteServlet extends GPServlet{
    @Override
    public void doGet(GPRequest request, GPResponse response) throws Exception{
        response.wirte("del servlet doGet ");
    }

    @Override
    public void doPost(GPRequest request, GPResponse response) throws Exception{
        response.wirte("del servlet doPost ");
    }
}
