package com.hull.test.javabase.serializiableTest;

import java.io.Serializable;

/**
 * Created by hull on 2017/3/3.
 */
public class Student implements Serializable{

    // 如果不手动添加，貌似会自动生成一个serialVersionUID保存到序列化文件中！！！
    private static final long serialVersionUID = 1L;

    private String name ;
    private int age;
    private boolean sex;

    public Student(){

    }

    public Student(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
