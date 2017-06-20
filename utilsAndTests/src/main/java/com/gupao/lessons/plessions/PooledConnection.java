package com.gupao.lessons.plessions;

import java.sql.Connection;

/**
 * Created by Administrator on 2017/6/20.
 *
 * 自定义 被连接池管理的管道对象
 */
public class PooledConnection {
    //真正的管道
    private Connection connection;
    //表示是否繁忙
    private boolean isBusy = false;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean isBusy(){
        return  isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public PooledConnection(Connection connection, boolean isBusy){
        this.connection = connection;
        this.isBusy = isBusy;
    }
}
