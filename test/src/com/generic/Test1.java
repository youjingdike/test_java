package com.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

public class Test1 {
	
	public static  <T extends Comparable<T>> void mySort1(List<T> list) {
		Collections.sort(list);
	}
	
	public static  <T extends Comparable<? super T>> void mySort2(List<T> list) {
		Collections.sort(list);
	}
	
	public static void main(String[] args) {
		Demo<ArrayList> p = null;//������ȷ
//		Demo<Collection> c = null;//����,����������
//		Demo1<GregorianCalendar> p1 = null;//���뱨��,��ΪGregorianCalendarû��ʵ��Comparable<GregorianCalendar>,ֻ�Ǽ̳���Comparable<Calendar>
		Demo2<GregorianCalendar> p2 = null;//������ȷ,��ΪGregorianCalendar�̳���Comparable<Calendar>��Calendar��GregorianCalendar�ĸ���
		
		List<Animal> animals = new ArrayList<Animal>();
		animals.add(new Animal(12));
		animals.add(new Dog(11));
		animals.add(new Dog(1));
		animals.add(new Dog(111));
		
		List<Dog> dogs = new ArrayList<Dog>();
		dogs.add(new Dog(121));
		dogs.add(new Dog(12));
		dogs.add(new Dog(122));
		dogs.add(new Dog(1222));
		
		mySort1(animals);
		ArrayUtils.read(animals);
//		mySort1(dogs);//����,����������
		
		mySort2(animals);
		ArrayUtils.read(animals);
		mySort2(dogs);
		ArrayUtils.read(dogs);
	}
}

class Demo<T extends List>{
	
}

class Demo1<T extends Comparable<T>>{
	
}

class Demo2<T extends Comparable<? super T>>{
	
}

class Animal implements Comparable<Animal> {
	protected int age;
	
	public Animal(int age) {
		this.age = age;
	}

	public int compareTo(Animal o) {
		return this.age - o.age;
	}
	
	public String toString() {
		return this.age+"";
	}
}

class Dog extends Animal {

	public Dog(int age) {
		super(age);
	}
	
}