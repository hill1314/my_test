package com.hull.test.javabase.pass;

/**
 * Created by hull on 2017/2/24.
 */

/**
 * java 的值传递 和引用传递 ，学习测试
 */
public class Test1 {

    public static void main(String[] args) {
        int a ;             //num是基本类型，值就直接保存在变量中
        String str;         //str是引用类型，变量中保存的只是实际对象的地址

        a = 1;              //基本类型 num ，赋值运算符会直接改变变量的值，原来的值被覆盖掉
        str = "a";          //引用类型 str，赋值运算符会改变引用中所保存的地址，原来的地址被覆盖掉。但是原来的对象不会被改变（重要）

        int[] nums = {1,2,3};
        Person p = new Person("mike");
        Person p2 = new Person("mike2");

        Car car = new Car("benchi");

        method1(a,str,nums,p,car);

        //除了在函数传值的时候是"引用传递"，在任何用"＝"向对象变量赋值的时候都是"引用传递"。
        Person p3 = p2;     //引用传递,指向同一块地址
        p3.setName("mike3");

        System.out.println(a);           //值传递
        System.out.println(str);         //值传递
        System.out.println(nums[2]);         //引用传递
        System.out.println(p.getName());    //引用传递
        System.out.println(p2.getName());    //引用传递
        System.out.println(car.getName());    //引用传递
    }

    public static void method1(int a,String str,int[] nums,Person p,Car car){
        a = 2;
        str = "b";      // string 是finnal 的，所以每次创建都是新对象
        nums[2] =4;
        p.setName("jack");
        car.setName("BMW");
    }
}
