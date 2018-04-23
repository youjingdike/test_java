package com.java8;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LambdaTester {
	
	@Test
	public void testLambda() {
		//����򵥵���ʽ�У�һ��lambda�������ö��ŷָ��Ĳ����б��C>�����뺯���������ֱ�ʾ
		//�ڲ���ǰ��ֻ��ָ��Ԫ�ع�ͬ���������
		Object obj = null;
		Arrays.asList( "a",obj,"c").forEach((e) -> System.out.println(e));
		//��ע�����e���������ɱ������Ʋ�����ġ�ͬʱ����Ҳ����ͨ���Ѳ�����������������������е���ʽֱ�Ӹ�������������
		Arrays.asList( "a",obj,"c").forEach((Object e) -> System.out.println(e));
		Arrays.asList( "a","b","c").forEach((String e) -> System.out.println(e));
		//����������ж������Ļ����ͷ���{}��
		Arrays.asList( "a","b","c").forEach((String e) -> {
				System.out.println(e);
				System.out.println(e);
			});
		
	}
	
	@Test
	public void lambda1() {
		//Lambda����������ĳ�Ա������ֲ������������Щ��������final�Ļ������ǻᱻ������תΪfinal������Ч�ʸ��ߣ����磬������������Ƭ���ǵȼ۵ģ�
		String separator = ",";
		Arrays.asList( "a", "b", "d" ).forEach( 
		    ( String e ) -> System.out.print( e + separator ) );
		final String separator1 = "@";
		Arrays.asList( "a", "b", "d" ).forEach( 
		    ( String e ) -> System.out.print( e + separator1 ) );
	}
	
	@Test
	public void lambda2() {
		//Lambda���ܻ᷵��һ��ֵ������ֵ������Ҳ���ɱ������Ʋ�����ġ����lambda�ĺ�����ֻ��һ�еĻ�����ôû�б�Ҫ��ʽʹ��return��䡣������������Ƭ���ǵȼ۵ģ�
		List<String> asList = Arrays.asList( "a", "d", "b" );
		asList.sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
		asList.forEach(e -> System.out.println(e));
		
		List<String> asList1 = Arrays.asList( "a", "v", "nb" );
		asList1.sort( ( e1, e2 ) -> {
		    int result = e1.compareTo( e2 );
		    return result;
		} );
		asList1.forEach(e -> System.out.println(e));
	}
	
}

