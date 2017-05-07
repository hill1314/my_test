package com.hull.test.javabase.nio.gupao;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2017/5/6.
 */
public class NioServer {
    private int port;
    private Selector selector;
    private ByteBuffer buffer=ByteBuffer.allocate(1024);
    
    public NioServer(int port){
        this.port=port;
        init();
    }
    public NioServer(){
        this(8080);
    }

    private void init(){
        try {
        	//先创建通道(修路)
			ServerSocketChannel channel = ServerSocketChannel.open();
			//设置为非阻塞
			channel.configureBlocking(false);
			
			//绑定地址（收费站）
			InetSocketAddress address = new InetSocketAddress(this.port);
			channel.bind(address);
			
			//轮询机制,开启一个selector （开门）
			this.selector = Selector.open();
			
			//准备接客
			channel.register(selector, SelectionKey.OP_ACCEPT);
			
        } catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void start(){
    	System.out.println("NIO server start!port="+this.port);
    	try {
    	//轮询，叫号系统中取过号的人
	       while(true){
	    	   int count = selector.select();//获取排队数
	    	   
	    	   //没有的话，继续
	    	   if(count==0) continue;
	    	   System.out.println("count:"+count);
	    	   
	    	   //从叫号系统取出一个号码牌
	    	   Set<SelectionKey> keys = selector.selectedKeys();
	    	   Iterator<SelectionKey> i = keys.iterator();
	    	   
	    	   while(i.hasNext()){
	    		   SelectionKey key = i.next();
	    		   //进行处理
	    		   process(key);
	    		   //处理完后，移除
	    		   i.remove();
	    	   }
	       }
    	} catch (IOException e) {
			e.printStackTrace();
		} 
    }
    
	private void process(SelectionKey key) throws IOException {
		
		//读写数据
		//判断数据是否已准备就绪
		if(key.isAcceptable()){
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			SocketChannel client = server.accept();
			client.configureBlocking(false);
			client.register(this.selector, SelectionKey.OP_READ);
		}else if(key.isReadable()){
			SocketChannel client = (SocketChannel) key.channel();
			int len =client.read(buffer);
			if(len>0){
				buffer.flip();
				String input = new String(buffer.array(),0,len);
				System.out.println("server process input:"+input);
				client.register(selector, SelectionKey.OP_WRITE,input);
			}
		}else if(key.isWritable()){
			SocketChannel client = (SocketChannel) key.channel();
			String output = (String) key.attachment();
			System.out.println("server process output:"+output);
			client.write(ByteBuffer.wrap(("output:"+output).getBytes()));
		}
//		client.close();
	}
    
    public static void main(String[] args) {
    	NioServer server = new NioServer();
    	server.start();
	}


}
