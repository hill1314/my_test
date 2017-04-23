package com.hull.test.javabase.jdk1_8NewFeatures;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by Administrator on 2017/3/6.
 *
 * 我们为什么需要Lambda表达式
 主要有三个原因：
 > 更加紧凑的代码
 比如Java中现有的匿名内部类以及监听器(listeners)和事件处理器(handlers）都显得很冗长
 > 修改方法的能力（我个人理解为代码注入，或者有点类似JavaScript中传一个回调函数给另外一个函数）
 比如Collection接口的contains方法，当且仅当传入的元素真正包含在集合中，才返回true。
 而假如我们想对一个字符串集合，传入一个字符串，只要这个字符串出现在集合中（忽略大小写）就返回true。
 简单地说，我们想要的是传入“一些我们自己的代码”到已有的方法中，已有的方法将会执行我们传入的代码。
 Lambda表达式能很好地支持这点
 > 更好地支持多核处理
 例如，通过Java 8新增的Lambda表达式，我们可以很方便地并行操作大集合，充分发挥多核CPU的潜能。
 并行处理函数如filter、map和reduce。

 */
public class F2_Lambda {

    static List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

    /**
     * 原来的写法
     */
    public static void compareOld(){
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
    }

    /**
     * 新的写法
     */
    public static void compareNew(){
        Collections.sort(names,(String a,String b)->b.compareTo(a));
    }


    public static void main(String[] args) {
        //Predicate和Filter
        String possible="111";
        Predicate<String> metched = s -> s.equalsIgnoreCase(possible);
//        names.filter(metched);
    }

}
