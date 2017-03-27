package com.hull.test.javabase.serializiableTest;

import java.io.*;

/**
 * Created by hull on 2017/3/3.
 *
 * 序列化的目的：便于将对象 转换为二进制数据流，存放于物理硬盘，或在网络上传输
 */
public class UseStudent {
    public static void main(String[] args) {

        //序列化
//        seriaObject();

        //反序列化
        reSeriaObject();

    }

    /**
     * 序列化
     */
    public static void seriaObject(){
        Student student = new Student("Lily",18);
        File file = new File("D:/student2.txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //序列化
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(student);
            oos.flush();
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reSeriaObject(){
        File file = new File("D:/student2.txt");
        try{
            //Student对象反序列化过程
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Student st1 = (Student) ois.readObject();
            System.out.println("name = " + st1.getName());
            System.out.println("age = " + st1.getAge());
            ois.close();
            fis.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
