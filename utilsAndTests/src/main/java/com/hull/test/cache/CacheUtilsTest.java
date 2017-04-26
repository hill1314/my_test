package com.hull.test.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.MemoryUnit;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by hull on 2017/1/9.
 */
public class CacheUtilsTest {
    @Test
    public void testDefault() {
        CacheManager cacheManager = new CacheManager();
        //输出当前cacheManager正在使用的配置对应的Xml格式文本
        System.out.println(cacheManager.getActiveConfigurationText());
    }

    @Test
    public void test() {
        //新建一个CacheManager的配置信息
        Configuration configuration = new Configuration();
        //新建一个缓存的配置信息
        CacheConfiguration cacheConfiguration = new CacheConfiguration().name("test");
        //指定当前缓存的最大堆内存值为100M
        cacheConfiguration.maxBytesLocalHeap(100, MemoryUnit.MEGABYTES);
        //添加一个cache
        configuration.addCache(cacheConfiguration);
        configuration.dynamicConfig(false);  //不允许动态修改配置信息
        CacheManager cacheManager = new CacheManager(configuration);
        Cache cache = cacheManager.getCache("test");
        cache.put(new Element("ID", "111"));
        System.out.println(cache.get("ID").getObjectValue());
        //删除缓存
//        cacheManager.removeCache("test");
        //清空缓存
        cache.flush();
        System.out.println(cache.get("ID"));
    }

    @Test
    public void testInputStream() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("/ehcache1.xml");
        CacheManager cacheManager = new CacheManager(is);
        is.close();
        System.out.println(cacheManager.getActiveConfigurationText());
    }

    @Test
    public void testXmlPath() {
        //这个文件路径可以是相对路径，也可以是绝对路径。这里使用的是相对路径。
        CacheManager cacheManager = new CacheManager("src/main/resources/cache/ehcache1.xml");
        System.out.println(cacheManager.getActiveConfigurationText());
    }

    @Test
    public void testURL() {
        URL url = this.getClass().getResource("/cache/ehcache1.xml");
        CacheManager cacheManager = new CacheManager(url);
        System.out.println(cacheManager.getActiveConfigurationText());
    }

}
