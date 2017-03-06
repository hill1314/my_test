package com.hull.test.javabase.collectionStudy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/4.
 *
 * 参考：
 * http://www.jb51.net/article/80443.htm
 */
public class MapStudy {

    /**
     *  遍历 HashMap
     */
    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<String, String>();
        for(int i=1;i<=10;i++){
            map.put("key"+i, "value"+i);
        }

    }

    public static void iterater(Map<String, String> map){
        //第一种：普遍使用，二次取值
        System.out.println("通过Map.keySet遍历key和value：");
        for (String key : map.keySet()) {
            System.out.println("key= "+ key + " and value= " + map.get(key));
        }

        //第二种
        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第三种：推荐，尤其是容量大时
        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }

        //第四种
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        for (String v : map.values()) {
            System.out.println("value= " + v);
        }

        //第五种 jdk1.8 提供的新的遍历方法
        map.forEach((id, val) -> System.out.println("key="+id+",val="+val));
    }

}
