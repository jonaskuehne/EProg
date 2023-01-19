import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class GradingNestedCyclesTest {

	/** cycleDistance **/

	@Test
	public void testCycleDistance01() {
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		Node n13 = new Node(null, null);
		Node n14 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n13);
		n13.setOuter(n14);
		n14.setOuter(n11);

		Map<Node, Integer> result = NestedCycles.cycleDistance(n12);
		assertNotNull(result);
		assertEquals(4, result.size());

		assertEquals(3, result.get(n11));
		assertEquals(0, result.get(n12));
		assertEquals(1, result.get(n13));
		assertEquals(2, result.get(n14));
	}

	@Test
	public void testCycleDistance02() {
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n11);

		Map<Node, Integer> result = NestedCycles.cycleDistance(n11);
		assertNotNull(result);
		assertEquals(2, result.size());

		assertEquals(0, result.get(n11));
		assertEquals(1, result.get(n12));
	}

	@Test
	public void testCycleDistance03() {
		// N4
		Node n41 = new Node(null, null);
		Node n42 = new Node(null, null);
		Node n43 = new Node(null, null);

		n41.setOuter(n42);
		n42.setOuter(n43);
		n43.setOuter(n41);

		// N3
		Node n31 = new Node(null, null);
		Node n32 = new Node(null, null);
		Node n33 = new Node(null, null);

		n31.setOuter(n32);
		n32.setOuter(n33);
		n33.setOuter(n31);

		n31.setInner(n41);
		n32.setInner(n42);
		n33.setInner(n43);

		// N2
		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		Node n23 = new Node(null, null);
		Node n24 = new Node(null, null);

		n21.setOuter(n22);
		n22.setOuter(n23);
		n23.setOuter(n24);
		n24.setOuter(n21);

		n21.setInner(n31);
		n22.setInner(n32);
		n23.setInner(n31);
		n24.setInner(n33);

		// N1
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		Node n13 = new Node(null, null);
		Node n14 = new Node(null, null);
		Node n15 = new Node(null, null);
		Node n16 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n13);
		n13.setOuter(n14);
		n14.setOuter(n15);
		n15.setOuter(n16);
		n16.setOuter(n11);

		n11.setInner(n22);
		n12.setInner(n24);
		n13.setInner(n21);
		n14.setInner(n23);
		n15.setInner(n23);
		n16.setInner(n21);

		Map<Node, Integer> result = NestedCycles.cycleDistance(n15);
		assertNotNull(result);
		assertEquals(6, result.size());

		assertEquals(2, result.get(n11));
		assertEquals(3, result.get(n12));
		assertEquals(4, result.get(n13));
		assertEquals(5, result.get(n14));
		assertEquals(0, result.get(n15));
		assertEquals(1, result.get(n16));
	}

	/** isNestedCycle **/
	@Test
	public void testIsNestedCycle01TrueA() {
		assertTrue(NestedCycles.isNestedCycle(exampleTrue01SingleCycle01()));
	}

	@Test
	public void testIsNestedCycle02TrueA() {
		assertTrue(NestedCycles.isNestedCycle(exampleTrue01SingleCycle02()));
	}

	@Test
	public void testIsNestedCycle03TrueB() {
		assertTrue(NestedCycles.isNestedCycle(exampleTrue02TwoCycles01()));
	}

	@Test
	public void testIsNestedCycle04TrueB() {
		assertTrue(NestedCycles.isNestedCycle(exampleTrue02TwoCycles02()));
	}

	@Test
	public void testIsNestedCycle05TrueC() {
		assertTrue(NestedCycles.isNestedCycle(exampleTrue03FourCycles01()));
	}

	@Test
	public void testIsNestedCycle06TrueC() {
		assertTrue(NestedCycles.isNestedCycle(exampleTrue03FourCycles02()));
	}

	@Test
	public void testIsNestedCycle07TrueD() {
		assertTrue(NestedCycles.isNestedCycle(exampleTrue04ManyCycles(5)));
	}

	@Test
	public void testIsNestedCycle08TrueD() {
		assertTrue(NestedCycles.isNestedCycle(exampleTrue04ManyCycles(8)));
	}

	@Test
	public void testIsNestedCycle09TrueE() {
		assertTrue(NestedCycles.isNestedCycle(exampleTrue05MiddleCycleLarge01()));
	}

	@Test
	public void testIsNestedCycle10TrueE() {
		assertTrue(NestedCycles.isNestedCycle(exampleTrue05MiddleCycleLarge02()));
	}

	@Test
	public void testIsNestedCycle11FalseA() {
		assertFalse(NestedCycles.isNestedCycle(exampleFalse01SingleMissingOuter01()));
	}

	@Test
	public void testIsNestedCycle12FalseA() {
		assertFalse(NestedCycles.isNestedCycle(exampleFalse01SingleMissingOuter02()));
	}

	@Test
	public void testIsNestedCycle13FalseB() {
		assertFalse(NestedCycles.isNestedCycle(exampleFalse02SingleWrongOuter01()));
	}

	@Test
	public void testIsNestedCycle14FalseB() {
		assertFalse(NestedCycles.isNestedCycle(exampleFalse02SingleWrongOuter02()));
	}

	@Test
	public void testIsNestedCycle15FalseC() {
		assertFalse(NestedCycles.isNestedCycle(exampleFalse03SkipCycle01()));
	}

	@Test
	public void testIsNestedCycle16FalseC() {
		assertFalse(NestedCycles.isNestedCycle(exampleFalse03SkipCycle02()));
	}

	@Test
	public void testIsNestedCycle17FalseD() {
		assertFalse(NestedCycles.isNestedCycle(exampleFalse04MissingInner01()));
	}

	@Test
	public void testIsNestedCycle18FalseD() {
		assertFalse(NestedCycles.isNestedCycle(exampleFalse04MissingInner02()));
	}

	@Test
	public void testIsNestedCycle19FalseE() {
		assertFalse(NestedCycles.isNestedCycle(exampleFalse05InnerOutside01()));
	}

	@Test
	public void testIsNestedCycle20FalseE() {
		assertFalse(NestedCycles.isNestedCycle(exampleFalse05InnerOutside02()));
	}

	/** positive cases **/

	public static Node exampleTrue01SingleCycle01() {
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		Node n13 = new Node(null, null);
		Node n14 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n13);
		n13.setOuter(n14);
		n14.setOuter(n11);

		return n11;
	}

	public static Node exampleTrue01SingleCycle02() {
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		Node n13 = new Node(null, null);
		Node n14 = new Node(null, null);
		Node n15 = new Node(null, null);
		Node n16 = new Node(null, null);
		Node n17 = new Node(null, null);
		Node n18 = new Node(null, null);
		Node n19 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n13);
		n13.setOuter(n14);
		n14.setOuter(n15);
		n15.setOuter(n16);
		n16.setOuter(n17);
		n17.setOuter(n18);
		n18.setOuter(n19);
		n19.setOuter(n11);

		return n11;
	}

	public static Node exampleTrue02TwoCycles01() {
		// N2
		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		Node n23 = new Node(null, null);

		n21.setOuter(n22);
		n22.setOuter(n23);
		n23.setOuter(n21);

		// N1
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		Node n13 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n13);
		n13.setOuter(n11);

		n11.setInner(n21);
		n12.setInner(n22);
		n13.setInner(n23);

		return n11;
	}

	public static Node exampleTrue02TwoCycles02() {
		// N2
		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		Node n23 = new Node(null, null);
		Node n24 = new Node(null, null);
		Node n25 = new Node(null, null);

		n21.setOuter(n22);
		n22.setOuter(n23);
		n23.setOuter(n24);
		n24.setOuter(n25);
		n25.setOuter(n21);

		// N1
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		Node n13 = new Node(null, null);
		Node n14 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n13);
		n13.setOuter(n14);
		n14.setOuter(n11);

		n11.setInner(n21);
		n12.setInner(n22);
		n13.setInner(n23);
		n14.setInner(n25);

		return n11;
	}

	public static Node exampleTrue03FourCycles01() {
		Node n41 = new Node(null, null);
		Node n42 = new Node(null, null);

		n41.setOuter(n42);
		n42.setOuter(n41);

		Node n31 = new Node(null, null);
		Node n32 = new Node(null, null);
		Node n33 = new Node(null, null);

		n31.setOuter(n32);
		n32.setOuter(n33);
		n33.setOuter(n31);

		n31.setInner(n41);
		n32.setInner(n41);
		n33.setInner(n41);

		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);

		n21.setOuter(n22);
		n22.setOuter(n21);

		n21.setInner(n33);
		n22.setInner(n33);

		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n11);

		n11.setInner(n22);
		n12.setInner(n21);

		return n11;
	}

	public static Node exampleTrue03FourCycles02() {
		// N4
		Node n41 = new Node(null, null);
		Node n42 = new Node(null, null);
		Node n43 = new Node(null, null);

		n41.setOuter(n42);
		n42.setOuter(n43);
		n43.setOuter(n41);

		// N3
		Node n31 = new Node(null, null);
		Node n32 = new Node(null, null);
		Node n33 = new Node(null, null);

		n31.setOuter(n32);
		n32.setOuter(n33);
		n33.setOuter(n31);

		n31.setInner(n41);
		n32.setInner(n42);
		n33.setInner(n43);

		// N2
		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		Node n23 = new Node(null, null);
		Node n24 = new Node(null, null);

		n21.setOuter(n22);
		n22.setOuter(n23);
		n23.setOuter(n24);
		n24.setOuter(n21);

		n21.setInner(n31);
		n22.setInner(n32);
		n23.setInner(n31);
		n24.setInner(n33);

		// N1
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		Node n13 = new Node(null, null);
		Node n14 = new Node(null, null);
		Node n15 = new Node(null, null);
		Node n16 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n13);
		n13.setOuter(n14);
		n14.setOuter(n15);
		n15.setOuter(n16);
		n16.setOuter(n11);

		n11.setInner(n22);
		n12.setInner(n24);
		n13.setInner(n21);
		n14.setInner(n23);
		n15.setInner(n23);
		n16.setInner(n21);

		return n11;
	}

	public static Node exampleTrue04ManyCycles(int numCycles) {
		ArrayList<Node> innerCycle = null;

		for (int i = 0; i < numCycles; i++) {
			final ArrayList<Node> curCycle = new ArrayList<Node>();

			final Node startNode = new Node(null, null);

			curCycle.add(startNode);

			Node prevNode = startNode;

			for (int j = 0; j <= i + 1; j++) {
				Node curNode = new Node(null, null);
				curCycle.add(curNode);

				prevNode.setOuter(curNode);

				if (i > 0) {
					curNode.setInner(innerCycle.get(Math.max(0, j - 1)));
				}

				prevNode = curNode;
			}

			if (i > 0) {
				startNode.setInner(innerCycle.get(0));
			}

			prevNode.setOuter(startNode);

			innerCycle = curCycle;
		}

		return innerCycle.get(0);
	}

	public static Node exampleTrue05MiddleCycleLarge01() {
		Node n31 = new Node(null, null);
		Node n32 = new Node(null, null);

		n31.setOuter(n32);
		n32.setOuter(n31);

		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		Node n23 = new Node(null, null);
		Node n24 = new Node(null, null);
		Node n25 = new Node(null, null);
		Node n26 = new Node(null, null);
		Node n27 = new Node(null, null);

		n21.setOuter(n22);
		n22.setOuter(n23);
		n23.setOuter(n24);
		n24.setOuter(n25);
		n25.setOuter(n26);
		n26.setOuter(n27);
		n27.setOuter(n21);

		n21.setInner(n31);
		n22.setInner(n32);
		n23.setInner(n31);
		n24.setInner(n32);
		n25.setInner(n31);
		n26.setInner(n32);
		n27.setInner(n31);

		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n11);

		n11.setInner(n21);
		n12.setInner(n24);

		return n11;
	}

	public static Node exampleTrue05MiddleCycleLarge02() {
		Node n31 = new Node(null, null);
		Node n32 = new Node(null, null);
		Node n33 = new Node(null, null);

		n31.setOuter(n32);
		n32.setOuter(n33);
		n33.setOuter(n31);

		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		Node n23 = new Node(null, null);
		Node n24 = new Node(null, null);
		Node n25 = new Node(null, null);

		n21.setOuter(n22);
		n22.setOuter(n23);
		n23.setOuter(n24);
		n24.setOuter(n25);
		n25.setOuter(n21);

		n21.setInner(n31);
		n22.setInner(n32);
		n23.setInner(n31);
		n24.setInner(n32);
		n25.setInner(n31);

		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		Node n13 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n13);
		n13.setOuter(n11);

		n11.setInner(n21);
		n12.setInner(n24);
		n13.setInner(n25);

		return n11;
	}

	/** negative cases **/

	public static Node exampleFalse01SingleMissingOuter01() {
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		Node n13 = new Node(null, null);
		Node n14 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n13);
		n13.setOuter(n14);
		//

		return n11;
	}

	public static Node exampleFalse01SingleMissingOuter02() {
		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		Node n23 = new Node(null, null);

		n21.setOuter(n22);
		n22.setOuter(n23);
		n23.setOuter(n21);

		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		Node n13 = new Node(null, null);

		n11.setInner(n21);
		n12.setInner(n22);
		n13.setInner(n23);

		n11.setOuter(n12);
		n12.setOuter(n13);
		//

		return n11;
	}

	public static Node exampleFalse02SingleWrongOuter01() {
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		Node n13 = new Node(null, null);
		Node n14 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n13);
		n13.setOuter(n14);
		n14.setOuter(n12); //

		return n11;
	}

	public static Node exampleFalse02SingleWrongOuter02() {
		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		Node n23 = new Node(null, null);

		n21.setOuter(n22);
		n22.setOuter(n23);
		n23.setOuter(n21);

		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		Node n13 = new Node(null, null);

		n11.setInner(n21);
		n12.setInner(n22);
		n13.setInner(n23);

		n11.setOuter(n12);
		n12.setOuter(n13);
		n13.setOuter(n12); //

		return n11;
	}

	public static Node exampleFalse03SkipCycle01() {
		Node n31 = new Node(null, null);
		Node n32 = new Node(null, null);

		n31.setOuter(n32);
		n32.setOuter(n31);

		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);

		n21.setOuter(n22);
		n22.setOuter(n21);

		n21.setInner(n31);
		n22.setInner(n32);

		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n11);

		n11.setInner(n21);
		n12.setInner(n31); //

		return n11;
	}

	public static Node exampleFalse03SkipCycle02() {
		// N4
		Node n41 = new Node(null, null);
		Node n42 = new Node(null, null);
		Node n43 = new Node(null, null);

		n41.setOuter(n42);
		n42.setOuter(n43);
		n43.setOuter(n41);

		// N3
		Node n31 = new Node(null, null);
		Node n32 = new Node(null, null);
		Node n33 = new Node(null, null);

		n31.setOuter(n32);
		n32.setOuter(n33);
		n33.setOuter(n31);

		n31.setInner(n41);
		n32.setInner(n42);
		n33.setInner(n43);

		// N2
		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		Node n23 = new Node(null, null);
		Node n24 = new Node(null, null);

		n21.setOuter(n22);
		n22.setOuter(n23);
		n23.setOuter(n24);
		n24.setOuter(n21);

		n21.setInner(n31);
		n22.setInner(n42); //
		n23.setInner(n31);
		n24.setInner(n33);

		// N1
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		Node n13 = new Node(null, null);
		Node n14 = new Node(null, null);
		Node n15 = new Node(null, null);
		Node n16 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n13);
		n13.setOuter(n14);
		n14.setOuter(n15);
		n15.setOuter(n16);
		n16.setOuter(n11);

		n11.setInner(n22);
		n12.setInner(n24);
		n13.setInner(n21);
		n14.setInner(n23);
		n15.setInner(n23);
		n16.setInner(n21);

		return n11;
	}

	public static Node exampleFalse04MissingInner01() {
		// N2
		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		n21.setOuter(n22);
		n22.setOuter(n21);

		// N1
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);
		Node n13 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n13);
		n13.setOuter(n11);

		n11.setInner(n21);
		n12.setInner(null); //
		n13.setInner(n22);

		return n11;
	}

	public static Node exampleFalse04MissingInner02() {
		// N4
		Node n41 = new Node(null, null);
		Node n42 = new Node(null, null);
		Node n43 = new Node(null, null);

		n41.setOuter(n42);
		n42.setOuter(n43);
		n43.setOuter(n41);

		// N3
		Node n31 = new Node(null, null);
		Node n32 = new Node(null, null);
		Node n33 = new Node(null, null);
		Node n34 = new Node(null, null);

		n31.setOuter(n32);
		n32.setOuter(n33);
		n33.setOuter(n34);
		n34.setOuter(n31);

		n31.setInner(n41);
		n32.setInner(null); // missing inner
		n33.setInner(n42);
		n34.setInner(n43);

		// N2
		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		Node n23 = new Node(null, null);

		n21.setOuter(n22);
		n22.setOuter(n23);
		n23.setOuter(n21);

		n21.setInner(n31);
		n22.setInner(n32);
		n23.setInner(n33);

		return n21;
	}

	public static Node exampleFalse05InnerOutside01() {
		// N2
		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		n21.setOuter(n22);
		n22.setOuter(n21);

		// N1
		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n11);

		n11.setInner(n21);
		n12.setInner(n22);

		n22.setInner(n11); //

		return n11;
	}

	public static Node exampleFalse05InnerOutside02() {
		Node n31 = new Node(null, null);
		Node n32 = new Node(null, null);
		Node n33 = new Node(null, null);

		n31.setOuter(n32);
		n32.setOuter(n33);
		n33.setOuter(n31);

		Node n21 = new Node(null, null);
		Node n22 = new Node(null, null);
		Node n23 = new Node(null, null);

		n21.setOuter(n22);
		n22.setOuter(n23);
		n23.setOuter(n21);

		n21.setInner(n31);
		n22.setInner(n32);

		Node n11 = new Node(null, null);
		Node n12 = new Node(null, null);

		n11.setOuter(n12);
		n12.setOuter(n11);

		n11.setInner(n21);
		n12.setInner(n22);

		n23.setInner(n11); //

		return n11;
	}
}