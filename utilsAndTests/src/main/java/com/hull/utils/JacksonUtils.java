package com.hull.utils;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by Administrator on 2016/11/19.
 */
public class JacksonUtils {

    /**
     *   Convert object to JSON string
     * @param obj
     * @return
     * @throws IOException
     */
    public static String obj2Json(Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(obj);
        return jsonStr;
    }

    /**
     * Convert Json string to Object
     * @param jsonStr
     * @param className
     * @return
     * @throws IOException
     */
    public static Object json2Obj(String jsonStr, TypeData.ClassName className) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object obj = mapper.readValue(jsonStr, className.getClass());
        return obj;
    }

    public static Object json2Obj(String jsonStr, Class className) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object obj = mapper.readValue(jsonStr, className);
        return obj;
    }
    
   
}
