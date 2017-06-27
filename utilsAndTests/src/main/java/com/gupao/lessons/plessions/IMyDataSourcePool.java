package com.gupao.lessons.plessions;

/**
 * Created by Administrator on 2017/6/20.
 */
public interface IMyDataSourcePool {
    //获取连接
    PooledConnection getConnection();
    //创建连接
    void createConnections(int count);
}
