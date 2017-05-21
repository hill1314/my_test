package com.gupao.lessons.spring.servlet.filter;

/**
 * Created by Administrator on 2017/5/7.
 */
public class FilterTest {
    public static void main(String[] args) {
        FilterContext filterContext = new FilterContext();
        filterContext.doFilter("111",filterContext);
    }
}
