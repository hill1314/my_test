package com.hull.test.autoMethod.camel;

import com.hull.utils.udmp.PropertiesLoader;

import java.text.MessageFormat;
import java.util.Properties;

/**
 * Created by hull on 2017/1/19.
 */
public class ExceptionMessageHelper {
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
                if (!isInit) {//考虑替换为PropertyLoad来实现
                    properties = new PropertiesLoader(ExceptionMsgPath.FRM_EXCEPTION_MSG.getPath()).getProperties();
                    isInit = true;
                }
            }
        }
        return properties;
    }

    /**
     * 通过错误代码来获取错误消息
     *
     * @param code 错误代码
     * @return 错误消息
     */
    public static String getExMsg(String code) {
        if (null == code) {
            return "";
        }
        // 公共技术平台-UDMP
//        if (!code.startsWith("UDMP")) {
//            return code;
//        }
        String msg = null;
        try {
            msg = getProperties().getProperty(code);
            if (null == msg) {
                msg = "错误代码未定义:" + code;
            } else {
                msg = code + ":" + msg;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FrameworkException(msg , e);
        }
        return msg;
    }

    /**
     * 通过错误代码来获取错误消息 当有传入args时，将会替换“测试{0}测试{1}测试{2}”中的“{x}”(X为从0开始的整数)
     *
     * @param code 错误代码
     * @param args 要替换“{x}”(x为从0开始的整数)的值
     * @return 错误消息
     */
    public static String getExMsg(String code, Object[] args) {
        String msg = getExMsg(code);
        msg = MessageFormat.format(msg, args);
        return msg;
    }
}
