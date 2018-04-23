package com.java8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AnnotationTester {
	/*
	 * �����и�ʹ��@Repeatable( Filters.class )ע���ע����Filter��Filters������Filterע������飬
	 * ��Java�������������ó���Ա��ʶ��Filters�Ĵ��ڡ�
	 * �������ӿ�Filterable��ӵ��������Filter����û���ᵽFilter��ע��
	 */
	@Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    public @interface Filters {
        Filter[] value();
    }
     
    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    @Repeatable( Filters.class )
    public @interface Filter {
        String value();
    };
     
    @Filter( "filter1" )
    @Filter( "filter2" )
    public interface Filterable {        
    }
    
    public static void main(String[] args) {
    	/*
    	 * ������ص�API�ṩ���µĺ���getAnnotationsByType()�������ظ�ע�������
    	 * ����ע��Filterable.class.getAnnotation( Filters.class )������������󽫻᷵��Filters��ʵ����
    	 */
        for( Filter filter: Filterable.class.getAnnotationsByType( Filter.class ) ) {
            System.out.println( filter.value() );
        }
    }
}

