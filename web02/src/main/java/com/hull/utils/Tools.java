package com.hull.utils;

import java.util.UUID;

/**
 * Created by Administrator on 2016/11/15.
 */
public class Tools {

    /**
     * 获取32位UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
