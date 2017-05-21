package com.gupao.homework.designPatterns.d6_prototype;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/21.
 * 考卷
 */
public class ExaminationPaper implements Serializable{
    protected String type ;//试卷类型
    protected int totalScore;//总分

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
