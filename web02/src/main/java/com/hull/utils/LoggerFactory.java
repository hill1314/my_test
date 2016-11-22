package com.hull.utils;

import org.slf4j.Logger;

/**
 * Created by Administrator on 2016/11/22.
 */
public class LoggerFactory {
    /**
     * @description 不需要传类名获取slf4j的Logger实例
     * @version
     * @title
     * @return  Logger实例
     */
    public static Logger getLogger() {
        String name = new Exception().getStackTrace()[1].getClassName();
        org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(name);
        return logger;
    }
}
