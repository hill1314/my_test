package com.hull.test.javabase.nio.gupao;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class BioClient {

	public static void main(String[] args) {
		try {
			Socket client = new Socket("127.0.0.1",8080);
//			Socket client = new Socket("localhost",8080);
			OutputStream out = client.getOutputStream();
			out.write("hull".getBytes());
			out.flush();
			
			InputStream in = client.getInputStream();
			byte[] buff = new byte[1024];
			int len = 0;
			if((len = in.read())>0){
				System.out.println(new String(buff,0,len));
			}
			
//			client.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
