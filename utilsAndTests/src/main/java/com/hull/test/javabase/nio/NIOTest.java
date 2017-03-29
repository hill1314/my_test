package com.hull.test.javabase.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by hull on 2017/3/27.
 * 参考 http://blog.csdn.net/wuxianglong/article/details/6604817
 */
public class NIOTest {
    public static void main(String[] args) {
        try {
//            readProgram();
            writeProgram();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Buffer 学习
     */
    public static void testIntBuffer(){
        // 分配新的int缓冲区，参数为缓冲区容量
        // 新缓冲区的当前位置将为零，其界限(限制位置)将为其容量。它将具有一个底层实现数组，其数组偏移量将为零。
        IntBuffer buffer = IntBuffer.allocate(8);

        for (int i = 0; i < buffer.capacity(); ++i) {
            int j = 2 * (i + 1);
            // 将给定整数写入此缓冲区的当前位置，当前位置递增
            buffer.put(j);
        }
        // 重设此缓冲区，将限制设置为当前位置，然后将当前位置设置为0
        buffer.flip();
        // 查看在当前位置和限制位置之间是否有元素
        while (buffer.hasRemaining()) {
            // 读取此缓冲区当前位置的整数，然后当前位置递增
            int j = buffer.get();
            System.out.print(j + "  ");
        }
    }

    /**
     * NIO读取数据可以分为下面三个步骤：
     1. 从FileInputStream获取Channel
     2. 创建Buffer
     3. 将数据从Channel读取到Buffer中
     * @throws IOException
     */
    public static void readProgram() throws IOException {
        FileInputStream fin = new FileInputStream("d:\\temp.txt");
        // 获取通道
        FileChannel fc = fin.getChannel();
        // 创建缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 读取数据到缓冲区
        fc.read(buffer);
        buffer.flip();

        while (buffer.remaining()>0) {
            byte b = buffer.get();
            System.out.print(((char)b));
        }

        fin.close();
    }

    /**
     * NIO写入数据与读取数据的过程类似，同样数据不是直接写入通道，而是写入缓冲区，可以分为下面三个步骤：
     1. 从FileInputStream获取Channel
     2. 创建Buffer
     3. 将数据从Channel写入到Buffer中
     */
    public static void writeProgram() throws Exception {
        byte message[] = { 83, 111, 109, 101, 32,98, 121, 116, 101, 115, 46 };
        FileOutputStream fos = new FileOutputStream("d:\\temp.txt");
        FileChannel fc = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        for(int i=0;i<message.length;i++){
            buffer.put(message[i]);
        }
        buffer.flip();
        fc.write(buffer);
        fos.close();
    }


}
