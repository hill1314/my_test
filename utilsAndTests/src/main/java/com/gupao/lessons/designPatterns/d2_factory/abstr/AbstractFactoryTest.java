package com.gupao.lessons.designPatterns.d2_factory.abstr;

public class AbstractFactoryTest {

	public static void main(String[] args) {
		
		DefaultFactory factory = new DefaultFactory();
		
		System.out.println(factory.getCar("Benz"));
		
		//���ģʽ�ľ���֮���������ڣ�����˱�д������˺͵��ô������˫����ʹ��
		//������ǵ�˫��
		
	}
}
