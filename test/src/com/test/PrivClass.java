package com.test;

public class PrivClass {
	private String name;
	
	/**
	 * private���εģ���������ͬ����ķ�����ʹ��
	 * @param ot
	 */
	public void comp(PrivClass ot) {
		ot.name = this.name;
	}
	
}
