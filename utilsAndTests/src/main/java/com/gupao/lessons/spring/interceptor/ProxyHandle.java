package com.gupao.lessons.spring.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Administrator on 2017/5/7.
 */
public class ProxyHandle implements InvocationHandler {

    private Object object = null;
    private ListIterator<MyInterceptor> interceptorIterator = null;

    public ProxyHandle(Object object) {
        this.object = object;

        ArrayList<MyInterceptor> arrayList = new ArrayList<MyInterceptor>();
        arrayList.add(new Interceptor1());
        arrayList.add(new Interceptor2());
        this.interceptorIterator = arrayList.listIterator();

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object oo = null;
        while(interceptorIterator.hasNext()){
            MyInterceptor interceptor = interceptorIterator.next();
            interceptor.preHandle();
        }
        oo =  method.invoke(object,args);
        while (interceptorIterator.hasPrevious()){
            MyInterceptor interceptor = interceptorIterator.previous();
            interceptor.postHandle();
        }
        return oo;
    }

    public  Object getProxy(){
        return Proxy.newProxyInstance(Person1.class.getClassLoader(), Person1.class.getInterfaces(), this);
    }
}
