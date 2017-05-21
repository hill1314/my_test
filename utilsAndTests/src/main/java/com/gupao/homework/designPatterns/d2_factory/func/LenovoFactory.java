package com.gupao.homework.designPatterns.d2_factory.func;

import com.gupao.homework.designPatterns.d2_factory.Computer;
import com.gupao.homework.designPatterns.d2_factory.Lenovo;

/**
 * Created by Administrator on 2017/5/21.
 */
public class LenovoFactory implements ComputerFactory {
    @Override
    public Computer getComputer() {
        return new Lenovo();
    }
}
