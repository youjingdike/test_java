package com.java8;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamTester {
	private enum Status {
        OPEN, CLOSED
    };
	
    private static final class Task {
        private final Status status;
        private final Integer points;
 
        Task( final Status status, final Integer points ) {
            this.status = status;
            this.points = points;
        }
         
        public Integer getPoints() {
            return points;
        }
         
        public Status getStatus() {
            return status;
        }
         
        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points );
        }
    }
    
	public static void main(String[] args) {
//		test1();
//		testInit();
//		testMap();
		testFilter();
	}
	
	private static void test1() {
		final Collection< Task > tasks = Arrays.asList(
			    new Task( Status.OPEN, 5 ),
			    new Task( Status.OPEN, 13 ),
			    new Task( Status.CLOSED, 8 ) 
			);
		// Calculate total points of all active tasks using sum()
		final long totalPointsOfOpenTasks = tasks
		    .stream()
		    .filter( task -> task.getStatus() == Status.OPEN )
		    .mapToInt( Task::getPoints )
		    .sum();
		         
		System.out.println( "Total points: " + totalPointsOfOpenTasks );
		
		// Calculate total points of all tasks
		final double totalPoints = tasks
		   .stream()
		   .parallel()
		   .map( task -> task.getPoints() ) // or map( Task::getPoints ) 
		   .reduce( 0, Integer::sum );
		     
		System.out.println( "Total points (all tasks): " + totalPoints );
		
		
		// Group tasks by their status
		final Map< Status, List< Task > > map = tasks
		    .stream()
		    .collect( Collectors.groupingBy( Task::getStatus ) );
		System.out.println( map );
		
		// Calculate the weight of each tasks (as percent of total points) 
		final Collection< String > result = tasks
		    .stream()                                        // Stream< Task >
		    .mapToInt( Task::getPoints )                     // IntStream
		    .asLongStream()                                  // LongStream
		    .mapToDouble( points -> points / totalPoints )   // DoubleStream
		    .boxed()                                         // Stream< Double >
		    .mapToLong( weigth -> ( long )( weigth * 100 ) ) // LongStream
		    .mapToObj( percentage -> percentage + "%" )      // Stream< String> 
		    .collect( Collectors.toList() );                 // List< String > 
		         
		System.out.println( result );
		
		final Path path = new File( "D:\\log\\File\\test.txt" ).toPath();
//		try( Stream< String > lines = Files.lines( path, StandardCharsets.UTF_8 ) ) {
		try( Stream< String > lines = Files.lines( path, Charset.forName("gbk") ) ) {
		    lines.onClose( () -> System.out.println("Done!") ).forEach( System.out::println );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void testInit() {
		//��ʼ��һ������
	    Stream<String> stream = Stream.of("a", "b", "c");
	    printStream(stream);
	    
	    //����ת��Ϊһ������
	    String [] strArray = new String[] {"a", "b", "c"};
//	    stream = Stream.of(strArray);
	    stream = Arrays.stream(strArray);
	    printStream(stream);
	    
	    //���϶���ת��Ϊһ������Collections����
	    List<String> list = Arrays.asList(strArray);
	    stream = list.stream();
	    printStream(stream);
	    
	}
	
	private static void testMap() {
		//1.1������ת��Ϊ��д:
		List<String> wordList = Arrays.asList("Hello","World");
		List<String> output = wordList.stream().
				map(String::toUpperCase).
				collect(Collectors.toList());
		printCollection(output,"output");
		
		//1.2��ƽ������
		List<Integer> nums = Arrays.asList(1, 2, 3, 4);
		List<Integer> squareNums = nums.stream().
				map(n -> n * n).
				collect(Collectors.toList());
		printCollection(squareNums,"squareNums");
	}
	
	private static void testFilter() {
		//2.1���õ����в�Ϊ�յ�String
		List<String> filterLists = new ArrayList<>();
		filterLists.add("");
		filterLists.add("a");
		filterLists.add("b");
		List afterFilterLists = filterLists.stream()
		       .filter(s -> !s.isEmpty())
		        .collect(Collectors.toList());
		printCollection(afterFilterLists,"afterFilterLists");
		
		//limit ���� Stream ��ǰ�� n ��Ԫ�أ�skip �����ӵ�ǰ n ��Ԫ��:
		//ע��skip��limit����˳���ϵ�ģ�
		//����ʹ��skip(2)���������ϵ�ǰ���������ص�Ϊc��d��e��f,Ȼ�����limit(3)�᷵��ǰ3����������󷵻ص�c,d,e
		List<String> forEachLists = new ArrayList<>();
		forEachLists.add("a");
		forEachLists.add("b");
		forEachLists.add("c");
		forEachLists.add("d");
		forEachLists.add("e");
		forEachLists.add("f");
		List<String> limitLists = forEachLists.stream().skip(2).limit(3).collect(Collectors.toList());
		List<String> limitLists1 = forEachLists.stream().limit(3).skip(2).collect(Collectors.toList());
		printCollection(limitLists,"limitLists");
		printCollection(limitLists1,"limitLists1");
		
		//sort���ԶԼ����е�����Ԫ�ؽ�������max��min����Ѱ�ҳ�������������С��Ԫ�أ���distinct����Ѱ�ҳ����ظ���Ԫ�أ�
		//5.1����һ�����Ͻ�������
		List<Integer> sortLists = new ArrayList<>();
		sortLists.add(1);
		sortLists.add(4);
		sortLists.add(6);
		sortLists.add(3);
		sortLists.add(2);
		List<Integer> afterSortLists = sortLists.stream().sorted((In1,In2)->
		       In1-In2).collect(Collectors.toList());
		printCollection(afterSortLists,"afterSortLists");
		//5.2���õ����г�������ֵ��
		List<String> maxLists = new ArrayList<>();
		maxLists.add("a");
		maxLists.add("b");
		maxLists.add("c");
		maxLists.add("d");
		maxLists.add("e");
		maxLists.add("f");
		maxLists.add("hahaha");
		int maxLength = maxLists.stream().mapToInt(s->s.length()).max().getAsInt();
		System.out.println("�ַ���������ĳ���Ϊ"+maxLength);
		//5.3����һ�����Ͻ��в��أ����е�distinct()�������ҳ�stream��Ԫ��equal()������ͬ��Ԫ�أ�������ͬ��ȥ�����������ؼ�Ϊa,c,d��
		List<String> distinctList = new ArrayList<>();
		distinctList.add("a");
		distinctList.add("a");
		distinctList.add("c");
		distinctList.add("d");
		List<String> afterDistinctList = distinctList.stream().distinct().collect(Collectors.toList());
		printCollection(afterDistinctList,"afterDistinctList");
		
		/*
		 * �е�ʱ������ֻ��Ҫ�жϼ������Ƿ�ȫ�����������������жϼ������Ƿ�������������Ԫ�أ���ʱ��Ϳ���ʹ��match������
		 * allMatch��Stream ��ȫ��Ԫ�ط��ϴ���� predicate������ true
		 * anyMatch��Stream ��ֻҪ��һ��Ԫ�ط��ϴ���� predicate������ true
		 * noneMatch��Stream ��û��һ��Ԫ�ط��ϴ���� predicate������ true
		 */
		//6.1���жϼ�������û��Ϊ��c����Ԫ�أ�
		List<String> matchList = new ArrayList<>();
		matchList.add("a");
		matchList.add("a");
		matchList.add("c");
		matchList.add("d"); 
		boolean isExits = matchList.stream().anyMatch(s -> s.equals("cd"));
		System.out.println(isExits);
		
		//6.2���жϼ������Ƿ�ȫ��Ϊ�գ�
		List<String> matchList1 = new ArrayList<>();
		matchList1.add("a");
		matchList1.add("");
		matchList1.add("a");
		matchList1.add("c");
		matchList1.add("d");
		boolean isNotEmpty = matchList1.stream().noneMatch(s -> s.isEmpty());
		System.out.println(isNotEmpty);
	}
	
	@Test
	public void testReduce() {
		/*reduce��������ʵ�ִ�һ��Ԫ��������һ��ֵ��sum()��max()��min()��count()�ȶ���reduce�����������ǵ�����Ϊ����ֻ����Ϊ���á�
		 * reduce()�ķ���������������д��ʽ��
		 		//����1��δ�����ʼֵ���Ӷ���һ��ִ�е�ʱ���һ��������ֵ��Stream�ĵ�һ��Ԫ�أ��ڶ���������Stream�ĵڶ���Ԫ��
				Optional<T> reduce(BinaryOperator<T> accumulator) 
				
				//����2�������˳�ʼֵ���Ӷ���һ��ִ�е�ʱ���һ��������ֵ�ǳ�ʼֵ���ڶ���������Stream�ĵ�һ��Ԫ��
				T reduce(T identity, BinaryOperator<T> accumulator)
				
				//
				<U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
		* ��Ȼ��������Խ��Խ���������岻���ı䣬��Ĳ���ֻ��Ϊ��ָ����ʼֵ������identity����
		* ������ָ������ִ��ʱ������ֽ���ĺϲ���ʽ������combiner����reduce()��õĳ������Ǵ�һ��ֵ������һ��ֵ��
		* ����ô���ӵĺ���ȥ��һ��������Сֵ�����ǲ��Ǿ���������в�����ʵ��Ȼ����Ϊ���󡱺͡�С�����ߡ���͡���ʱ���в�ͬ������
		*/
		
		Stream<String> stream = Stream.of("I", "lovedddd", "you", "too");
		Optional<String> longest = stream.reduce((s1, s2) -> s1.length()>=s2.length() ? s1 : s2);
		//Optional<String> longest = stream.max((s1, s2) -> s1.length()-s2.length());
		System.out.println(longest.get());
		
		Stream<String> stm = Stream.of("I", "lovedddd", "you", "too");
		Optional<String> max = stm.max((s1, s2) -> s1.length()-s2.length());
		System.out.println(max.orElseGet(()->""));
		
		// �󵥴ʳ���֮��
		Stream<String> stream1 = Stream.of("I", "love", "you", "too");
		Integer lengthSum = stream1.reduce(0, // ��ʼֵ��// (1)
		        (sum, str) -> sum+str.length(), // �ۼ��� // (2)
		        (a, b) -> a+b); // ���ֺ�ƴ����������ִ��ʱ�Ż��õ� // (3)
		// int lengthSum = stream.mapToInt(str -> str.length()).sum();
		System.out.println(lengthSum);
		
		
		//�󵥴ʳ���֮��
		Stream<String> stream2 = Stream.of("I", "love", "you", "too");
//		Integer s_m = stream2.parallel()
		Integer s_m = stream2
			.reduce(new Integer(0), new BiFunction<Integer, String, Integer>() {
	
				@Override
				public Integer apply(Integer t, String u) {
					System.out.println("BiFunction");
					return t+u.length();
				}
				
			}, new BinaryOperator<Integer>(){
	
				@Override
				public Integer apply(Integer t, Integer u) {
					System.out.println("BinaryOperator");
					return t+u;
				}
			});
		
		System.out.println("s_m:"+s_m);
	}
	
	@Test
	public void testCollect() {
		// ��Streamת����������Map
		Stream<String> stream = Stream.of("I", "love", "you", "too");
		
//		List<String> list = stream.collect(Collectors.toList()); // (1)
//		Set<String> set = stream.collect(Collectors.toSet()); // (2)
		Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length)); // (3)
		
//		printCollection(list, "Collect");
//		printCollection(set, "Collect");
		printMap(map, "Collect");
	}
	
	private static void printStream(Stream<?> stream) {
		System.out.println("~~~~~~~~~~~~~~~");
		stream.forEach(System.out::println);
//		stream.forEach(s -> System.out.println(s));
	}
	
	private static void printCollection(Collection co,String operater) {
		System.out.println("***********");
		System.out.println(operater+":"+co);
	}
	
	private static void printMap(Map co,String operater) {
		System.out.println("***********");
		System.out.println(operater+":"+co);
	}
}
