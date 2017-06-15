package com.hull.test.designPatterns.createPatterns.p4_Builder;

/**
 * Created by hull on 2017/3/1.
 */
public class P4Test {
    public static void main(String[] args) {
        Builder builder = new Builder();
        builder.produceMailSender(10);
    }
}
