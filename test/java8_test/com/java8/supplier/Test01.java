package com.java8.supplier;

import java.util.function.Supplier;

public class Test01 {
	public static void main(String[] args) {  
        Supplier<String> supplier=()->"Supplier";//������������ ���û�б�Ҫ��дtoString()  
        System.out.println(supplier.get());  
    }
}
