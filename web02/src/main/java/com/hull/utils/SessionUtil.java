package com.hull.utils;

import com.hull.session.ISession;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * 管理session信息的保存和读取
 *
 * @author linql 创建 2012-2-16
 * @author linql 修改 2012-07-08 修改session实现机制，调整为从session中获取
 * @version 1.0 Copyright(c) 北京神州数码思特奇信息技术股份有限公司
 */
public final class SessionUtil implements ISession {

    /**
     * session实现类方法
     */
    private static final String PROPERTIES_FILE = "sessionutil.properties";

    /**
     * 配置文件实现类配置KEY值
     */
    private static final String IMPL_CLASS_KEY = "ISessionImplClass";

    /**
     * 读取的配置文件实现类
     */
    private static String iSessionImplClass = null;

    /**
     * 加载配置信息
     */
    static {
        Properties configProperties = new Properties();
        // Load reference definition file
        InputStream is = SessionUtil.class.getResourceAsStream(PROPERTIES_FILE);
        if (is == null) {
            throw new IllegalStateException("从classPath加载日志配置文件[sessionutil.properties]失败");
        }
        try {
            configProperties.load(is);
            iSessionImplClass = configProperties.getProperty(IMPL_CLASS_KEY);
        } catch (IOException e) {
            throw new IllegalStateException("从classPath加载日志配置文件[logthreadcontextfactory.properties]失败");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                // do nothing
            }
        }
    }

    /**
     * session数据存放容器
     */
    private ISession sessionContain = null;

    /**
     * <p/>
     * 创建一个新的实例 SessionUtil.
     *
     * @param _sessionCookieKey 请求信息
     * @param _request          请求信息
     */
    public SessionUtil(String _sessionCookieKey, HttpServletRequest _request) {
        /* 获取session实现类 */
        Class clazz = null;
        try {
            clazz = Class.forName(iSessionImplClass);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("session实现类在CLASSPATH中不存在", e);
        }
        try {
            Constructor constructor = clazz.getConstructor(String.class);
            this.sessionContain = initISessionByCnsct(constructor, _sessionCookieKey);
        } catch (NoSuchMethodException e) {
            Constructor constructor = null;
            try {
                constructor = clazz.getConstructor(HttpServletRequest.class);
                this.sessionContain = initISessionByCnsct(constructor, _request);
            } catch (NoSuchMethodException e1) {
                throw new IllegalStateException("session实现类不存在String或者HttpServletRequest为参数的构造方法", e);
            }
        }
    }

    /**
     * <p/>
     * 创建一个新的实例 SessionUtil.
     *
     * @param _request 请求信息
     */
    public SessionUtil(HttpServletRequest _request) {
        Class clazz = null;
        try {
            clazz = Class.forName(iSessionImplClass);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Session实现类在CLASSPATH中不存在", e);
        }
        try {
            Constructor constructor = clazz.getConstructor(HttpServletRequest.class);
            this.sessionContain = initISessionByCnsct(constructor, _request);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Session实现类不存在HttpServletRequest参数的构造方法", e);
        }
    }

    private ISession initISessionByCnsct(Constructor constructor, Object param) {
        try {
            ISession iSession = (ISession) constructor.newInstance(param);
            return iSession;
        } catch (InvocationTargetException e) {
            throw new IllegalStateException("构造session实现类失败", e);
        } catch (InstantiationException e) {
            throw new IllegalStateException("构造session实现类失败", e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("构造session实现类失败", e);
        }
    }

    /**
     * 获取session中的字符对象
     *
     * @param attrName 属性名称
     * @return 属性值
     */
    public String getString(String attrName) {
        return String.valueOf(sessionContain.getObject(attrName));
    }

    /**
     * 获取session中的int对象
     *
     * @param attrName 属性名称
     * @return 属性值
     */
    public int getInt(String attrName) {
        return Integer.valueOf(getString(attrName));
    }

    /**
     * 获取session中的long对象
     *
     * @param attrName 属性名称
     * @return 属性值
     */
    public long getLong(String attrName) {
        return Long.valueOf(getString(attrName));
    }

    /**
     * 获取session中的Object对象
     *
     * @param attrName 属性名称
     * @return 属性值
     */
    public Object getObject(String attrName) {
        return sessionContain.getObject(attrName);
    }

    /**
     * 获取Session的Key值
     *
     * @return Session主键
     */
    @Override
    public String getSessionKey() {
        return sessionContain.getSessionKey();
    }

    /**
     * 设置属性值
     *
     * @param attrName 属性名称
     * @param value    属性值
     */
    public void setAttr(String attrName, Object value) {
        this.sessionContain.setAttr(attrName, value);
    }

    /**
     * 删除Session属性
     *
     * @param _attrName 属性名称
     */
    public void removeAttr(String _attrName) {
        this.sessionContain.removeAttr(_attrName);
    }

    /**
     * 删除所有Session数据
     */
    public void removeAll() {
        this.sessionContain.removeAll();
    }
}
