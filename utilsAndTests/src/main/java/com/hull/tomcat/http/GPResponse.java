package com.hull.tomcat.http;

import java.io.OutputStream;

/**
 * Created by Administrator on 2017/4/30.
 */
public class GPResponse {

    private OutputStream out;

    public GPResponse(OutputStream out){
        this.out = out;
    }

    public void wirte(String content) throws Exception{
        out.write(content.getBytes());
        out.flush();
    }
}
