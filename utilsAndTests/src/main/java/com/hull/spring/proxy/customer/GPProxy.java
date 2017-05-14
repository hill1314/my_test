package com.hull.spring.proxy.customer;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;

/**
 * Created by Administrator on 2017/5/14.
 */
public class GPProxy {

    public static Object newProxyInstance(GPClassLoader classLoader,Class<?>[] interfaces,
                                          GPInvocationHandle invocationHandle){
        try {
            //1\生成源代码

            //2、将源代码输出到磁盘,保存为.Java 文件
            String filePath = GPProxy.class.getResource("").getPath();
            File f = new File(filePath+"$Proxy.java");

            //3、编译源代码，生成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = manager.getJavaFileObjects(f);

            JavaCompiler.CompilationTask task = compiler.getTask(null,manager,null,null,null,iterable);
            task.call();
            manager.close();


            //4、将class文件中的内容，动态加载到JVM中


            //5、返回被代理后的对象
            Class proxyClass = classLoader.findClass("$Proxy0");
            Constructor c =  proxyClass.getConstructor(GPInvocationHandle.class);
            return c.newInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
