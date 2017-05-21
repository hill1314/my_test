package com.gupao.homework.designPatterns.d2_factory.simple;

import com.gupao.homework.designPatterns.d2_factory.Computer;

/**
 * Created by Administrator on 2017/5/21.
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        Computer computer = SimpleFactory.getComputer("IBM");
        computer.getComputerBrand();
    }
}
