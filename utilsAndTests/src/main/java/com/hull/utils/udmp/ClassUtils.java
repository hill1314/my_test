package com.hull.utils.udmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;

/**
 * 
 * 类工具——继承Apache Commons
 * 
 * @author Spring.Cao
 * @version v1.0 2015-12-22
 * 
 */
public class ClassUtils extends org.apache.commons.lang3.ClassUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ClassUtils.class);
	
    /**
     * @description 获取类资源
     * @version
     * @title
     * @param resourceName 资源名
     * @param callingClass 资源类型
     * @return URL 类路径
    */
    public static URL getResource(final String resourceName, final Class<?> callingClass) {
        URL url = callingClass.getClassLoader().getResource(resourceName);
        return url;
    }
    
    /**
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型. 如无法找到, 返回Object.class.
     * 
     *@param clazz
     *            clazz The class to introspect
     * @param index
     *            the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be
     *         determined
     */
    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(final Class clazz, final int index) {
        
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
           return Object.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
                     return Object.class;
        }
        if (!(params[index] instanceof Class)) {
              return Object.class;
        }

        return (Class) params[index];
    }
}