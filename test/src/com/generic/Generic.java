package com.generic;

import java.util.List;

public class Generic {

	//����һ  
	public static <T> void get(List<? extends T> list)  {  
	    list.get(0);
	}
	
	//������  
	public static void set(List<? super B> list, B b)  
	{  
	    list.add(b);  
	} 

}