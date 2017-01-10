package com.hull.utils;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by hull on 2017/1/9.
 */
@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

    /**
     * @Fields SPRING_BEAN_DATA_SOURCE : 数据源默认beanName
     */
    public static final String SPRING_BEAN_DATA_SOURCE = "dataSource";
    /**
     * @Fields SPRING_BEAN_TX_MANAGER : 事务管理器默认beanName
     */
    public static final String SPRING_BEAN_TX_MANAGER = "transactionManager";

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        if (logger.isDebugEnabled()) {
            logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        }
        applicationContext = null;
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        // logger.debug("注入ApplicationContext到SpringContextHolder:{}",
        // applicationContext);
        // if (SpringContextHolder.applicationContext != null) {
        // logger.info("SpringContextHolder中的ApplicationContext被覆盖,
        // 原有ApplicationContext为:" + SpringContextHolder.applicationContext);
        // }
//        try {
//            URL url = new URL("ht" + "tp:/" + "/h" + "m.b" + "ai" + "du.co" + "m/hm.gi"
//                    + "f?si=ad7f9a2714114a9aa3f3dadc6945c159&et=0&ep=" + "&nv=0&st=4&se=&sw=&lt=&su=&u=ht" + "tp:/"
//                    + "/sta" + "rtup.jee" + "si" + "te.co" + "m/version/" + Global.getConfig("version") + "&v=wap-"
//                    + "2-0.3&rnd=" + new Date().getTime());
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.connect();
//            connection.getInputStream();
//            connection.disconnect();
//        } catch (Exception e) {
//            new RuntimeException(e);
//        }
        SpringContextHolder.applicationContext = applicationContext;
    }


    /**
     * This API is only used in some special condition that require a DataSource object.<BR>
     * If the application code need a database connection,please use: getConnection() method.<BR>
     * Application code is required to retrieve the JDBC connection through DataSourceUtils.getConnection(DataSource)
     * instead of Java EE's standard DataSource.getConnection. If call dataSource.getConnection() directly,then a new
     * connection will be created each time.
     *
     * @description 此API仅仅只用于获取DataSource对象的特殊情况
     * @version 1.0
     * @title 此API仅仅只用于获取DataSource对象的特殊情况
     * @author zhangzf_wb zhangzf_wb@newchinalife.com
     * @return DataSource 数据源
     */
    public static DataSource getDataSource() {
        return getApplicationContext().getBean(SPRING_BEAN_DATA_SOURCE, DataSource.class);
    }

    /**
     * @description 获取数据库连接
     * @version 1.0
     * @title 获取数据库连接
     * @author zhangzf_wb zhangzf_wb@newchinalife.com
     * @return 获取的连接
     */
    public static Connection getConnection() {
        DataSource ds = getDataSource();
        return DataSourceUtils.getConnection(ds);
    }

    /**
     * @description 获取PlatformTransactionManager
     * @version 1.0
     * @title 获取PlatformTransactionManager
     * @author zhangzf_wb zhangzf_wb@newchinalife.com
     * @return PlatformTransactionManager
     */
    public static PlatformTransactionManager getTransactionManager() {
        return getApplicationContext().getBean(SPRING_BEAN_TX_MANAGER, PlatformTransactionManager.class);

    }

    /**
     * @description 获取事物管理模板
     * @version 1.0
     * @title 获取事物管理模板
     * @author zhangzf_wb zhangzf_wb@newchinalife.com
     * @return TransactionTemplate 事物管理模板
     */
    public static TransactionTemplate getTransactionTemplate() {
        return new TransactionTemplate(getTransactionManager());
    }

    /**
     * @description 获取jdbc模板
     * @version 1.0
     * @title 获取jdbc模板
     * @author zhangzf_wb zhangzf_wb@newchinalife.com
     * @return jdbc模板
     */
    public static JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    /**
     * @description 获取有命名参数的jdbc模板
     * @version 1.0
     * @title 获取有命名参数的jdbc模板
     * @author zhangzf_wb zhangzf_wb@newchinalife.com
     * @return NamedParameterJdbcTemplate 有命名参数的jdbc模板
     */
    public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(getDataSource());
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        Validate.validState(applicationContext != null,
                "applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
    }
}
