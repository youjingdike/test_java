package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.vo.Person;

public class TestReflection {
	
	/**
	 * ���Ի�ȡClass���� 
	 */
	private static void testGetClassObject(){
		//Object��getClass����
//		Person p = new Person("��7");
//		Class  c = p.getClass();        //p  Persion��  ��Class����
////		System.out.println(c.getName());
////		System.out.println(c.getPackage());
////		//ʹ������.class�ķ�ʽ
//		Class c2 = Person.class;
//		
//		System.out.println(c==c2);
//		System.out.println(c2.getName()); 
//		
//		//ʹ��Class.forName()��ʽ
		try {
			Class c3 = Class.forName("com.vo.Person");
			System.out.println(c3.getName());
			System.out.println(c3.getSimpleName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	


	/**
	 * �������Բ���
	 */
	private static void testField(){
		try {
			Class c = Class.forName("com.vo.Person");
////			Field[] fs = c.getFields();     // ֻ�ܻ��public��������Ϣ
////			Field[]  fs2=c.getDeclaredFields();  //�������������������Ϣ
////			for (int i = 0; i < fs2.length; i++) {
////				Field field = fs2[i];
////				System.out.println(field.getName());
////				System.out.println("�������ͣ�"+field.getType().getName()); //�����ǹ��в��С� 
////			}
//			//�õ�ĳ������ָ�����Ե�ֵ
			Field f = c.getField("name");   //Person��� name���Ե���Ϣ
			System.out.println(f.get(new Person("��7")));  
//
//			//�õ���ľ�̬����
//			Field f2= c.getField("age");
//			System.out.println("��ľ�̬����ֵ��"+f2.get(c));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	/**
	 * ����ִ�ж������ķ���
	 */
	private static void testMethod(){
		try {
			Class c = Class.forName("com.bjsxt.softrc.test.Person");
			Method[] ms = c.getMethods();
			for (int i = 0; i < ms.length; i++) {
				Method method = ms[i];
				System.out.println(method.getName()); 
			}
//			//���󷽷��ĵ���
			Person p = new Person("��7");
			Method m = c.getMethod("test1",null);
			m.invoke(p, null);
//			//�в�������ķ����ĵ���!
			Method m2 = c.getMethod("test2",int.class,String.class);
			m2.invoke(p,22,"aaa");
//			
//			//��ľ�̬��������
			Method m3 = c.getMethod("test3", int.class,String.class);
			m3.invoke(null, 33,"ddd");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	/**
	 * ���Թ����µĶ���
	 */
	private static void testConstructor(){
		try {
			Class c = Class.forName("com.vo.Person");
			Constructor con = c.getConstructor();
			Person p = (Person) con.newInstance();
			p.setName("ddd");
			System.out.println(p.getName());
						
//			Constructor con2 = c.getConstructor(String.class);
//			Person p2 = (Person) con2.newInstance("eee");
//			System.out.println(p2.getName());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * ����ĳ�������Ƿ�Ϊĳ����Ķ���
	 */
	private static void testIsInstance(){
		Person p = new Person();
		Class c = TestReflection.class;
		Class c2 = Person.class;
		System.out.println(c.isInstance(p));
		System.out.println(c2.isInstance(p));
	}
	public static void main(String[] args) {
//		testGetClassObject();
		testField();
//		testMethod();
//		testConstructor();
//		testIsInstance();
	}
}