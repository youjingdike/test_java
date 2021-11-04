package com.spi.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
//        System.out.println("classPath:"+System.getProperty("java.class.path"));

        ServiceLoader<IOperation> operations = ServiceLoader.load(IOperation.class);
        List<IOperation> result = new LinkedList<>();
        Iterator<IOperation> operationIterator = operations.iterator();
        operationIterator.forEachRemaining(result::add);

        while (operationIterator.hasNext()) {
  
            IOperation operation = operationIterator.next();
  
            System.out.println(operation.getClass().getName());  
            System.out.println(operation.operation(6, 3));  
  
        }
	}
}
