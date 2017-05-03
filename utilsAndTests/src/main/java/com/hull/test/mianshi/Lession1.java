package com.hull.test.mianshi;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/5/3.
 */
public class Lession1 {
    public static void main(String[] args) {
        Integer a=1,b=2;
//        System.out.println(a==b);
        System.out.println("a="+a+",b="+b);
        swap(a,b);
        System.out.println("a="+a+",b="+b);
    }

    /**
     * 反射法实现 （装箱/拆箱）
     * @param a
     * @param b
     */
    private static void swap(Integer a, Integer b) {
        Integer tem = new Integer(a.intValue());
        try {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);//绕过检查，这样可以访问私有变量
            field.set(a,b.intValue());
            field.set(b,tem);

//            field.setInt(a,b.intValue());
//            field.setInt(b,tem);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
