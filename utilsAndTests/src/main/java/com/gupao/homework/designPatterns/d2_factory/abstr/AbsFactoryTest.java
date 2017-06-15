package com.gupao.homework.designPatterns.d2_factory.abstr;

/**
 * Created by Administrator on 2017/5/21.
 */
public class AbsFactoryTest {
    public static void main(String[] args) {
        AbstractFactory factory = new IBMFactory();
        factory.getNoteBook();
    }
}
