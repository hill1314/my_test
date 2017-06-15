package com.gupao.lessons.designPatterns.d2_factory.abstr;


import com.gupao.lessons.designPatterns.d2_factory.Benz;
import com.gupao.lessons.designPatterns.d2_factory.Car;

public class BenzFactory extends AbstractFactory {

	@Override
	public Car getCar() {
		return new Benz();
	}

}
