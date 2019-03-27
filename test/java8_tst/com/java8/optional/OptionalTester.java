package com.java8.optional;

import java.util.Optional;
/**
 * Optional��
 * 1.map()�ɼ�������
 * 2.����ʹ��orElse(),orElseGet(),��Ҫʹ��get(),���Ҫʹ��get(),�����ȵ���isPresent();
 * 3.Optional ��Ҫ�������Ի��Ƿ�������
 * 4.Optional ��Ҫ������������
 * @author xq
 * @date 2018��6��30��
 */
public class OptionalTester {
	public static void main(String[] args) {
		test1();
//		test2();
	}
	
	private static void test1() {
//		Optional< String > fullName = Optional.ofNullable( null );
		Optional< String > fullName = Optional.ofNullable( "testestset" );
		System.out.println( "Full Name is set? " + fullName.isPresent() );        
		System.out.println( "Full Name: " + fullName.orElseGet( () -> "[none]" ) ); 
		System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
	}
	
	private static void test2() {
		Optional< String > firstName = Optional.of( "Tom" );
		System.out.println( "First Name is set? " + firstName.isPresent() );        
		System.out.println( "First Name: " + firstName.orElseGet( () -> "[none]" ) ); 
		System.out.println( firstName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
		System.out.println();
	}
}
