package com.hull.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author leilei.hu
 * @create 2017-09-23 下午6:15
 * @desc
 **/
public class NioUtil {

    /**
     * 读取文件内容
     * @param file
     * @throws Exception
     */
    public static String readFile(File file) throws Exception {
        FileInputStream fin = new FileInputStream(file);

        // 获取通道
        FileChannel channel = fin.getChannel();
        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 读取数据到缓冲区
        channel.read(buffer);
        buffer.flip();

        String str = new String(buffer.array(),"utf-8");
        fin.close();
        return str;
    }

    public static void writeFile(File file,String comment) throws Exception {
        FileOutputStream fos = new FileOutputStream(file);
        // 获取通道
        FileChannel channel = fos.getChannel();
        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //写入缓存区
        buffer.put(comment.getBytes());
        buffer.flip();

        //放入通道 之前 要flip (复位)
        channel.write(buffer);

        fos.close();

    }


    public static void main(String[] args) throws Exception {
        //读取
//        String str = readFile(new File("/Users/huleilei/Desktop/settings.xml"));
//        System.out.println(str);

        //写入
        writeFile(new File("/Users/huleilei/Desktop/test.txt"),"fsdfsdfsdsdf");
    }
}
