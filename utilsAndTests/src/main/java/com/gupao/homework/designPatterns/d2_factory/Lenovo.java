package com.gupao.homework.designPatterns.d2_factory;

/**
 * Created by Administrator on 2017/5/21.
 */
public class Lenovo implements Computer{

    private String type = "desktop";

    public Lenovo(){}

    public Lenovo(String type){
        this.type = type;
    }

    @Override
    public void getComputerBrand() {
        System.out.println("this is a Lenovo "+type+" computer!");
    }
}
