package com.gupao.lessons.designPatterns.d2_factory.simple;


import com.gupao.lessons.designPatterns.d2_factory.Car;

public class SimpleFactoryTest {


	public static void main(String[] args) {
	
		Car car = new SimpleFactory().getCar("Audi");
		System.out.println(car.getName());
		
	}
	
}
