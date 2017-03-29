package com.hull.test.javabase.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Base64;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by hull on 2017/3/29.
 */
public class SelectorTest {
    public static int port = 8888;
    public static Selector selector;
    public static ByteBuffer buffer;
    public static Base64.Decoder decoder;
    public static Base64.Encoder encoder;
    public static String name;

    /*
 * 注册事件
 * */
    protected Selector getSelector() throws IOException {
        // 创建Selector对象
        Selector sel = Selector.open();

        // 创建可选择通道，并配置为非阻塞模式
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);

        // 绑定通道到指定端口
        ServerSocket socket = server.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        socket.bind(address);

        // 向Selector中注册感兴趣的事件
        server.register(sel, SelectionKey.OP_ACCEPT);
        return sel;
    }

    /*
 * 开始监听
 * */
    public void listen() {
        System.out.println("listen on " + port);
        try {
            while(true) {
                // 该调用会阻塞，直到至少有一个事件发生
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = (SelectionKey) iter.next();
                    iter.remove();
                    process(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
 * 根据不同的事件做处理
 * */
    protected void process(SelectionKey key) throws IOException{
        // 接收请求
        if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel channel = server.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        }
        // 读信息
        else if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            int count = channel.read(buffer);
            if (count > 0) {
                buffer.flip();
//                CharBuffer charBuffer = decoder.decode(buffer);
                ByteBuffer charBuffer = decoder.decode(buffer);
                name = charBuffer.toString();
                SelectionKey sKey = channel.register(selector, SelectionKey.OP_WRITE);
                sKey.attach(name);
            } else {
                channel.close();
            }
            buffer.clear();
        }
        // 写事件
        else if (key.isWritable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            String name = (String) key.attachment();

//            ByteBuffer block = encoder.encode(CharBuffer.wrap("Hello " + name));
            ByteBuffer block = encoder.encode(ByteBuffer.wrap(("Hello " + name).getBytes()));
            if(block != null)
            {
                channel.write(block);
            }
            else
            {
                channel.close();
            }

        }
    }
}
