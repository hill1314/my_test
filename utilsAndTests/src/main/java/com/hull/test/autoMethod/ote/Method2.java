package com.hull.test.autoMethod.ote;

import com.hull.test.autoMethod.atomic.MyServerImpl;

import java.util.HashMap;

/**
 * Created by hull on 2017/1/19.
 */
public class Method2 {
    public HashMap appProcess(HashMap<String, Object> result,HashMap<String, Object> paramMap){
        MyServerImpl server = new MyServerImpl();
        int mp1 = (int) paramMap.get("mp1");
        int mp2 = (int) paramMap.get("mp2");
        int mr2 = server.method2(mp1,mp2);
        result.put("mr2",mr2);
        return result;
    }

}
