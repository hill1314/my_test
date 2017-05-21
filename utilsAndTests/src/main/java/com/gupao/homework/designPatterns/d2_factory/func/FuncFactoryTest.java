package com.gupao.homework.designPatterns.d2_factory.func;

import com.gupao.homework.designPatterns.d2_factory.Computer;

/**
 * Created by Administrator on 2017/5/21.
 * 工厂方法是针对每一种产品提供一个工厂类。通过不同的工厂实例来创建不同的产品实例。
 在同一等级结构中，支持增加任意产品。
 */
public class FuncFactoryTest {
    public static void main(String[] args) {
        ComputerFactory factory = new IBMFactory();
        Computer computer = factory.getComputer();
        computer.getComputerBrand();
    }
}
