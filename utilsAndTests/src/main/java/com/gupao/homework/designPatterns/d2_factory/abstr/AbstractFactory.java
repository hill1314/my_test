package com.gupao.homework.designPatterns.d2_factory.abstr;

import com.gupao.homework.designPatterns.d2_factory.Computer;

/**
 * Created by Administrator on 2017/5/21.
 */
public interface AbstractFactory {

    public Computer getComputer();
    public Computer getNoteBook();

}
