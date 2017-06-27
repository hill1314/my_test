package com.hull.test.socketTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/6/18.
 */
public class GPServer {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("服务启动成功！");

            while(true){
                Socket socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                byte[] b = new byte[1024];
                int len = is.read(b);
                if(len !=-1){   //-1 表示没收到时间
                    System.out.println(new String(b) );
                }

                OutputStream os = socket.getOutputStream();
                os.write("hello !".getBytes());
                os.close();
                is.close();
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
