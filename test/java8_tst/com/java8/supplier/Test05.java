package com.java8.supplier;

import java.util.function.BooleanSupplier;

public class Test05 {
	public static void main(String[] args) {  
        BooleanSupplier bs = () -> true;//û�в���  �����з��ؽ��  
        System.out.println(bs.getAsBoolean());  
  
        int x = 0, y= 1;  
        bs = () -> x > y;  
        System.out.println(bs.getAsBoolean());  
    }
}
