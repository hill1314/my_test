package com.hull.test.designPatterns.structurePatterns.p9_facade;

/**
 * @author hull
 * @title
 * @description
 * @date 2017/4/28 17:12
 */
public class CPU implements Hardware{
    private String type = "CPU";
    @Override
    public void startUp() {
        System.out.println( this.type + " start up !");
    }

    @Override
    public void shutDown() {
        System.out.println( this.type + " shut down !");
    }
}
