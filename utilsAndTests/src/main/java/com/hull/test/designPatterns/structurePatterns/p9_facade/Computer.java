package com.hull.test.designPatterns.structurePatterns.p9_facade;

/**
 * @author hull
 * @title
 * @description
 * @date 2017/4/28 17:19
 */
public class Computer {
    private CPU cpu;
    private Memory memory;
    private Disk disk;

    public Computer(){
        cpu = new CPU();
        memory = new Memory();
        disk = new Disk();
    }

    public void startUp(){
        System.out.println("start the computer!");
        cpu.startUp();
        memory.startUp();
        disk.startUp();
        System.out.println("start computer finished!");
    }

    public void shutDown(){
        System.out.println("begin to close the computer!");
        cpu.shutDown();
        memory.shutDown();
        disk.shutDown();
        System.out.println("computer closed!");
    }
}
