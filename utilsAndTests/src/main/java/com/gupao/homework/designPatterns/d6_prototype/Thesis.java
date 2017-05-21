package com.gupao.homework.designPatterns.d6_prototype;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/21.
 * 论文/作文
 */
public class Thesis implements Serializable{
    private String title;//论文标题
    private ArrayList<String> rules ;//论文要求

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getRules() {
        return rules;
    }

    public void setRules(ArrayList<String> rules) {
        this.rules = rules;
    }
}
