package com.gupao.homework.designPatterns.d6_prototype;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

/**
 * Created by Administrator on 2017/5/21.
 * 语文 试卷
 */
public class ChineseExamPaper extends ExaminationPaper implements Cloneable,Serializable{
    //论文
    private Thesis thesis;

    //设计试卷
    public ChineseExamPaper(){
        this.type="chinese";
        this.totalScore = 100;
        this.thesis = new Thesis();
        this.thesis.setTitle("where am I from ?");
    }


    public Object clone(){
        ChineseExamPaper copy = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            copy = (ChineseExamPaper) ois.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                bos.close();
                oos.close();
                bis.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return  copy;
    }

    //再打印一份试卷
    public void printAnother(){
        ChineseExamPaper paper = (ChineseExamPaper) clone();
        System.out.println("paper's type :"+paper.getType());
        System.out.println("paper's total score :"+paper.getTotalScore());
        System.out.println("paper's old one :"+ (this==paper));
        System.out.println("paper's thesis is old one :"+ (this.thesis==paper.thesis));
    }

}
