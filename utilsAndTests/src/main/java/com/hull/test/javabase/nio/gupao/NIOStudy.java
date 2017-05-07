package com.hull.test.javabase.nio.gupao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017/5/6.
 */
public class NIOStudy {

    public static void main(String[] args) {

    }

    /**
     * 传统BIO 操作
     * @throws IOException
     */
    public void bio() throws IOException {
        ServerSocket server = new ServerSocket(8181);
        Socket client = new Socket();
        client.getInputStream();
        client.getOutputStream();
    }

}
