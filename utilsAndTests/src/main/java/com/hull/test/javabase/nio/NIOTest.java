package com.hull.test.javabase.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by hull on 2017/3/27.
 * 参考 http://blog.csdn.net/wuxianglong/article/details/6604817
 */
public class NIOTest {
    public static void main(String[] args) {
        try {
//            readProgram();
//            writeProgram();
//            subBuffer();
            directBuffer();
//            readOnlyBuffer();
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


    /**
     * 子缓存区
     */
    public static void subBuffer(){
        ByteBuffer buffer = ByteBuffer.allocate( 10 );

        // 缓冲区中的数据0-9
        for (int i=0; i<buffer.capacity(); ++i) {
            buffer.put( (byte)i );
        }

        // 创建子缓冲区
        buffer.position( 3 );
        buffer.limit( 7 );
        ByteBuffer slice = buffer.slice();

        // 改变子缓冲区的内容
        for (int i=0; i<slice.capacity(); ++i) {
            byte b = slice.get( i );
            b *= 10;
            slice.put( i, b );
        }

        buffer.position( 0 );
        buffer.limit( buffer.capacity() );

        while (buffer.remaining()>0) {
            System.out.println( buffer.get() );
        }
    }

    /**
     *  只读缓存区
     */
    public static void readOnlyBuffer(){
        ByteBuffer buffer = ByteBuffer.allocate( 10 );

        // 缓冲区中的数据0-9
        for (int i=0; i<buffer.capacity(); ++i) {
            buffer.put( (byte)i );
        }

        // 创建只读缓冲区
        ByteBuffer readonly = buffer.asReadOnlyBuffer();

        // 改变原缓冲区的内容
        for (int i=0; i<buffer.capacity(); ++i) {
            byte b = buffer.get( i );
            b *= 10;
            buffer.put( i, b );
        }

        readonly.position(0);
        readonly.limit(buffer.capacity());

        // 只读缓冲区的内容也随之改变
        while (readonly.remaining()>0) {
            System.out.println( readonly.get());
        }
    }


    /**
     * 直接缓存区
     */
    public static void directBuffer() throws Exception {
        String infile = "d:\\test.txt";
        FileInputStream fin = new FileInputStream( infile );
        FileChannel fcin = fin.getChannel();

        String outfile = String.format("d:\\testcopy.txt");
        FileOutputStream fout = new FileOutputStream( outfile );
        FileChannel fcout = fout.getChannel();

        // 使用allocateDirect，而不是allocate
        ByteBuffer buffer = ByteBuffer.allocateDirect( 1024 );
        while (true) {
            buffer.clear();
            int r = fcin.read( buffer );
            if (r==-1) {
                break;
            }
            buffer.flip();
            fcout.write( buffer );
        }
    }

    /**
     * 内存映射文件I/O
     * @throws Exception
     */
    public static void iOBuffer() throws Exception {
        RandomAccessFile raf = new RandomAccessFile( "c:\\test.txt", "rw" );
        FileChannel fc = raf.getChannel();

        MappedByteBuffer mbb = fc.map( FileChannel.MapMode.READ_WRITE, 0, 1024 );

        mbb.put( 0, (byte)97 );
        mbb.put( 1023, (byte)122 );

        raf.close();
    }



}
