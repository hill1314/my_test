package com.hull.spring.proxy.standed;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/5/13.
 */
public class MeiPo implements InvocationHandler{

    private Person target;//被代理对象的引用

    public Object getInstance(Person target) throws Exception{
        this.target = target;
        Class clazz = target.getClass();
        System.out.println("被代理对象："+clazz.getClass());
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆，你的姓名："+this.target.getName()+"，性别："+this.target.getSex());
        System.out.println("开始海选...");
        System.out.println("-------------------------");
//        this.target.findLove();
        method.invoke(target,args);
        System.out.println("-------------------------");
        System.out.println("找到了，办事吧。");
        return null;
    }
}
