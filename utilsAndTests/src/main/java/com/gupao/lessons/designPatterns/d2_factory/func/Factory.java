package com.gupao.lessons.designPatterns.d2_factory.func;


import com.gupao.lessons.designPatterns.d2_factory.Car;

//�����ӿڣ��Ͷ��������й�����ִ�б�׼
public interface Factory {

	//����������·��׼
	//β���ŷű�׼
	//�����豸��ȫϵ��
	//�����䱸��ȫ������ȫ����
	//��̥����ĥ�̶�
	Car getCar();
	
}
