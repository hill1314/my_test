package com.hull.spring.proxy.standed;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * Created by Administrator on 2017/5/13.
 */
public class FindLove {

    public static void main(String[] args) {
        try {
            /**
             * 原理：
             * 1、拿到被代理对象的引用，然后获取它的接口
             * 2、JDK 代理重新生成一个类，并实现代理对象所实现的接口
             * 3、拿到被代理对象的引用
             * 4、重新动态生成一个class字节码
             * 5、然后编译
             */

            Person obj = (Person) new MeiPo().getInstance(new XiaoXingXing());
            System.out.println(obj.getClass());
            obj.findLove();


            byte[] data = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
            FileOutputStream fos = new FileOutputStream("D:$Proxy0.class");
            fos.write(data);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
