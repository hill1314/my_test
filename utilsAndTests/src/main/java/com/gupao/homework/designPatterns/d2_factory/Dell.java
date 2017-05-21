package com.gupao.homework.designPatterns.d2_factory;

/**
 * Created by Administrator on 2017/5/21.
 */
public class Dell implements Computer{

    private String type = "desktop";

    public Dell(){}

    public Dell(String type){
        this.type = type;
    }

    @Override
    public void getComputerBrand() {
        System.out.println("this is a Dell "+type+"  Computer!");
    }
}
