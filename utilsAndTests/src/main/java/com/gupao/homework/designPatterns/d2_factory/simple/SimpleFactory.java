package com.gupao.homework.designPatterns.d2_factory.simple;

import com.gupao.homework.designPatterns.d2_factory.Computer;
import com.gupao.homework.designPatterns.d2_factory.Dell;
import com.gupao.homework.designPatterns.d2_factory.IBM;
import com.gupao.homework.designPatterns.d2_factory.Lenovo;

/**
 * Created by Administrator on 2017/5/21.
 * 简单工厂模式的工厂类一般是使用静态方法，通过接收的参数的不同来返回不同的对象实例
 */
public class SimpleFactory {
    public static Computer getComputer(String brand){
        Computer computer = null;
        if("Lenovo".equalsIgnoreCase(brand)){
            computer = new Lenovo();
        }else if("IBM".equalsIgnoreCase(brand)){
            computer = new IBM();
        }else if("Dell".equalsIgnoreCase(brand)){
            computer = new Dell();
        }else{
            System.out.println("no this brand this moment");
        }
        return computer;
    }
}
