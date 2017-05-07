package com.hull.servlet.filter;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/5/7.
 */
public class FilterContext implements IMyFilter{
    Iterator<IMyFilter> iterator = null;

    public FilterContext(){
        ArrayList list = new ArrayList();
        Filter1 filter1 = new Filter1();
        Filter2 filter2 = new Filter2();
        list.add(filter1);
        list.add(filter2);
        iterator = list.iterator();
    }

    @Override
    public void doFilter(String arg, FilterContext filterContext) {
        if(iterator.hasNext()){
            IMyFilter filter = iterator.next();
            filter.doFilter(arg,filterContext);
        }else{
            System.out.println("调用acction："+arg);
        }
    }
}
