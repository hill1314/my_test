package com.gupao.lessons.springAop;

import org.aopalliance.intercept.Joinpoint;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/6/3.
 */

@Component
@EnableAspectJAutoProxy
public class LogAspect {

    public void before(Joinpoint joinpoint){
        System.out.println(joinpoint);
    }

    public void after(Joinpoint joinpoint){
        System.out.println(joinpoint);
    }

    public void afterReturning(Joinpoint joinpoint){
        System.out.println(joinpoint);
    }

    public void throwException(Joinpoint joinpoint){
        System.out.println(joinpoint);
    }

}
