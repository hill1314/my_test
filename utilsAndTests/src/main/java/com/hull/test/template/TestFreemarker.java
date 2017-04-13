package com.hull.test.template;

import com.hull.utils.FreemarkerUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/13.
 */
public class TestFreemarker {

    @Test
    public void test01() {
        //1、创建数据模型
        Map<String,Object> data = new HashMap<String,Object>();
        //2、为数据模型添加值
        data.put("username", "张三11");
        //3、将数据模型和模板组合的数据输出到控制台
        FreemarkerUtils.print("hello.ftl", data);
    }

    public static void main(String[] args) {
        //1、创建数据模型
        Map<String,Object> data = new HashMap<String,Object>();
        //2、为数据模型添加值
        data.put("username", "张三");
//        FreemarkerUtils.creatFile("D:\\hello.txt","hello.ftl",data);
        FreemarkerUtils.print("hello.ftl",data);
    }
}
