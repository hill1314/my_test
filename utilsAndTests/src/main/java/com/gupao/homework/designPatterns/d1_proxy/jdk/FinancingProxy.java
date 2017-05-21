package com.gupao.homework.designPatterns.d1_proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/5/21.
 * 理财 代理
 */
public class FinancingProxy implements InvocationHandler{

    //被代理对象
    Investor investor = null;

    public Object setInvestor(Investor investor){
        this.investor = investor;
        Class clazz = investor.getClass();
        System.out.println("Congratulations, Mr "+investor.getName()+" , you have been our vip customer!");
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("We will do anything you want to do ... ");
        method.invoke(investor,args);
        System.out.println("Done!");
        return null;
    }
}
