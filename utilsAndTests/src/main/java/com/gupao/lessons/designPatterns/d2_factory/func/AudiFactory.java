package com.gupao.lessons.designPatterns.d2_factory.func;


import com.gupao.lessons.designPatterns.d2_factory.Audi;
import com.gupao.lessons.designPatterns.d2_factory.Car;

public class AudiFactory implements Factory {

	@Override
	public Car getCar() {
		return new Audi();
	}

}
