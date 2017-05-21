package com.gupao.lessons.spring.interceptor;

/**
 * Created by Administrator on 2017/5/7.
 */
public interface MyInterceptor {
    boolean preHandle();
    void postHandle();
    void afterCompletion();
}
