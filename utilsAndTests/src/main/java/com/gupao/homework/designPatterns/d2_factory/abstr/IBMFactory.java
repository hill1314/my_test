package com.gupao.homework.designPatterns.d2_factory.abstr;

import com.gupao.homework.designPatterns.d2_factory.Computer;
import com.gupao.homework.designPatterns.d2_factory.IBM;

/**
 * Created by Administrator on 2017/5/21.
 */
public class IBMFactory implements AbstractFactory{
    @Override
    public Computer getComputer() {
        return new IBM();
    }

    @Override
    public Computer getNoteBook() {
        return new IBM("notebook");
    }
}
