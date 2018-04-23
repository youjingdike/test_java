package com.java8.supplier;

import java.util.Objects;
import java.util.function.Supplier;

public class Test02 {
	
	public static SunPower produce(Supplier<SunPower> supp) {  
        return supp.get();  
    }  
  
    public static void main(String[] args) {  
        SunPower power = new SunPower();  
  
        SunPower p1 = produce(() -> power);//ֻ���ʼ��һ�� ���ֻ�����һ�����  
        SunPower p2 = produce(() -> power);  
  
        System.out.println("Check the same object? " + Objects.equals(p1, p2));  
    }
}

class SunPower {  
    public SunPower() {  
        System.out.println("Sun Power initialized..");  
    }  
}