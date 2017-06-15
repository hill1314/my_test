package com.gupao.lessons.designPatterns.d2_factory.func;


import com.gupao.lessons.designPatterns.d2_factory.Benz;
import com.gupao.lessons.designPatterns.d2_factory.Car;

public class BenzFactory implements Factory {

	@Override
	public Car getCar() {
		return new Benz();
	}

}
