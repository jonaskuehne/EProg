import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class GradingSeqAnalyzerTest {
	
	@Test
	public void test01NumbersOnlyOneNumber() {
		{
			Scanner scanner = new Scanner("1 1 1 1");
			SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
			List<SeqNumber> numbers = analyzer.getNumbers();
			assertNotNull(numbers);
			assertEquals(1, numbers.size());
			assertContainsNumber(1, Set.of(0,1,2,3), numbers);
		}
		
		{
			Scanner scanner = new Scanner("42 42");
			SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
			List<SeqNumber> numbers = analyzer.getNumbers();
			assertNotNull(numbers);
			assertEquals(1, numbers.size());
			assertContainsNumber(42, Set.of(0,1), numbers);
		}
	}
	
	@Test
	public void test02NumbersSingleton() {
		{
			Scanner scanner = new Scanner("5");
			SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
			List<SeqNumber> numbers = analyzer.getNumbers();
			assertNotNull(numbers);
			assertEquals(1, numbers.size());
			assertContainsNumber(5, Set.of(0), numbers);
		}
		
		{
			Scanner scanner = new Scanner("7");
			SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
			List<SeqNumber> numbers = analyzer.getNumbers();
			assertEquals(1, numbers.size());
			assertContainsNumber(7, Set.of(0), numbers);
		}
	}
	
	@Test
	public void test03NumbersNoRep() {
		Scanner scanner = new Scanner("4 3 2 5 200 98");
		SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
		List<SeqNumber> numbers = analyzer.getNumbers();
		assertNotNull(numbers);
		assertEquals(6, numbers.size());
		
		assertContainsNumber(4, Set.of(0), numbers);
		assertContainsNumber(3, Set.of(1), numbers);
		assertContainsNumber(2, Set.of(2), numbers);
		assertContainsNumber(5, Set.of(3), numbers);
		assertContainsNumber(200, Set.of(4), numbers);
		assertContainsNumber(98, Set.of(5), numbers);		
	}
	
	@Test
	public void test04NumbersNoRep() {
		Scanner scanner = new Scanner("20 9 7 21 19 32 33 34 72 36 37");
		SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
		List<SeqNumber> numbers = analyzer.getNumbers();
		assertNotNull(numbers);
		assertEquals(11, numbers.size());
		
		assertContainsNumber(20, Set.of(0), numbers);
		assertContainsNumber(9, Set.of(1), numbers);
		assertContainsNumber(7, Set.of(2), numbers);
		assertContainsNumber(21, Set.of(3), numbers);
		assertContainsNumber(19, Set.of(4), numbers);
		assertContainsNumber(32, Set.of(5), numbers);
		assertContainsNumber(33, Set.of(6), numbers);
		assertContainsNumber(34, Set.of(7), numbers);
		assertContainsNumber(72, Set.of(8), numbers);
		assertContainsNumber(36, Set.of(9), numbers);
		assertContainsNumber(37, Set.of(10), numbers);
	}
	
	@Test
	public void test05Numbers() {
		Scanner scanner = new Scanner("1042 21 9 1042 9 21 9");
		SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
		List<SeqNumber> numbers = analyzer.getNumbers();
		assertNotNull(numbers);
		assertEquals(3, numbers.size());
		
		assertContainsNumber(1042, Set.of(0,3), numbers);
		assertContainsNumber(21, Set.of(1,5), numbers);
		assertContainsNumber(9, Set.of(2,4,6), numbers);
	}
	
	@Test
	public void test06Numbers() {
		Scanner scanner = new Scanner("14 20 20 20 20 14 7 9 9 2 7");
		SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
		List<SeqNumber> numbers = analyzer.getNumbers();
		assertNotNull(numbers);
		assertEquals(5, numbers.size());
		
		assertContainsNumber(14, Set.of(0,5), numbers);
		assertContainsNumber(20, Set.of(1,2,3,4), numbers);
		assertContainsNumber(7, Set.of(6,10), numbers);
		assertContainsNumber(9, Set.of(7,8), numbers);
		assertContainsNumber(2, Set.of(9), numbers);
	}
	
	@Test
	public void test07RankingExactlyTwice() {
		Scanner scanner = new Scanner("88 7 88 90 21 21 90 7");
		SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
		List<SeqNumber> ranking = analyzer.getRanking(3);
		assertNotNull(ranking);
		assertEquals(3, ranking.size());
		
		checkSeqNumber(7, Set.of(1,7), ranking.get(0));
		checkSeqNumber(90, Set.of(3,6), ranking.get(1));
		checkSeqNumber(88, Set.of(0,2), ranking.get(2));
	}

	
	@Test
	public void test08RankingAtLeastTwice() {
		Scanner scanner = new Scanner("25 4 2 2 25 3 2 3 2 4");
		SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
		List<SeqNumber> ranking = analyzer.getRanking(3);
		assertNotNull(ranking);
		assertEquals(3, ranking.size());
		
		checkSeqNumber(4, Set.of(1,9), ranking.get(0));
		checkSeqNumber(25, Set.of(0,4), ranking.get(1));
		checkSeqNumber(3, Set.of(5,7), ranking.get(2));
	}
	
	@Test
	public void test09Ranking() {
		Scanner scanner = new Scanner("90 100 2 3 4 50 3 210 3 4 190 200 4 2 2");
		SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
		List<SeqNumber> ranking = analyzer.getRanking(3);
		assertNotNull(ranking);
		assertEquals(3, ranking.size());
		
		checkSeqNumber(4, Set.of(4,9,12), ranking.get(0));
		checkSeqNumber(3, Set.of(3,6,8), ranking.get(1));
		checkSeqNumber(2, Set.of(2,13,14), ranking.get(2));
	}
	
	@Test
	public void test10Ranking() {
		Scanner scanner = new Scanner("42 9 10 42 10 201 201 203 204 9 500 201");
		SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
		List<SeqNumber> ranking = analyzer.getRanking(4);
		assertNotNull(ranking);
		assertEquals(4, ranking.size());
		
		checkSeqNumber(9, Set.of(1,9), ranking.get(0));
		checkSeqNumber(42, Set.of(0,3), ranking.get(1));
		checkSeqNumber(10, Set.of(2,4), ranking.get(2));
		checkSeqNumber(201, Set.of(5,6,11), ranking.get(3));
	}
	
	@Test
	public void test11RankingAmbiguous() {
		Scanner scanner = new Scanner("20 12 50 12 50 20");
		SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
		List<SeqNumber> ranking = analyzer.getRanking(2);
		assertNotNull(ranking);
		assertEquals(2, ranking.size());

		checkSeqNumber(20, Set.of(0,5), ranking.get(0));
				
		SeqNumber secondNumber = ranking.get(1);
		assertTrue(secondNumber.getNumber() == 12 || secondNumber.getNumber() == 50);
		
		if(secondNumber.getNumber() == 12) {
			assertEquals(Set.of(1,3), secondNumber.getPositions());
		} else {
			assertEquals(Set.of(2,4), secondNumber.getPositions());
		}
	}
	
	@Test
	public void test12RankingExceptionNoRep() {
		Scanner scanner = new Scanner("302 33 72 401 432 987 117 4091");
		SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
		List<SeqNumber> ranking = analyzer.getRanking(1);
		assertNotNull(ranking);
		assertThrows(IllegalArgumentException.class, () -> {
			analyzer.getRanking(9);
		});
	}
	
	@Test
	public void test13RankingExceptionRep() {
		Scanner scanner = new Scanner("24 201 301 24 301 54");
		SeqAnalyzer analyzer = new SeqAnalyzer(scanner);
		List<SeqNumber> ranking = analyzer.getRanking(4);
		assertNotNull(ranking);
		assertThrows(IllegalArgumentException.class, () -> {
			analyzer.getRanking(5);
		});
	}
	
    public static void assertContainsNumber(int expectedNumber, Set<Integer> expectedPositions, List<SeqNumber> numbers) {
    	Set<Integer> foundPositions = null;
    	
    	for (SeqNumber number : numbers) {
    		if (number.getNumber() == expectedNumber) {
    			assertTrue(foundPositions == null);
    			foundPositions = number.getPositions();
    		}
    	}
    	
    	assertEquals(expectedPositions, foundPositions);
    }
    
    public static void checkSeqNumber(int expectedNumber, Set<Integer> expectedPositions, SeqNumber input) {
    	assertNotNull(input);
    	assertEquals(expectedNumber, input.getNumber());
    	assertEquals(expectedPositions, input.getPositions());
    }
	
}
