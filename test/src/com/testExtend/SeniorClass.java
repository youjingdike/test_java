package com.testExtend;

public class SeniorClass {
    public SeniorClass(){
        System.out.println(toString()); //may throw NullPointerException if overridden
                                        //���������д���toString����,�������ڶ����д���෽����ʱ��Ҫע��
                                        //new�ĸ�����͵����ĸ������toString����
    }
    public String toString(){
      return "IAmSeniorClass";
    }
    
}
