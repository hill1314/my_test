package com.hull.tomcat.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/30.
 */
public class GPRequest {
    private String method;
    private String url;
    private InputStream in;
    private String paramStr;
    private Map<String,String> paramMap = new HashMap<>();

    public GPRequest(InputStream in){
        this.in = in;
        try {
            String content = "";
            byte[] buff = new byte[1024];
            int len = 0;
            if((len=in.read(buff))>0){
                content = new String(buff,0,len);
            }
            System.out.println("获取的输入："+content);

            //解析request
            String line = content.split("\n")[0];
            String[] arr = line.split("\\s"); //空格
            //method
            this.method = arr[0];
            //url 和 参数
            String[] urls = arr[1].split("\\?");
            this.url = urls[0];
            if(urls.length>2){
                this.paramStr = urls[1];
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String,String> getParameters(){
        String[] params = this.paramStr.split("&");
        for(int i=0;i<params.length;i++){
            String key = params[i].split("=")[0];
            String value = params[i].split("=")[1];
            this.paramMap.put(key,value);
        }
        return this.paramMap;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
