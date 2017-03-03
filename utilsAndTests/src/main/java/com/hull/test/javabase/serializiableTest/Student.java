package com.hull.test.javabase.serializiableTest;

import java.io.Serializable;

/**
 * Created by hull on 2017/3/3.
 */
public class Student implements Serializable{
    private String name ;
    private int age;

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
}
