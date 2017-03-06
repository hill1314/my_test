package com.hull.test.javabase.jdk1_8NewFeatures;

/**
 * Created by Administrator on 2017/3/6.
 *
 * Java 8允许我们给接口添加一个非抽象的方法实现，只需要使用 default关键字即可，这个特征又叫做扩展方法
 *
 * Java中只有单继承，如果要让一个类赋予新的特性，通常是使用接口来实现，
 * 在C++中支持多继承，允许一个子类同时具有多个父类的接口与功能，在其他语言中，
 * 让一个类同时具有其他的可复用代码的方法叫做mixin。
 * 新的Java 8 的这个特新在编译器实现的角度上来说更加接近Scala的trait。
 * 在C#中也有名为扩展方法的概念，允许给已存在的类型扩展方法，和Java 8的这个在语义上有差别。
 *
 * 我的理解：
 * Java只允许单集成，但是可以实现多个接口，如果接口中允许有默认的方法，那么就可以更好的实现代码复用
 *
 */
public interface F1_MyInterface {
    String method1();
    default int method2(){
        return 0;
    }

    //定义一个匿名内部类
//    实现了F1_MyInterface接口的子类只需要实现一个method1方法，默认方法method2将在子类上可以直接使用。
    F1_MyInterface f1 = new F1_MyInterface() {
        @Override
        public String method1() {
            return "hello!";
        }
    };
}
