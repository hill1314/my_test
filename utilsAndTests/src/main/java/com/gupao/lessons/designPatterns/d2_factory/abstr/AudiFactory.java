package com.gupao.lessons.designPatterns.d2_factory.abstr;


import com.gupao.lessons.designPatterns.d2_factory.Audi;
import com.gupao.lessons.designPatterns.d2_factory.Car;

//具体的业务逻辑封装
public class AudiFactory extends AbstractFactory {

	@Override
	public Car getCar() {
		return new Audi();
	}

}
