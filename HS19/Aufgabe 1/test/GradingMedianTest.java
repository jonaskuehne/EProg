

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.junit.Test;

public class GradingMedianTest {
	
	public final double delta = 0.0001d;
	
	@Test
	public void test1SingleValue() {		
		for(int i = 1; i <= 101; i++) {
			final Scanner scanner = new Scanner(Integer.toString(i));
			final double result = Median.median(scanner);
			assertEquals((double) i, result, delta);
		}
	}
	
	@Test
	public void test2SortedOdd() {
		{
			final Integer [] sorted1 = new Integer[] {9,20,301,343,370,491,501};
			testArrayInput(sorted1, 343);
		}
		
		{
			final Integer [] sorted2 = new Integer[] {10,1230,2301,2302,4032,5001,7005};
			testArrayInput(sorted2, 2302);
		}
		
		{
			final Integer [] sorted3 = new Integer[] {9001,10092,11000,13000,15000};
			testArrayInput(sorted3, 11000);
		}
	}
		
    @Test
    public void test3SortedEven() {
		{
			final Integer [] sorted1 = new Integer[] {21,32,901,1043,50001,90001};
			testArrayInput(sorted1, 972);
		}
		
		{
			final Integer [] sorted2 = new Integer[] {901, 5001, 9080, 71001, 72007, 81002, 82009, 90008};
			testArrayInput(sorted2, 71504);
		}

		
		{
			final Integer [] sorted3 = new Integer[] {35, 89, 308, 509, 510, 20008, 20009, 20010, 20011, 20023};
			testArrayInput(sorted3, 10259);
		}
	}
    
    @Test
    public void test4SortedDuplicate() {
		{
			final Integer [] sorted1 = new Integer[] {21,21,23,23,32,33,34};
			testArrayInput(sorted1, 23);
		}
		
		{
			final Integer [] sorted1 = new Integer[] {21,23,32,33,34};
			testArrayInput(sorted1, 32);
		}
	}
		
	@Test
	public void test5UnsortedOdd() {
		{
			final Integer [] unsorted1 = new Integer[] {349, 21, 492, 502, 371, 302, 10};
			testArrayInput(unsorted1, 349);
		}
		
		{
			final Integer [] unsorted2 = new Integer[] {7006,11,5002,4033,2303,2308,1237};
			testArrayInput(unsorted2, 2308);
		}
		
		{
			final Integer [] unsorted3 = new Integer[] {15001, 13001, 10092, 1, 20000, 11001, 9001};
			testArrayInput(unsorted3, 11001);
		}
	}
	
	@Test
	public void test6UnsortedEven() {
		{
			final Integer [] unsorted1 = new Integer[] {350, 21, 492, 502, 371, 302, 10, 25};
			testArrayInput(unsorted1, 326);
		}
		
		{
			final Integer [] unsorted2 = new Integer[] {7006,11,5002,4033,2304,2308,1237,29};
			testArrayInput(unsorted2, 2306);
		}
		
		{
			final Integer [] unsorted3 = new Integer[] {15001, 13001, 10093, 1, 20000, 11001, 9001,102};
			testArrayInput(unsorted3, 10547);
		}
	}
	
	@Test
	public void test7UnsortedEven() {
		{
			final Integer [] unsorted1 = new Integer[] {401, 21, 12309, 4901, 8532, 2, 1, 0, -10, -100};				
			testArrayInput(unsorted1, 11.5);
		}
		
		{
			final Integer [] unsorted3 = new Integer[] {89, -10, -11, 12, 15, 209, -301, -500, 1024, 1029};
			testArrayInput(unsorted3, 13.5);
		}
		
		{
			final Integer [] unsorted4 = new Integer[] {1005, -10, -11, -132, 1030, 1019, 1024, -430, -10,1020};
			testArrayInput(unsorted4, 497.5);
		}
	}
	
	
	@Test
	public void test8UnsortedDuplicate() {
		{
			final Integer [] unsorted1 = new Integer[] {100, -10, 1245, 953, 1245};				
			testArrayInput(unsorted1, 953);
		}
		
		{
			final Integer [] unsorted2 = new Integer[] {-100,-1000,1231,-1000,-1000};				
			testArrayInput(unsorted2, -1000);
		}
		
		{
			final Integer [] unsorted3 = new Integer[] {-100,-1000,1231,-1000,-1000,421,100};				
			testArrayInput(unsorted3, -100);
		}	
	}
	
	@Test 
	public void test9Large() {
		testLarge(201,10);
		testLarge(301,10);
		testLarge(401, 50);
	}
	
	public void testLarge(final int size, final int step) {		
		final Integer [] input = new Integer[size];
		
		for(int i = 0; i < size; i++) {
			if(i%5 == 1) {
				input[i] = input[i-1];
			} else {
				input[i] = size-i*step;	
			}			
		}
		
		if(size % 2 == 0) {
			final int medianIdxOrigLeft = size/2;
			final int medianIdxOrigRight = (size-1)/2;
			
			final Integer outlier =  input[medianIdxOrigLeft]+1;
			input[size-2] = outlier;
			
			double median = ((double) (outlier+input[medianIdxOrigRight]))/2;
			
			testArrayInput(input, median);
			
		} else {
			final int medianIdxOrig = (size-1)/2;		
			final Integer outlier = input[medianIdxOrig]+1;
			input[size-2] = outlier;
			
			
			testArrayInput(input, outlier);
		}
		
	}
	
	
	public void testArrayInput(final Integer [] input, final double expectedResult) {
		List<Integer> integerList = Arrays.asList(input);
		Collections.sort(integerList);		
		
		final String sourceStr = Arrays.stream(input).map(String::valueOf).collect(Collectors.joining(" "));
        final Scanner scanner = new Scanner(sourceStr);
        final double result = Median.median(scanner);
        assertEquals(expectedResult, result, delta);
	}

}
