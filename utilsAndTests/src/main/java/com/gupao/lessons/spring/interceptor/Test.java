package com.gupao.lessons.spring.interceptor;

/**
 * Created by Administrator on 2017/5/7.
 */
public class Test {
    public static void main(String [] args){
        Person1 p1 = new Person1();
        ProxyHandle proxyHandle = new ProxyHandle(p1);

        //Psrson1 必须实现接口 不然会出现类型转换错误
        IPerson proxy = (IPerson) proxyHandle.getProxy();
        proxy.sayHello();
    }
}
