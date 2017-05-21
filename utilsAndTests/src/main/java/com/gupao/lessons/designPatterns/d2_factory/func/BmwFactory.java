package com.gupao.lessons.designPatterns.d2_factory.func;


import com.gupao.lessons.designPatterns.d2_factory.Bmw;
import com.gupao.lessons.designPatterns.d2_factory.Car;

public class BmwFactory implements Factory {

	@Override
	public Car getCar() {
		return new Bmw();
	}

}
