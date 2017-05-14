package com.hull.spring.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/5/14.
 */
public class GPCgiMeipo implements MethodInterceptor{

    public Object getInstance(Class clazz) throws Exception{
        Enhancer enhancer = new Enhancer();
        //设置父类，指定实现哪个类的子类
        enhancer.setSuperclass(clazz);
        //设置回调
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * cglib 动态代理 ，也是做字节码重组
     * 不像jdk实现动态代理，必须有一个接口，这个不需要接口，
     * 内部实现：为被代理类实现一个子类

     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("我是媒婆");
        System.out.println("开始海选...");
        System.out.println("-------------------------");
        //此处调用的是父类的方法，用invokeSuper，
        // 用invoke是执行子类的方法，会递归的生成子类引用，会进入死循环
        //这里的obj对象引用是cglib创建的，是被代理对象的子类
        methodProxy.invokeSuper(obj,args);
        System.out.println("-------------------------");
        System.out.println("找到了，办事吧。");
        return null;
    }


}
