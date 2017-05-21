package com.gupao.lessons.designPatterns.d2_factory.abstr;


import com.gupao.lessons.designPatterns.d2_factory.Car;

public class DefaultFactory extends AbstractFactory {

	private AudiFactory defaultFactory = new AudiFactory();
	
	public Car getCar() {
		return defaultFactory.getCar();
	}

}
