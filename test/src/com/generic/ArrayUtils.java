package com.generic;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayUtils {
	
	/**
	 * ��һ���䳤����ת��Ϊ�б����ҳ��ȿɱ�
	 * @param <T>
	 * @param t
	 * @return
	 */
	public static <T> List<T> asList(T...t) {
		List<T> list = new ArrayList<T>();
		Collections.addAll(list, t);
		return list;
	}
	
	/**
	 * ������Ҫָ������
	 * @param list
	 */
	public static <E> void read(List<? extends E> list) {
		System.out.println("**************");
		for (E e:list) {//�������������ͱ�����E���������ת����E,����û��ת��
			System.out.println(e);
		}
	}
	
	/**
	 * д����Ҫָ������
	 * @param list
	 */
	public static void write(List<? super BigDecimal> list) {
		list.add(new BigDecimal("123"));//Ҫ���BigDecimal����,�������ͱ��������ĸ���������
		read(list);
	}
	
	/*public static void read(String[] listArr) {
		for (String e:listArr) {//�������������ͱ�����E���������ת����E,����û��ת��
			System.out.println(e);
		}
	}*/
	
	/**
	 * ѭ������
	 * @param arr
	 */
	public static <E> void read(E[] arr) {
		for (E e:arr) {
			System.out.println(e+":read method");
		}
	}
	
	/**
	 * ��listת������Ӧ <i><b>����</b></i> ������
	 * @param list
	 * @param tClass
	 * @return
	 */
	public static <T> T[] toArrays(List<T> list,Class<T> tClass) {
		if (list == null || tClass == null) {
			return null;
		}
		T[] t = (T[])Array.newInstance(tClass, list.size());
		for (int i=0,len=list.size();i<len;i++) {
			t[i] = list.get(i);
		}
		return t;
		
	}
	
	public static void testList(List<Integer> list) {
		
	}
}
