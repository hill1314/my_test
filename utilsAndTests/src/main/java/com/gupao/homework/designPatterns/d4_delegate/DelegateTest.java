package com.gupao.homework.designPatterns.d4_delegate;

/**
 * Created by Administrator on 2017/5/21.
 */
public class DelegateTest {
    public static void main(String[] args) {
        Contractor contractor = new Contractor(new WorkerA());
        contractor.working();
    }
}
