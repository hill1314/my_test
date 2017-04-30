package com.hull.tomcat.catalina;

import com.hull.tomcat.http.GPRequest;
import com.hull.tomcat.http.GPResponse;
import com.hull.tomcat.http.GPServlet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/4/30.
 * 手工实现tomcat
 * http://www.toutiao.com/i6408896689888821761/
 */
public class GPTomcat {
    private int port = 8080;
    private Properties webxml = new Properties();
    private Map<Pattern,Class> servletMap = new HashMap<>();


    public GPTomcat(){

    }

    public GPTomcat(int port){
        this.port = port;
    }

    public void start(){
        try {
            String path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\hull\\tomcat\\";
            FileInputStream fis = new FileInputStream(path+"web.properties");
            webxml.load(fis);
            fis.close();

            for(Object k:webxml.keySet()){
                String key = k.toString();
                if(key.endsWith(".url")){
                    String url = webxml.get(key).toString();
                    String className = webxml.get(key.replace(".url",".class")).toString();
                    Class sClass = Class.forName(className);
                    Pattern pattern = Pattern.compile(url.replaceAll("\\.","\\\\.").replaceAll("\\*",".*"));
                    this.servletMap.put(pattern,sClass);
                }
            }

            ServerSocket server = new ServerSocket(this.port);
            System.out.println("咕泡 tomcat start,port = "+this.port);

            while(true){
                Socket socket = server.accept(); //阻塞
                //Servlet  和 Request\ response
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();

                GPRequest request = new GPRequest(in);
                GPResponse response = new GPResponse(out);

                String url = request.getUrl();

                try {
                    boolean isPattern = false;
                    for(Map.Entry<Pattern,Class> entry : servletMap.entrySet()){
                        if(entry.getKey().matcher(url).matches()){
                            GPServlet servlet =  (GPServlet)entry.getValue().newInstance();
                            servlet.service(request,response);
                            isPattern = true;
                            break;
                        }
                    }
                    if(!isPattern){
                        response.wirte("404 Not Found "+url);
                    }
                }catch (Exception e){
                    response.wirte("500  error!" + url + "\n"+ e.getMessage() );
                }

//                socket.getOutputStream().write("Hello world".getBytes());
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GPTomcat().start();
    }
}
