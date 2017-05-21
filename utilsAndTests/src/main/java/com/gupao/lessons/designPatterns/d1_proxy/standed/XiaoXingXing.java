package com.gupao.lessons.designPatterns.d1_proxy.standed;

/**
 * Created by Administrator on 2017/5/13.
 */
public class XiaoXingXing implements Person{
    private String sex = "女";
    private String name = "小星星";

    @Override
    public void findLove() {
        System.out.println("我叫"+this.name+",性别："+this.sex+",我要高富帅");
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
