package com.hull.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {

    /**
     * 日志输出
     */
    private static Log logger = LogFactory.getLog(PropertiesUtil.class);

    /**
     * 构造函数
     */
    private PropertiesUtil() {
    }

    /**
     * 重配置文件中读取配置信息
     *
     * @param fileName     配置文件前缀
     * @param propertyName 属性名称
     * @return 属性值
     */
    public static String readString(String fileName, String propertyName) {
        return readString(fileName, propertyName, true);
    }

    /**
     * 重配置文件中读取配置信息
     *
     * @param fileName     配置文件前缀
     * @param propertyName 属性名称
     * @param cacheFlag    是否缓存配置文件
     * @return 属性值
     */
    public static String readString(String fileName, String propertyName, boolean cacheFlag) {
        // 方法开始日志
        if (logger.isDebugEnabled()) {
            logger.debug("方法【readString】调用开始，参数:[propertyFileName]=" + fileName
                    + ", [propertyName]=" + propertyName);
        }
        // 处理结果
        Properties properties = readPropertyFile(fileName, cacheFlag);
        String result = readString(properties, propertyName);
        // 方法结束日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【readString】结束result=" + result);
        }
        // 返回处理结果
        return result;
    }

    /**
     * 重配置文件中读取配置信息
     *
     * @param properties   配置文件
     * @param propertyName 属性名称
     * @return 属性值
     */
    public static String readString(Properties properties, String propertyName) {
        String readResult = properties.getProperty(propertyName);
        if (!StringUtil.isEmptyOrNull(readResult)) {
            readResult = readResult.trim();
        }
        return readResult;
    }

    /**
     * 重配置文件中读取配置信息
     *
     * @param properties   配置文件
     * @param propertyName 属性名称
     * @return 属性值 值不符合要求，不配置返回false
     */
    public static boolean readBoolean(Properties properties, String propertyName) {
        boolean result = false;
        String propertyValue = readString(properties, propertyName);
        return result = "true".equals(propertyValue);
    }

    /**
     * 重配置文件中读取配置信息
     *
     * @param properties   配置文件
     * @param propertyName 属性名称
     * @return 属性值 值不符合要求，不配置返回false
     */
    public static int readInt(Properties properties, String propertyName) {
        boolean result = false;
        String propertyValue = readString(properties, propertyName);
        return Integer.parseInt(propertyValue);
    }

    /**
     * 重配置文件中读取配置信息
     *
     * @param fileName     配置文件前缀
     * @param propertyName 属性名称
     * @return 属性值 值不符合要求，不配置返回false
     */
    public static boolean readBoolean(String fileName, String propertyName) {
        // 开始日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【readBoolean】开始propertyFileName=" + fileName
                    + "; propertyName=" + propertyName);
        }
        Properties properties = readPropertyFile(fileName);
        boolean result = readBoolean(properties, propertyName);
        // 方法结束日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【readBoolean】结束result=" + result);
        }
        return result;
    }

    /**
     * 重配置文件中读取文件路径配置信息
     *
     * @param fileName     配置文件前缀
     * @param propertyName 属性名称
     * @return 属性值 值不符合要求，不配置返回false
     */
    public static String readPath(String fileName, String propertyName) {
        // 开始日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【readPath】fileName=" + fileName + "; propertyName="
                    + propertyName);
        }
        String propertyValue = readPath(fileName, propertyName, true);
        // 方法结束日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【readPath】结束result=" + propertyValue);
        }
        return propertyValue;
    }

    /**
     * 重配置文件中读取文件路径配置信息
     *
     * @param fileName     配置文件前缀
     * @param propertyName 属性名称
     * @return 属性值 值不符合要求，不配置返回false
     */
    public static String readPath(String fileName, String propertyName, boolean cacheFlag) {
        // 开始日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【readPath】[fileName]=" + fileName + "; [propertyName]="
                    + propertyName + ":[cacheFlag]=" + cacheFlag);
        }
        String propertyValue = readString(fileName, propertyName, cacheFlag);
        // 文件替换处理
//        propertyValue = StringUtil.formatFilePath(propertyValue);
        // 方法结束日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【readPath】结束result=" + propertyValue);
        }
        return propertyValue;
    }

    /**
     * 重配置文件中读取文件夹路径配置信息
     *
     * @param fileName     配置文件前缀
     * @param propertyName 属性名称
     * @return 属性值 值不符合要求，不配置返回false
     */
    public static String readDir(String fileName, String propertyName) {
        // 开始日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【readDir】fileName=" + fileName + "; propertyName="
                    + propertyName);
        }
        String propertyValue = readDir(fileName, propertyName, true);
        // 方法结束日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【readDir】结束result=" + propertyValue);
        }
        return propertyValue;
    }

    /**
     * 重配置文件中读文件夹取路径配置信息
     *
     * @param fileName     配置文件前缀
     * @param propertyName 属性名称
     * @return 属性值 值不符合要求，不配置返回false
     */
    public static String readDir(String fileName, String propertyName, boolean cacheFlag) {
        // 开始日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【readDir】[fileName]=" + fileName + "; [propertyName]="
                    + propertyName + ":[cacheFlag]=" + cacheFlag);
        }
        String propertyValue = readString(fileName, propertyName, cacheFlag);
        // 文件替换处理
//        propertyValue = StringUtil.formatDirPath(propertyValue);
        // 方法结束日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【readDir】结束result=" + propertyValue);
        }
        return propertyValue;
    }

    /**
     * 读取配置文件
     *
     * @param fileName  配置文件名称
     * @param cacheFlag 配置文件缓存
     * @return 缓存文件表
     */
    public static Properties readPropertyFile(String fileName, boolean cacheFlag) {
        // 开始日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【fileName】,入参[fileName]=" + fileName + ",[cacheFlag]=" + cacheFlag);
        }
        Properties properties = null;
//        if (cacheFlag) {
//            properties = (Properties) CacheManager.getInstance().get("PROPERTIES_FILE", fileName);
//            if (properties == null) {
//                properties = readPropertyFile(fileName);
//                CacheManager.getInstance().put("PROPERTIES_FILE", fileName, properties);
//            }
//        } else {
            properties = readPropertyFile(fileName);
//        }
        // 开始日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【fileName】结束");
        }
        return properties;
    }

    /**
     * 不缓存读取配置文件
     *
     * @param fileName 配置文件名称
     * @return 缓存文件表
     */
    public static Properties readPropertyFile(String fileName) {
        // 开始日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【fileName】fileName=" + fileName);
        }
        Properties properties = new Properties();
        // 配置文件名称要求小写
        InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(
                fileName + ".properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("读取配置文件【" + fileName + "】错误", e);
            throw new RuntimeException("读取配置文件【" + fileName + "】错误", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.warn("关闭配置文件【" + fileName + "】错误", e);
                }
            }
        }
        // 开始日志
        if (logger.isDebugEnabled()) {
            logger.debug("调用方法【fileName】结束");
        }
        return properties;
    }
}
