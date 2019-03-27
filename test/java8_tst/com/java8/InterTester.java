package com.java8;

import java.util.function.Supplier;

import org.junit.Test;

public class InterTester {
	
	@Test
	public void test() {
		/*
		 * 1.����ʽ�ӿھ���һ������һ����������ͨ�ӿڡ��������Ľӿڣ����Ա���ʽת��Ϊlambda���ʽ��
		 * 2.��Ҫ��ס��һ�����ǣ�Ĭ�Ϸ����뾲̬��������Ӱ�캯��ʽ�ӿڵ���Լ����������ʹ�ã�
		 */
		Defaulable defaulable = DefaulableFactory.create( DefaultableImpl::new );
	    System.out.println( defaulable.notRequired() );
	         
	    defaulable = DefaulableFactory.create( OverridableImpl::new );
	    System.out.println( defaulable.notRequired() );
	}
	
	/**
	 * 1.����ʽ�ӿ�����ֻ��һ����ͨ����
	 * 2.Ĭ�Ϸ����뾲̬��������Ӱ�캯��ʽ�ӿڵ���Լ
	 * @author xingqian
	 *
	 */
	@FunctionalInterface
	private interface FunctionalDefaultMethods {
		void method();
		/*
		 * 1.Ĭ�Ϸ���:������Ը�д,Ҳ���Բ���д
		 * @return
		 */
		default String  defaultMethod() { 
	        return "defaultMethod"; 
	    }
	}
	
	private interface Defaulable {
		/*
		 * 1.Ĭ�Ϸ���:������Ը�д,Ҳ���Բ���д
		 * 
		 * @return
		 */
		// Interfaces now allow default methods, the implementer may or 
	    // may not implement (override) them.
		default String notRequired() { 
			return "Default implementation"; 
		}
	}
	
	private static class DefaultableImpl implements Defaulable {
	}
	     
	private static class OverridableImpl implements Defaulable {
	    @Override
	    public String notRequired() {
	        return "Overridden implementation";
	    }
	}
	
	private interface DefaulableFactory {
	    // Interfaces now allow static methods
	    static Defaulable create( Supplier< Defaulable > supplier ) {
	        return supplier.get();
	    }
	}
}

