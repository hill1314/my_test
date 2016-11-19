package com.hull.utils;


import com.hull.entity.User;
import org.apache.ibatis.javassist.bytecode.stackmap.TypeData;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("Change Object to JSON String: " + jsonStr);
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


    public static void main(String[] args) {
        try {
            List userList = new ArrayList();
            User user = new User();
            user.setUserId("1111");
            user.setUserName("aaaa");

            userList.add(user);
            obj2Json(userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
