package com.hull.utils.log;

import org.slf4j.Logger;

/**
 * Created by hull on 2017/1/9.
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
