package com.hull.servlet.filter;

/**
 * Created by Administrator on 2017/5/7.
 */
public class Filter1 implements IMyFilter{
    @Override
    public void doFilter(String arg, FilterContext filterContext) {
        System.out.println("执行 过滤器filter1 ");
        filterContext.doFilter(arg,filterContext);
        System.out.println("结束 过滤器filter1 ");
    }
}
