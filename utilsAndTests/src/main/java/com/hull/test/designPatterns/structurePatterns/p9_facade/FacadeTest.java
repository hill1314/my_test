package com.hull.test.designPatterns.structurePatterns.p9_facade;

/**
 * @author hull
 * @title
 * @description
 * @date 2017/4/28 17:21
 */
public class FacadeTest {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startUp();
        computer.shutDown();
    }
}
