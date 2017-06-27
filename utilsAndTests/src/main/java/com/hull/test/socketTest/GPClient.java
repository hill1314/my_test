package com.hull.test.socketTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2017/6/18.
 */
public class GPClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",6666);
            OutputStream os = socket.getOutputStream();
            os.write("hello world".getBytes());

            InputStream is = socket.getInputStream();
            byte[] b = new byte[1024];
            is.read(b);
            System.out.println("接收："+new String(b));

            os.flush();
            os.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
