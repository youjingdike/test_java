package com.java8.supplier;

import java.util.function.Supplier;

public class Test03 {
	private static Employee employee(Supplier<Employee> supplier){  
        return supplier.get();  //��û��д���캯����ʱ��  ����Ҫ��дtoString����  
    }  
  
    public static void main(String[] args) {  
        System.out.println(employee(Employee::new));// A EMPLOYEE  
    }
}

class Employee{  
    @Override  
    public String toString() {  
        return "A EMPLOYEE";  
    }  
}