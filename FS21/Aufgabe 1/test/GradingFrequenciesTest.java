package Aufgabe_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GradingFrequenciesTest {

	private static Frequencies create(ArrayList<Coordinate> stations, double minimalDistance) {
		// return new FrequenciesSol(stations, minimalDistance);
		return new Frequencies(stations, minimalDistance);
	}

	@Test
	public void testTrueAssignmentPossibleOriginal1() {
		assertTrue(assignmentPossible(true, new double[] { 0.0, 0.0 }, new double[] { 0.0, 2.0 }, 2.0, 1));
	}

	@Test
	public void testTrueAssignmentPossibleOriginal2() {
		assertTrue(assignmentPossible(true, new double[] { 0.0, 0.0 }, new double[] { 0.0, 2.0 }, 2.0, 2));
	}

	@Test
	public void testTrueAssignmentPossibleOriginal3() {
		assertTrue(assignmentPossible(true, new double[] { 0.0, 0.0 }, new double[] { 0.0, 2.0 }, 3.0, 2));
	}

	@Test
	public void falseTrueAssignmentPossibleOriginal1() {
		assertTrue(assignmentPossible(false, new double[] { 0.0, 0.0 }, new double[] { 0.0, 2.0 }, 3.0, 1));
	}

	@Test
	public void testTrueAssignmentPossibleOriginal4() {
		assertTrue(assignmentPossible(true, new double[] { 0.0, 1.0, 3.0, 4.5, 5.5 },
				new double[] { 4.0, 1.0, 3.0, 4.0, 2.5 }, 2.0, 2));
	}

	@Test
	public void falseTrueAssignmentPossibleOriginal2() {
		assertTrue(assignmentPossible(false, new double[] { 0.0, 1.0, 3.0, 4.5, 5.5 },
				new double[] { 4.0, 1.0, 3.0, 4.0, 2.5 }, 2.0, 1));
	}

	@Test
	public void testFalseAssignmentPossibleCorner1() {
		assertTrue(assignmentPossible(false, new double[] { 5.0, 5.0 }, new double[] { 17.0, 17.0 }, 2.0, 1));
	}

	@Test
	public void testTrueAssignmentPossibleCorner1() {
		assertTrue(assignmentPossible(true, new double[] { 5.0, 5.0 }, new double[] { 17.0, 17.0 }, 2.0, 2));
	}

	@Test
	public void testTrueAssignmentPossibleCorner2() {
		assertTrue(assignmentPossible(true, new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				new double[] { 0.0, 2.0, 4.0, 6.0, 8.0, 10.0 }, 2.0, 1));
	}

	@Test
	public void falseTrueAssignmentPossibleCorner2() {
		assertTrue(assignmentPossible(false, new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 },
				new double[] { 0.0, 2.0, 4.0, 6.0, 8.0, 10.0 }, 2.01, 1));
	}

	@Test
	public void trueTrueAssignmentPossibleCorner3() {
		assertTrue(assignmentPossible(true, new double[] { 0.0, 2.0, 4.0, 6.0, 8.0, 10.0 },
				new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, 2.0, 1));
	}

	@Test
	public void trueTrueAssignmentPossibleCorner4() {
		assertTrue(assignmentPossible(true, new double[] { 0.0, 2.0, 4.0, 6.0, 8.0, 10.0 },
				new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, 4.0, 2));
	}

	@Test
	public void trueTrueAssignmentPossibleCorner5() {
		assertTrue(assignmentPossible(true, new double[] { 0.0, 2.0, 4.0, 6.0, 8.0, 10.0 },
				new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, 1.0, 6));
	}

	@Test
	public void trueTrueAssignmentPossibleAdvanced1() {
		assertTrue(assignmentPossible(true, new double[] { 8.237, 7.31, 0.86, 2.71, 6.21 },
				new double[] { 0.408, 7.36, 3.34, 5.66, 4.45 }, 5.931, 3));
	}

	@Test
	public void falseTrueAssignmentPossibleAdvanced1() {
		assertTrue(assignmentPossible(false, new double[] { 8.237, 7.31, 0.86, 2.71, 6.21 },
				new double[] { 0.408, 7.36, 3.34, 5.66, 4.45 }, 5.931, 2));
	}

	@Test
	public void falseTrueAssignmentPossibleAdvanced2() {
		assertTrue(assignmentPossible(false, new double[] { 5.771, 6.649, 2.481, 0.373, 9.696 },
				new double[] { 2.941, 9.124, 9.798, 2.99, 7.965 }, 4.356, 1));
	}

	@Test
	public void trueTrueAssignmentPossibleAdvanced2() {
		assertTrue(assignmentPossible(true, new double[] { 5.771, 6.649, 2.481, 0.373, 9.696 },
				new double[] { 2.941, 9.124, 9.798, 2.99, 7.965 }, 4.356, 2));
	}

	@Test
	public void trueTrueAssignmentPossibleAdvanced3() {
		assertTrue(assignmentPossible(true, new double[] { 5.771, 6.649, 2.481, 0.373, 9.696 },
				new double[] { 2.941, 9.124, 9.798, 2.99, 7.965 }, 4.356, 3));
	}

	@Test
	public void trueTrueAssignmentPossibleAdvanced4() {
		assertTrue(assignmentPossible(true,
				new double[] { 2.205, 6.016, 1.137, 1.283, 4.530, 4.259, 6.149, 7.428, 10.0, 5.306, 3.186, 6.311, 7.482,
						7.121, 9.199 },
				new double[] { 5.157, 7.955, 3.777, 9.586, 8.137, 5.651, 7.339, 1.569, 8.062, 0.158, 4.581, 3.114,
						9.813, 1.14, 9.99 },
				5.05, 6));
	}

	@Test
	public void trueTrueAssignmentPossibleAdvanced5() {
		assertTrue(assignmentPossible(true,
				new double[] { 2.205, 6.016, 1.137, 1.283, 4.530, 4.259, 6.149, 7.428, 10.0, 5.306, 3.186, 6.311, 7.482,
						7.121, 9.199 },
				new double[] { 5.157, 7.955, 3.777, 9.586, 8.137, 5.651, 7.339, 1.569, 8.062, 0.158, 4.581, 3.114,
						9.813, 1.14, 9.99 },
				5.05, 9));
	}

	@Test
	public void falseTrueAssignmentPossibleAdvanced3() {
		assertTrue(assignmentPossible(false,
				new double[] { 2.205, 6.016, 1.137, 1.283, 4.530, 4.259, 6.149, 7.428, 10.0, 5.306, 3.186, 6.311, 7.482,
						7.121, 9.199 },
				new double[] { 5.157, 7.955, 3.777, 9.586, 8.137, 5.651, 7.339, 1.569, 8.062, 0.158, 4.581, 3.114,
						9.813, 1.14, 9.99 },
				5.05, 4));
	}

	@Test
	public void falseTrueAssignmentPossibleAdvanced4() {
		assertTrue(assignmentPossible(false,
				new double[] { 2.205, 6.016, 1.137, 1.283, 4.530, 4.259, 6.149, 7.428, 10.0, 5.306, 3.186, 6.311, 7.482,
						7.121, 9.199 },
				new double[] { 5.157, 7.955, 3.777, 9.586, 8.137, 5.651, 7.339, 1.569, 8.062, 0.158, 4.581, 3.114,
						9.813, 1.14, 9.99 },
				5.05, 5));
	}

	@Test
	public void falseTrueAssignmentPossibleAdvanced5() {
		assertTrue(
				assignmentPossible(false, new double[] { 21.0, 50.0, 46.0, 35.0, 30.0, 86.0, 95.0, 75.0, 11.0, 25.0 },
						new double[] { 2.0, 39.0, 78.0, 61.0, 53.0, 65.0, 74.0, 44.0, 35.0, 54.0 }, 33.0, 2));
	}

	@Test
	public void falseTrueAssignmentPossibleAdvanced6() {
		assertTrue(
				assignmentPossible(false, new double[] { 21.0, 50.0, 46.0, 35.0, 30.0, 86.0, 95.0, 75.0, 11.0, 25.0 },
						new double[] { 2.0, 39.0, 78.0, 61.0, 53.0, 65.0, 74.0, 44.0, 35.0, 54.0 }, 33.0, 3));
	}

	@Test
	public void trueTrueAssignmentPossibleAdvanced6() {
		assertTrue(assignmentPossible(true, new double[] { 21.0, 50.0, 46.0, 35.0, 30.0, 86.0, 95.0, 75.0, 11.0, 25.0 },
				new double[] { 2.0, 39.0, 78.0, 61.0, 53.0, 65.0, 74.0, 44.0, 35.0, 54.0 }, 33.0, 4));
	}

	@Test
	public void trueTrueAssignmentPossibleAdvanced7() {
		assertTrue(assignmentPossible(true, new double[] { 21.0, 50.0, 46.0, 35.0, 30.0, 86.0, 95.0, 75.0, 11.0, 25.0 },
				new double[] { 2.0, 39.0, 78.0, 61.0, 53.0, 65.0, 74.0, 44.0, 35.0, 54.0 }, 33.0, 5));
	}

	@Test
	public void trueTrueAssignmentPossibleAdvanced8() {
		assertTrue(assignmentPossible(true, new double[] { 3.44, 9.42, 4.88, 6.41, 2.63, 2.43, 3.34, 1.07, 9.7, 7.98 },
				new double[] { 5.98, 7.76, 1.09, 2.79, 6.12, 3.41, 4.73, 2.31, 4.0, 1.56 }, 9.71, 9));
	}

	@Test
	public void falseTrueAssignmentPossibleAdvanced7() {
		assertTrue(assignmentPossible(false, new double[] { 3.44, 9.42, 4.88, 6.41, 2.63, 2.43, 3.34, 1.07, 9.7, 7.98 },
				new double[] { 5.98, 7.76, 1.09, 2.79, 6.12, 3.41, 4.73, 2.31, 4.0, 1.56 }, 9.71, 3));
	}

	@Test
	public void falseTrueAssignmentPossibleAdvanced8() {
		assertTrue(assignmentPossible(false, new double[] { 3.44, 9.42, 4.88, 6.41, 2.63, 2.43, 3.34, 1.07, 9.7, 7.98 },
				new double[] { 5.98, 7.76, 1.09, 2.79, 6.12, 3.41, 4.73, 2.31, 4.0, 1.56 }, 9.71, 6));
	}

	@Test
	public void falseTrueAssignmentPossibleAdvanced9() {
		assertTrue(
				assignmentPossible(false, new double[] { 7.09, 9.98, 7.36, 9.64, 0.09, 3.59, 5.86, 8.09, 7.99, 7.70 },
						new double[] { 6.10, 6.82, 2.71, 8.28, 7.38, 8.09, 0.73, 4.79, 5.12, 8.35 }, 5.54, 3));
	}

	@Test
	public void falseTrueAssignmentPossibleAdvanced10() {
		assertTrue(
				assignmentPossible(false, new double[] { 7.09, 9.98, 7.36, 9.64, 0.09, 3.59, 5.86, 8.09, 7.99, 7.70 },
						new double[] { 6.10, 6.82, 2.71, 8.28, 7.38, 8.09, 0.73, 4.79, 5.12, 8.35 }, 5.54, 5));
	}

	@Test
	public void trueTrueAssignmentPossibleAdvanced9() {
		assertTrue(assignmentPossible(true, new double[] { 7.09, 9.98, 7.36, 9.64, 0.09, 3.59, 5.86, 8.09, 7.99, 7.70 },
				new double[] { 6.10, 6.82, 2.71, 8.28, 7.38, 8.09, 0.73, 4.79, 5.12, 8.35 }, 5.54, 8));
	}

	@Test
	public void trueTrueAssignmentPossibleAdvanced10() {
		assertTrue(assignmentPossible(true, new double[] { 7.09, 9.98, 7.36, 9.64, 0.09, 3.59, 5.86, 8.09, 7.99, 7.70 },
				new double[] { 6.10, 6.82, 2.71, 8.28, 7.38, 8.09, 0.73, 4.79, 5.12, 8.35 }, 5.54, 6));
	}

	private boolean assignmentPossible(boolean expected, double[] Xs, double[] Ys, double minDistance,
			int frequencies) {
		assert Xs.length == Ys.length;
		ArrayList<Coordinate> stations = new ArrayList<Coordinate>();
		for (int i = 0; i < Xs.length; i++) {
			stations.add(new Coordinate(Xs[i], Ys[i]));
		}
		Frequencies freq = create(stations, minDistance);

		try {
			return freq.assignmentPossible(frequencies) == expected;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	private int minFrequencies(double[] Xs, double[] Ys, double minDistance) {
		assert Xs.length == Ys.length;
		ArrayList<Coordinate> stations = new ArrayList<Coordinate>();
		for (int i = 0; i < Xs.length; i++) {
			stations.add(new Coordinate(Xs[i], Ys[i]));
		}
		Frequencies freq = create(stations, minDistance);

		try {
			return freq.minFrequencies();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}

	}

	@Test
	public void testAssignmentPossibleEmpty() {
		assertTrue(assignmentPossible(true, new double[] {}, new double[] {}, 0.1, 0));

		assertTrue(assignmentPossible(false, new double[] { 1.0 }, new double[] { 2.0 }, 500.0, 0));
	}

	@Test
	public void testMinFrequenciesLarge() {
		assertEquals(9, minFrequencies(new double[] { 3.44, 9.42, 4.88, 6.41, 2.63, 2.43, 3.34, 1.07, 9.7, 7.98 },
				new double[] { 5.98, 7.76, 1.09, 2.79, 6.12, 3.41, 4.73, 2.31, 4.0, 1.56 }, 9.71));
	}

	@Test
	public void testMinFrequenciesMedium1() {
		assertEquals(4, minFrequencies(new double[] { 21.0, 50.0, 46.0, 35.0, 30.0, 86.0, 95.0, 75.0, 11.0, 25.0 },
				new double[] { 2.0, 39.0, 78.0, 61.0, 53.0, 65.0, 74.0, 44.0, 35.0, 54.0 }, 33.0));
	}

	@Test
	public void testMinFrequenciesMedium2() {
		assertEquals(6, minFrequencies(new double[] { 7.09, 9.98, 7.36, 9.64, 0.09, 3.59, 5.86, 8.09, 7.99, 7.70 },
				new double[] { 6.10, 6.82, 2.71, 8.28, 7.38, 8.09, 0.73, 4.79, 5.12, 8.35 }, 5.54));
	}

	@Test
	public void testMinFrequenciesSmall1() {
		assertEquals(2, minFrequencies(new double[] { 5.771, 6.649, 2.481, 0.373, 9.696 },
				new double[] { 2.941, 9.124, 9.798, 2.99, 7.965 }, 4.356));
	}

	@Test
	public void testMinFrequenciesSmall2() {
		assertEquals(3, minFrequencies(new double[] { 8.237, 7.31, 0.86, 2.71, 6.21 },
				new double[] { 0.408, 7.36, 3.34, 5.66, 4.45 }, 5.931));
	}

	@Test
	public void testMinFrequenciesSmall3() {
		assertEquals(1, minFrequencies(new double[] { 0.0, 2.0, 4.0, 6.0, 8.0, 10.0 },
				new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 }, 2.0));
	}

	@Test
	public void testMinFrequenciesEmpty() {
		assertEquals(1, minFrequencies(new double[] { 1.0 }, new double[] { 1.0 }, 3.0));

		assertEquals(0, minFrequencies(new double[] {}, new double[] {}, 3.0));
	}

	@Test
	public void testMinFrequenciesOriginal() {
		assertEquals(3, minFrequencies(new double[] { 0.0, 0.0, 0.0 }, new double[] { 0.0, 1.0, 2.0 }, 3.0));

		assertEquals(2, minFrequencies(new double[] { 0.0, 1.0, 3.0, 4.5, 5.5 },
				new double[] { 4.0, 1.0, 3.0, 4.0, 2.5 }, 2.0));
	}

	@Test
	public void testMostProblematicStationsOriginal() {
		ArrayList<Coordinate> stations = new ArrayList<Coordinate>();

		stations.add(new Coordinate(1.5, 4.0));
		stations.add(new Coordinate(3.0, 1.5));
		stations.add(new Coordinate(3.0, 3.0));
		stations.add(new Coordinate(4.5, 1.0));
		stations.add(new Coordinate(4.5, 4.0));

		Frequencies freq = create(stations, 2.0);

		/*
		 * Konfliktzahlen der Standorte: (1.5, 4.0): 1 (moegliche Interferenz mit (3.0,
		 * 3.0)) (3.0, 1.5): 2 (moegliche Interferenzen mit (3.0, 3.0), (4.5, 1.0))
		 * (3.0, 3.0): 3 (moegliche Interferenzen mit (1.5, 4.0), (3.0, 1.5), (4.5,
		 * 4.0)) (4.5, 1.0): 1 (moegliche Interferenz mit (3.0, 1.5)) (4.5, 4.0): 1
		 * (moegliche Interferenz mit (3.0, 3.0))
		 */

		List<Coordinate> result = freq.mostProblematicStations(2);
		assertNotNull(result);
		assertEquals(2, result.size());

		assertEquals(3.0, result.get(0).getX());
		assertEquals(3.0, result.get(0).getY());

		assertEquals(3.0, result.get(1).getX());
		assertEquals(1.5, result.get(1).getY());
	}

	@Test
	public void testMostProblematicStationsEmpty() {
		ArrayList<Coordinate> stations = new ArrayList<Coordinate>();
		Frequencies freq = create(stations, 2.0);
		List<Coordinate> result = freq.mostProblematicStations(0);
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	@Test
	public void testMostProblematicStationsUnique() {
		ArrayList<Coordinate> stations = new ArrayList<Coordinate>();

		stations.add(new Coordinate(9.98, 6.82));
		stations.add(new Coordinate(7.36, 2.71));
		stations.add(new Coordinate(9.64, 8.28));
		stations.add(new Coordinate(0.09, 7.38));
		stations.add(new Coordinate(3.59, 8.09));
		stations.add(new Coordinate(5.86, 0.73));
		stations.add(new Coordinate(7.70, 8.35));

		/*
		 * Konfliktzahlen: (3.59, 8.09) : 2 (9.98, 6.82) : 2 (7.7, 8.35) : 3 (9.64,
		 * 8.28) : 2 (5.86, 0.73) : 1 (0.09, 7.38) : 1 (7.36, 2.71) : 1
		 */

		Frequencies freq = create(stations, 4.75);
		List<Coordinate> result = freq.mostProblematicStations(1);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(7.7, result.get(0).getX());
		assertEquals(8.35, result.get(0).getY());
	}

	@Test
	public void testMostProblematicStationsLarge() {
		ArrayList<Coordinate> stations = new ArrayList<Coordinate>();

		Coordinate x = new Coordinate(7.99, 5.12);
		Coordinate y = new Coordinate(7.09, 6.10);
		Coordinate z = new Coordinate(8.09, 4.79);

		stations.add(new Coordinate(9.98, 6.82));
		stations.add(new Coordinate(7.36, 2.71));
		stations.add(new Coordinate(9.64, 8.28));
		stations.add(new Coordinate(0.09, 7.38));
		stations.add(x);
		stations.add(y);
		stations.add(z);
		stations.add(new Coordinate(3.59, 8.09));
		stations.add(new Coordinate(5.86, 0.73));
		stations.add(new Coordinate(7.70, 8.35));

		/*
		 * Konfliktzahlen: (9.98, 6.82) : 6 (9.64, 8.28) : 5 (7.99, 5.12) : 7 (0.09,
		 * 7.38) : 1 (7.36, 2.71) : 5 (7.09, 6.1) : 7 (8.09, 4.79) : 7 (3.59, 8.09) : 3
		 * (5.86, 0.73) : 3 (7.7, 8.35) : 6
		 */

		Frequencies freq = create(stations, 5.0);
		List<Coordinate> result = freq.mostProblematicStations(3);

		assertNotNull(result);
		assertEquals(3, result.size());

		assertTrue(result.contains(x));
		assertTrue(result.contains(y));
		assertTrue(result.contains(z));
	}

	@Test
	public void testMostProblematicStationsSorted() {
		ArrayList<Coordinate> stations = new ArrayList<Coordinate>();

		Coordinate x = new Coordinate(10.0, 10.0);
		Coordinate y = new Coordinate(12.0, 12.0);
		Coordinate z = new Coordinate(9.0, 12.0);

		stations.add(x); // Konfliktzahl 4
		stations.add(new Coordinate(8.0, 8.0)); // Konfliktzahl 1
		stations.add(new Coordinate(12.0, 8.0)); // Konfliktzahl 1
		stations.add(y); // Konfliktzahl 3
		stations.add(new Coordinate(14.0, 14.0)); // Konfliktzahl 1
		stations.add(z); // Konfliktzahl 2

		Frequencies freq = create(stations, 4.0);
		List<Coordinate> result = freq.mostProblematicStations(3);

		assertNotNull(result);
		assertEquals(3, result.size());

		assertEquals(result.get(0), x);
		assertEquals(result.get(1), y);
		assertEquals(result.get(2), z);

	}

}