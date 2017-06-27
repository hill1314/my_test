package com.hull.test.javabase.keyWords;

import org.springframework.util.Assert;

/**
 * Created by Administrator on 2017/5/29.
 */
public class AssertTest {
    public static void main(String[] args) {
        AssertTest a = null;
        Assert.isNull(a,"不能是null呀");
    }
}
