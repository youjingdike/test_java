package com.java8.function;

import java.util.function.Function;

public class FunctionTester {
	public static void main(String[] args) {  
        //�򵥵�,ֻ��һ��  
        Function<String, String> function1 = (x) -> "result1: " + x;  
  
        //��׼��,�л�����, return, �ֺ�.  
        Function<String, String> function2 = (x) -> {  
            return "result2: " + x;  
        };  
  
        System.out.println(function1.apply("98")); 
        System.out.println();
        System.out.println(function1.andThen(function2).apply("100"));//��ִ��function1 Ȼ��������Ϊ�������ݵ�function2��  
        System.out.println();
        System.out.println(function2.compose(function1).apply("102"));//��ִ��function1 ��ִ��function2  
        System.out.println();
        System.out.println(Function.identity());  
    }  
}
