package com.spi.test;

import java.util.Iterator;
import java.util.ServiceLoader;

import com.spi.inter.IOperation;
import com.spi.inter.impl.DivisionOperationImpl;
import com.spi.inter.impl.PlusOperationImpl;

public class Test {
	public static void main(String[] args) {
		/*IOperation plus = new PlusOperationImpl();  
		  
        IOperation division = new DivisionOperationImpl();  
  
        System.out.println(plus.operation(6, 3));  
  
        System.out.println(division.operation(6, 3));  
        System.out.println("~~~~~~~~~~~~~");*/
        
        ServiceLoader<IOperation> operations = ServiceLoader.load(IOperation.class);  
  
        Iterator<IOperation> operationIterator = operations.iterator();  
  
        System.out.println("classPath:"+System.getProperty("java.class.path"));  
  
        while (operationIterator.hasNext()) {  
  
            IOperation operation = operationIterator.next();  
  
            System.out.println(operation.getClass().getName());  
            System.out.println(operation.operation(6, 3));  
  
        }
	}
}
