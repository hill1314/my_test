package com.hull.utils;

import com.hull.test.autoMethod.camel.BizException;
import com.hull.utils.udmp.PropertiesLoader;

import java.util.Properties;

/**
 * Created by hull on 2017/3/31.
 */
public class PropertiesUtils {
    /** 属性对象 */
    private static Properties properties = new Properties();

    /** 初始化标志 */
    private static boolean isInit;

    static {
        getProperties();
    }

    /**
     * 获取属性对象
     *
     * @return Properties
     */
    public static Properties getProperties() {
        if (!isInit) {
            synchronized (properties) {
                if (!isInit) {
                    properties = new PropertiesLoader("application.properties").getProperties();
                    isInit = true;
                }
            }
        }
        return properties;
    }

    /**
     *  获取配置的 key 对应的value
     * @param key
     * @return
     */
    public static String getConfig(String key){
        if (null == key) {
            return "";
        }
        String value = null;
        try {
            value = getProperties().getProperty(key);
            if (null == value) {
                throw new BizException("00009");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("00009");
        }
        return value;
    }
}
