package com.java8.functionalInter;
/**
 * 1.����ʽ���ֻ����һ����ͨ����
 * 2.Ĭ�Ϸ����뾲̬��������Ӱ�캯��ʽ�ӿڵ���Լ
 * @author xingqian
 *
 */
@FunctionalInterface
public interface FunctionalDefaultMethodsInter {
	void method();
	
    default void defaultMethod() {            
    }
    
    static void staticMethod() {
    }
    
}
