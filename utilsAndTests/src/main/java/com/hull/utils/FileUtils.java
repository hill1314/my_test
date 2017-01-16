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

    /**
     * A方法追加文件：使用RandomAccessFile
     */
    public static void appendMethodA(String fileName, String content) {
        RandomAccessFile randomFile = null;
        try {
            // 获取是项目的绝对路径
            File newFile = new File(fileName);
            if(!newFile.exists())
            {
                newFile.createNewFile();
            }
            // 打开一个随机访问文件流，按读写方式
            content = new String(content.getBytes("UTF-8"),"ISO8859_1");
            randomFile = new RandomAccessFile(newFile, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.writeBytes("\n");
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(randomFile!=null){
                try {
                    randomFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
