package com.gupao.lessons.designPatterns.d2_factory.abstr;


import com.gupao.lessons.designPatterns.d2_factory.Bmw;
import com.gupao.lessons.designPatterns.d2_factory.Car;

public class BmwFactory extends AbstractFactory {

	@Override
	public Car getCar() {
		return new Bmw();
	}

}
