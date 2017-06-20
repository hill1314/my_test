package com.gupao.lessons.plessions;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by Administrator on 2017/6/20.
 */
public class MyDataSourcePoolImpl implements IMyDataSourcePool{
    private static String jdbcDriver = "";
    private static String jdbcUrl = "";
    private static String userName = "";
    private static String password = "";
    private static int initCount ;
    private static int stepSize;
    private static int poolMaxSize;
    private static Vector<PooledConnection> pooledConnections = new Vector<>();

    public MyDataSourcePoolImpl(){
        init();
    }

    private void init() {
        InputStream in = MyDataSourcePoolImpl.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pro = new Properties();
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        jdbcDriver = pro.getProperty("jdbcDriver");
        jdbcUrl = pro.getProperty("jdbcUrl");
        userName = pro.getProperty("userName");
        password = pro.getProperty("password");
        initCount = Integer.parseInt(pro.getProperty("initCount"));
        stepSize = Integer.parseInt(pro.getProperty("stepSize"));
        poolMaxSize = Integer.parseInt(pro.getProperty("poolMaxSize"));

        //创建数据库连接
        try {
            //反射拿到Mysql数据库驱动
            Driver driver = (Driver) Class.forName(jdbcDriver).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建连接
        createConnections(initCount);
    }


    @Override
    public PooledConnection getConnection() {
        if(pooledConnections.size()==0){
            System.out.println("获取连接对象失败,连接池为空");
            throw new RuntimeException("获取连接对象失败,连接池为空");
        }
        //获取连接，真正的连接 就是它为空闲状态
        PooledConnection connection =  getRealConnection();
        //判断是否为空
        while (connection==null){
            //说明管道都被占用了，创建管道
            createConnections(stepSize);
            connection = getRealConnection();
            try {
                //经验 等300毫秒
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    //考虑多线程环境
    //其实是一个判断我们是否拿到有效的连接对象
    //有效：表示管道没有超时，且没有被占用
    private synchronized PooledConnection getRealConnection() {
        //判断连接池是不是有我们需要的空闲连接对象
        for(PooledConnection conn : pooledConnections){
            //判断是否被占用
            if(!conn.isBusy()){
                Connection connection = conn.getConnection();
                //进一步判断是否超时
                try {
                    //isValid(2000) 原理就是想数据库一个信息是否能收到返回 2000毫秒
                    //返回false,代表没有收到数据库的回应，正在 connection超时
                    if(!connection.isValid(2000)){
                        //重新创建一个新管道
                        Connection validConn = DriverManager.getConnection(
                                jdbcUrl,userName,password);
                        //替换原来无效的
                        conn.setConnection(validConn);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn.setBusy(true); //设置被占用
                return conn;
            }
        }
        return null;
    }

    /**
     * 创建连接数据管道
     * @param count
     */
    @Override
    public void createConnections(int count) {
        if(poolMaxSize>0 && pooledConnections.size()+count>poolMaxSize){
            System.out.println("创建连接对象失败，超过最大连接数");
            throw new RuntimeException("创建连接对象失败，超过最大连接数");
        }
        for(int i=0;i<count;i++){
            try {
                //创建驱动，注册
                Connection conn = DriverManager.getConnection(
                        jdbcUrl,userName,password);
                PooledConnection pooledConnection = new PooledConnection(conn,false);
                pooledConnections.add(pooledConnection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
