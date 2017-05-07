package com.hull.spring.interceptor;

/**
 * Created by Administrator on 2017/5/7.
 */
public class Interceptor1 implements MyInterceptor{
    @Override
    public boolean preHandle() {
        System.out.println("开始-拦截器1");
        return false;
    }

    @Override
    public void postHandle() {
        System.out.println("结束-拦截器1");
    }

    @Override
    public void afterCompletion() {

    }
}
