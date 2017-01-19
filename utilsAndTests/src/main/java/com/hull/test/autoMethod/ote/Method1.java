package com.hull.test.autoMethod.ote;

import com.hull.test.autoMethod.atomic.MyServerImpl;

import java.util.HashMap;

/**
 * Created by hull on 2017/1/19.
 */
public class Method1 {
    public HashMap appProcess(HashMap<String, Object> result,HashMap<String, Object> paramMap){
        MyServerImpl server = new MyServerImpl();
        String mr1 = server.method1("hello");
        result.put("mr1",mr1);
        return result;
    }

}
