package com.gupao.homework.designPatterns.d3_singleton;

/**
 * Created by Administrator on 2017/5/21.
 */

/**
 * 数据库连接池
 */
public class DataSourceConnectionSingleton {

    //私有化类的构造方法
    private DataSourceConnectionSingleton(){}

    //创建一个内部类，内部类包含一个单例类的静态变量实例
    public static class InnerClass{
        private static final  DataSourceConnectionSingleton instance = new DataSourceConnectionSingleton();
    }

    //通过一个公共的方法回去 单例类的实例对象
    public static final DataSourceConnectionSingleton getInstance(){
        return InnerClass.instance;
    }

}
