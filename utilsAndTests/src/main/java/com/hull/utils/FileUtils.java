package com.hull.utils;

import java.io.*;

/**
 * Created by Administrator on 2016/12/5.
 */
public class FileUtils {

    /**
     * 读文件内容
     * @param file
     * @return
     * @throws Exception
     */
    public static String readFile(String file) throws Exception {
        StringBuffer buffer = new StringBuffer();
        FileInputStream f = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(f));
        String line = reader.readLine();
        while (line!=null){
            buffer.append(line);
            line = reader.readLine();
        }
        String content = new String(buffer.toString().getBytes("iso-8859-1"),"utf-8");
        f.close();
        reader.close();
        return  content;
    }

    /**
     * 写文件
     * @param file
     * @param content
     * @throws Exception
     */
    public static void writeFile(String file,String content) throws Exception {
        FileOutputStream f = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(f));
        f.write(content.getBytes());
        f.close();
        writer.close();
    }

    public static void main(String[] args){
        try {
//            String str = readFile("F:\\test.txt");
//            System.out.print(str);
            writeFile("F:\\test1.txt","啊啊啊啊");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
