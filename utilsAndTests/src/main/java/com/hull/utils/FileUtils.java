package com.hull.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * 写文件
     * @param file
     * @param inputStream
     * @throws Exception
     */
    public static void writeFileStream(String file,InputStream inputStream) throws Exception {
        FileOutputStream f = new FileOutputStream(file);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(f));

        byte[] buf = new byte[4096];
        int count = 0 ;
        while((count = inputStream.read(buf))!=-1)
        {
            f.write(buf, 0, count);
        }

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

    /**
     * 获取文件列表
     * @param path
     * @return
     */
    public static File[] listFiles(String path){
        File filePath = new File(path);
        File[] files = null;

        if (filePath.isDirectory()){
            files = filePath.listFiles();
        }
        return files;
    }


    public static void main(String[] args){
        StringBuffer result = new StringBuffer();
        try {

            File[] files = listFiles("/Users/huleilei/Desktop/");
            for(File file:files){
                Date date = new Date(file.lastModified());
                String dateStr = DateUtil.toStringYmdHms(date);
                result.append(file.getName()+"_"+file.length()/1024+"kb_"+dateStr+"\n");
            }

            System.out.println(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
