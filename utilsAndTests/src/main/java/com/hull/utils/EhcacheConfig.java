package com.hull.utils;


import com.hull.utils.log.ILog;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;


/**
 * Created by hull on 2017/1/9.
 */

@Configuration
@EnableAutoConfiguration
public class EhcacheConfig implements ILog {
    @Bean(name="ehcache")
    @ConditionalOnMissingBean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        logger.info("aplus ehcache init");
        EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
        bean.setConfigLocation(new DefaultResourceLoader().getResource("cache/ehcache.xml"));
        return bean;
    }
}
