import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class GradingLabyrinthTest {
	
	/** a) **/
	
	@Test
	public void testExits01() {
		Room[] N = lineGraphA();
		
		List<Room> prefix = new LinkedList<>(List.of());
		List<Room> res = Labyrinth.exits(N[3], prefix);
		assertNotNull(res);
		
		assertEquals(Set.of(N[2]), new HashSet<>(res));
	}
	
	@Test
	public void testExits02() {
		Room[] N = lineGraphA();
		
		List<Room> prefix = new LinkedList<>(List.of(N[3], N[1], N[5]));
		List<Room> res = Labyrinth.exits(N[3], prefix);
		assertNotNull(res);
		
		assertEquals(Set.of(N[2]), new HashSet<>(res));
	}
	
	@Test
	public void testExits03() {
		Room[] N = lineGraphA();
		
		List<Room> prefix = new LinkedList<>(List.of(N[3], N[1], N[5], N[0], N[4], N[2]));
		List<Room> res = Labyrinth.exits(N[3], prefix);
		assertNotNull(res);
		
		assertEquals(Set.of(N[2]), new HashSet<>(res));
	}
	
	@Test
	public void testExits04() {
		Room[] N = treeGraphA();
		
		List<Room> prefix = new LinkedList<>(List.of());
		List<Room> res = Labyrinth.exits(N[0], prefix);
		assertNotNull(res);
		
		assertEquals(Set.of(N[3], N[4], N[5], N[6]), new HashSet<>(res));
	}
	
	@Test
	public void testExits05() {
		Room[] N = treeGraphA();
		
		List<Room> prefix = new LinkedList<>(List.of(N[0], N[2]));
		List<Room> res = Labyrinth.exits(N[0], prefix);
		assertNotNull(res);
		
		assertEquals(Set.of(N[5], N[6]), new HashSet<>(res));
	}
	
	@Test
	public void testExits06() {
		Room[] N = dag1A();
		
		List<Room> prefix = new LinkedList<>(List.of());
		List<Room> res = Labyrinth.exits(N[0], prefix);
		assertNotNull(res);
		
		assertEquals(Set.of(N[5], N[8], N[9]), new HashSet<>(res));
	}
	
	@Test
	public void testExits07() {
		Room[] N = dag1A();
		
		List<Room> prefix = new LinkedList<>(List.of(N[0], N[1], N[2], N[4]));
		List<Room> res = Labyrinth.exits(N[0], prefix);
		assertNotNull(res);
		
		assertEquals(Set.of(N[5], N[8]), new HashSet<>(res));
	}

	@Test
	public void testExits08() {
		Room[] N = dag1A();
		
		List<Room> prefix = new LinkedList<>(List.of(N[0]));
		List<Room> res = Labyrinth.exits(N[0], prefix);
		assertNotNull(res);
		
		assertEquals(3, res.size());
		assertEquals(Set.of(N[5], N[8], N[9]), new HashSet<>(res));
	}
	
	@Test
	public void testExits09() {
		Room[] N = dag2A();
		
		List<Room> prefix = new LinkedList<>(List.of(N[0], N[1], N[2], N[3]));
		List<Room> res = Labyrinth.exits(N[0], prefix);
		assertNotNull(res);
		
		assertEquals(Set.of(), new HashSet<>(res));
	}
	
	@Test
	public void testExits10() {
		Room[] N = dag2A();
		
		List<Room> prefix = new LinkedList<>(List.of());
		List<Room> res = Labyrinth.exits(N[14], prefix);
		assertNotNull(res);
		
		assertEquals(Set.of(N[5], N[8], N[9], N[10], N[11]), new HashSet<>(res));
	}
	
	@Test
	public void testExits11() {
		Room[] N = dag2A();
		
		{
			List<Room> prefix0 = new LinkedList<>(List.of(N[0], N[12], N[6], N[8]));
			List<Room> res0 = Labyrinth.exits(N[0], prefix0);
			assertNotNull(res0);
			
			assertEquals(1, res0.size());
			assertEquals(Set.of(N[8]), new HashSet<>(res0));
		}
		
		{
			List<Room> prefix1 = new LinkedList<>(List.of(N[0], N[12], N[6], N[13]));
			List<Room> res1 = Labyrinth.exits(N[0], prefix1);
			assertNotNull(res1);
			
			assertEquals(2, res1.size());
			assertEquals(Set.of(N[8], N[10]), new HashSet<>(res1));
		}
		
		{
			List<Room> prefix2 = new LinkedList<>(List.of(N[14], N[1], N[2]));
			List<Room> res2 = Labyrinth.exits(N[14], prefix2);
			assertNotNull(res2);
			
			assertEquals(4, res2.size());
			assertEquals(Set.of(N[5], N[8], N[10], N[11]), new HashSet<>(res2));
		}
	}
	
	@Test
	public void testExits12() {
		Room[] N = dag2A();
		
		{
			List<Room> prefix0 = new LinkedList<>(List.of(N[14], N[12], N[6], N[8]));
			List<Room> res0 = Labyrinth.exits(N[0], prefix0);
			assertNotNull(res0);
			
			assertEquals(Set.of(), new HashSet<>(res0));
		}
		
		{
			List<Room> prefix1 = new LinkedList<>(List.of());
			List<Room> res1 = Labyrinth.exits(N[3], prefix1);
			assertNotNull(res1);
			
			assertEquals(3, res1.size());
			assertEquals(Set.of(N[5], N[9], N[10]), new HashSet<>(res1));
		}
		
		{
			List<Room> prefix2 = new LinkedList<>(List.of(N[0], N[12], N[6], N[13], N[7], N[10]));
			List<Room> res2 = Labyrinth.exits(N[0], prefix2);
			assertNotNull(res2);
			
			assertEquals(1, res2.size());
			assertEquals(Set.of(N[10]), new HashSet<>(res2));
		}
		
		{
			List<Room> prefix3 = new LinkedList<>(List.of(N[2], N[4]));
			List<Room> res3 = Labyrinth.exits(N[2], prefix3);
			assertNotNull(res3);
			
			assertEquals(4, res3.size());
			assertEquals(Set.of(N[5], N[8], N[10], N[11]), new HashSet<>(res3));
		}
	}

	public static Room[] lineGraphA() {
		Room[] N = new Room[6];
		N[0] = new Room("N0");
		N[1] = new Room("N1");
		N[2] = new Room("N2");
		N[3] = new Room("N3");
		N[4] = new Room("N4");
		N[5] = new Room("NFive");
		
		N[3].doorsTo = new Room[] {N[1]};
		N[1].doorsTo = new Room[] {N[5]};
		N[5].doorsTo = new Room[] {N[0]};
		N[0].doorsTo = new Room[] {N[4]};
		N[4].doorsTo = new Room[] {N[2]};
		N[2].doorsTo = new Room[] {};
		
		return N;
	}
	
	public static Room[] treeGraphA() {
		Room[] N = new Room[7];
		N[0] = new Room("N0");
		N[1] = new Room("N1");
		N[2] = new Room("N2");
		N[3] = new Room("N3");
		N[4] = new Room("N4");
		N[5] = new Room("N5");
		N[6] = new Room("N6");
		
		N[0].doorsTo = new Room[] {N[1], N[2]};
		N[1].doorsTo = new Room[] {N[3], N[4]};
		N[2].doorsTo = new Room[] {N[5], N[6]};
		N[3].doorsTo = new Room[] {};
		N[4].doorsTo = new Room[] {};
		N[5].doorsTo = new Room[] {};
		N[6].doorsTo = new Room[] {};
		
		return N;
	}
	
	public static Room[] dag1A() {
		Room[] N = new Room[10];
		N[0] = new Room("N0");
		N[1] = new Room("N1");
		N[2] = new Room("N2");
		N[3] = new Room("N3");
		N[4] = new Room("N4");
		N[5] = new Room("N5");
		N[6] = new Room("N6");
		N[7] = new Room("N7");
		N[8] = new Room("N8");
		N[9] = new Room("N9");
		
		N[0].doorsTo = new Room[] {N[1], N[2], N[3]};
		N[1].doorsTo = new Room[] {N[2], N[3]};
		N[2].doorsTo = new Room[] {N[4]};
		N[3].doorsTo = new Room[] {N[5], N[9]};
		N[4].doorsTo = new Room[] {N[5], N[6], N[7]};
		N[5].doorsTo = new Room[] {};
		N[6].doorsTo = new Room[] {N[8]};
		N[7].doorsTo = new Room[] {N[8]};
		N[8].doorsTo = new Room[] {};
		N[9].doorsTo = new Room[] {};
		
		return N;
	}
	
	public static Room[] dag2A() {
		Room[] N = new Room[15];
		N[0] = new Room("N0");
		N[1] = new Room("N1");
		N[2] = new Room("N2");
		N[3] = new Room("N3");
		N[4] = new Room("N4");
		N[5] = new Room("N5");
		N[6] = new Room("N6");
		N[7] = new Room("N7");
		N[8] = new Room("N8");
		N[9] = new Room("N9");
		N[10] = new Room("N10");
		N[11] = new Room("N11");
		N[12] = new Room("N12");
		N[13] = new Room("N13");
		N[14] = new Room("N14");
		
		N[0].doorsTo = new Room[] {N[1], N[2], N[3], N[12]};
		N[1].doorsTo = new Room[] {N[2], N[3]};
		N[2].doorsTo = new Room[] {N[4]};
		N[3].doorsTo = new Room[] {N[5], N[9], N[10]};
		N[4].doorsTo = new Room[] {N[5], N[6], N[7], N[11]};
		N[5].doorsTo = new Room[] {};
		N[6].doorsTo = new Room[] {N[8], N[13]};
		N[7].doorsTo = new Room[] {N[8], N[10]};
		N[8].doorsTo = new Room[] {};
		N[9].doorsTo = new Room[] {};
		N[10].doorsTo = new Room[] {};
		N[11].doorsTo = new Room[] {};
		N[12].doorsTo = new Room[] {N[6]};
		N[13].doorsTo = new Room[] {N[7]};
		N[14].doorsTo = new Room[] {N[12], N[1]};
		
		return N;
	}
	
	/** b) **/
	
	@Test
	public void testSort01() {
		Room[] N = testGraph1B();
		
		List<Room> ex = new LinkedList<Room>(List.of(N[2],N[0],N[3]));
		Labyrinth.sortRooms(ex);
		assertEquals(List.of(N[3],N[2],N[0]), ex);	
	}
	
	@Test
	public void testSort02() {
		Room[] N = testGraph1B();
		
		List<Room> ex = new LinkedList<Room>(List.of(N[3],N[5]));
		Labyrinth.sortRooms(ex);
		assertEquals(List.of(N[5],N[3]), ex);	
	}
	
	@Test
	public void testSort03() {
		Room[] N = testGraph1B();
		
		Room Q = new Room("Q", new Room[] {new Room("R1"), new Room("R2"), new Room("R3")});
		List<Room> ex = new LinkedList<Room>(List.of(Q,N[1]));
		Labyrinth.sortRooms(ex);
		assertEquals(List.of(N[1],Q), ex);		
	}
	
	@Test
	public void testSort04() {
		Room[] N = testGraph2B();
		
		List<Room> ex = new LinkedList<Room>(List.of(N[2],N[5],N[1]));
		Labyrinth.sortRooms(ex);
		assertEquals(List.of(N[5],N[1],N[2]), ex);	
	}
	
	@Test
	public void testSort05() {
		{
			Room[] N = testGraph2B();
			
			List<Room> ex = new LinkedList<Room>(List.of(N[2],N[0]));
			Labyrinth.sortRooms(ex);
			assertEquals(List.of(N[0],N[2]), ex);
		}

		{
			Room[] N = testGraph2B();
			
			Room Q = new Room("Q", new Room[] {new Room("R1"), new Room("R2"), new Room("R3")});
			List<Room> ex = new LinkedList<Room>(List.of(Q,N[1]));
			Labyrinth.sortRooms(ex);
			assertEquals(List.of(N[1],Q), ex);
		}
	}
	
	@Test
	public void testSort06() {
		Room[] N = testGraph1B();
		
		List<Room> ex = new LinkedList<Room>(List.of(N[0],N[5],N[1],N[2]));
		Labyrinth.sortRooms(ex);
		assertEquals(List.of(N[5],N[2],N[1],N[0]), ex);	
	}
	
	@Test
	public void testSort07() {
		Room[] N = testGraph2B();
		
		List<Room> ex = new LinkedList<Room>(List.of(N[0],N[4],N[9],N[2],N[1]));
		Labyrinth.sortRooms(ex);
		assertEquals(List.of(N[9],N[4],N[1],N[0],N[2]), ex);	
	}
	
	@Test
	public void testSort08() {
		Room[] N = testGraph3B();
		
		List<Room> ex = new LinkedList<Room>(List.of(N[0],N[1],N[2],N[4]));
		Labyrinth.sortRooms(ex);
		assertTrue(List.of(N[4],N[0],N[2],N[1]).equals(ex)||List.of(N[4],N[2],N[0],N[1]).equals(ex));	
	}
	
	@Test
	public void testSort09() {
		Room[] N = testGraph2B();
		
		List<Room> ex = new LinkedList<Room>(List.of(N[2],N[1],N[1],N[2],N[4],N[0],N[1]));
		Labyrinth.sortRooms(ex);
		assertEquals(List.of(N[4],N[1],N[1],N[1],N[0],N[2],N[2]), ex);	
	}
	
	@Test
	public void testSort10() {
		Room[] N = testGraph4B();
		
		Room R = new Room("R");
		
		List<Room> ex = new LinkedList<Room>(List.of(R,N[1],N[0]));
		Labyrinth.sortRooms(ex);
		assertEquals(List.of(R,N[0],N[1]), ex);

		R.doorsTo = new Room [] { new Room("A1"), new Room("A2"), new Room("A3"), new Room("A4") };

		List<Room> ex2 = new LinkedList<Room>(List.of(R,N[1],N[0]));
		
		Labyrinth.sortRooms(ex2);
		assertEquals(List.of(N[0],N[1],R), ex2);
	}
	
	public static Room[] testGraph1B() {
		Room[] N = new Room[6];
		N[0] = new Room("N0");
		N[1] = new Room("N1");
		N[2] = new Room("N2");
		N[3] = new Room("N3");
		N[4] = new Room("N4");
		N[5] = new Room("NFive");
		
		N[0].doorsTo = new Room[]{ N[1], N[2] };
		N[1].doorsTo = new Room[]{ N[3], N[4] };
		N[2].doorsTo = new Room[]{ N[4], N[5] };
		N[3].doorsTo = new Room[]{  };
		N[4].doorsTo = new Room[]{  };
		N[5].doorsTo = new Room[]{  };
		
		return N;
	}
	
	public static Room[] testGraph2B() {	  
	  Room[] N = new Room[10];
	  N[0] = new Room("N0");
	  N[1] = new Room("N1");
	  N[2] = new Room("N2");
	  N[3] = new Room("N3");
	  N[4] = new Room("N2*2");
	  N[5] = new Room("N5");
	  N[6] = new Room("N6");
	  N[7] = new Room("N7");
	  N[8] = new Room("N8");
	  N[9] = new Room("NNine");

	  N[0].doorsTo = new Room[]{ N[1], N[9] };
	  N[1].doorsTo = new Room[]{ N[3], N[4], N[7] };
	  N[2].doorsTo = new Room[]{ N[3], N[6] };
	  N[3].doorsTo = new Room[]{ N[4], N[5], N[7] };
	  N[4].doorsTo = new Room[]{ };
	  N[5].doorsTo = new Room[]{ N[8] };
	  N[6].doorsTo = new Room[]{  };
	  N[7].doorsTo = new Room[]{  };
	  N[8].doorsTo = new Room[]{  };
	  N[9].doorsTo = new Room[]{  };

	  return N;
	}
	
	public static Room[] testGraph3B() {
		Room[] N=testGraph1B();
		N[0].doorsTo = new Room[]{ N[3], N[5] };
		return N;
	}

	public static Room[] testGraph4B() {		
		Room[] N = new Room[6];
		N[0] = new Room("N0");
		N[1] = new Room("N1");
		N[2] = new Room("N2");
		N[3] = new Room("N3");
		N[4] = new Room("NFour");
		N[5] = new Room("N5");
		
		N[0].doorsTo = new Room[]{ N[3], N[4] };
		N[1].doorsTo = new Room[]{ N[2], N[5] };
		N[2].doorsTo = new Room[]{ N[3], N[4] };
		N[3].doorsTo = new Room[]{  };
		N[4].doorsTo = new Room[]{  };
		N[5].doorsTo = new Room[]{  };
		
		return N;
	}
	
	/** c) **/
	
	@Test
	public void testPaths01() {
		Room r0 = createNewRoom("100", createNewRoom("101", createNewRoom("102", createNewRoom("103"))));
		
		assertTrue(Labyrinth.pathsWithSameNames(r0, 0));
		assertTrue(Labyrinth.pathsWithSameNames(r0, 1));
		assertFalse(Labyrinth.pathsWithSameNames(r0, 2));
	}
	
	@Test
	public void testPaths02() {		
		Room r6 = createNewRoom("6", new Room("7"));
		
		Room r0 = createNewRoom("2", createNewRoom("3", createNewRoom("4", createNewRoom("5", r6), createNewRoom("5", r6))));
		
		assertTrue(Labyrinth.pathsWithSameNames(r0, 2));
		assertFalse(Labyrinth.pathsWithSameNames(r0, 3));	
	}
	
	@Test
	public void testPaths03() {
		Room r6 = createNewRoom("6", new Room("7"));
		
		Room r0 = createNewRoom("2", createNewRoom("3", createNewRoom("4", createNewRoom("5", r6), createNewRoom("17", r6))));
		
		assertTrue(Labyrinth.pathsWithSameNames(r0, 1));
		assertFalse(Labyrinth.pathsWithSameNames(r0, 2));
	}
	
	@Test
	public void testPaths04() {
		Room r5End = new Room("5");
		Room r9End = new Room("9");
		
		Room r0 = createNewRoom("1", createNewRoom("4", createNewRoom("9", r5End, r9End), createNewRoom("5", r5End, r9End)));
		
		assertTrue(Labyrinth.pathsWithSameNames(r0, 2));
		assertFalse(Labyrinth.pathsWithSameNames(r0, 3));
	}
	
	@Test
	public void testPaths05() {
		Room r6End0 = new Room("6");
		Room r6End1 = new Room("6");
		Room r0 = createNewRoom("2", createNewRoom("42", 
				                 createNewRoom("3", 
				                		  new Room("101"), r6End0, r6End1), 
				                 createNewRoom("3", r6End0, r6End1, new Room("500"))
				               )
			              );
		
		assertTrue(Labyrinth.pathsWithSameNames(r0, 4));
		assertFalse(Labyrinth.pathsWithSameNames(r0, 5));
	}
	
	@Test
	public void testPaths06() {
		Room [] rooms1 = { new Room("A"), new Room("A"), new Room("A"), new Room("A"), new Room("A"), new Room("A") };
		
		Room r0 = new Room("B", rooms1);
		
		assertTrue(Labyrinth.pathsWithSameNames(r0, 5));
		assertTrue(Labyrinth.pathsWithSameNames(r0, 6));
		assertFalse(Labyrinth.pathsWithSameNames(r0, 7));
	}
	
	@Test
	public void testPaths07() {
		Room [] rooms1 = { new Room("A"), new Room("A"), new Room("A"), new Room("A"), new Room("A"), new Room("C") };
		
		Room r0 = new Room("B", rooms1);
		
		assertTrue(Labyrinth.pathsWithSameNames(r0, 4));
		assertTrue(Labyrinth.pathsWithSameNames(r0, 5));
		assertFalse(Labyrinth.pathsWithSameNames(r0, 6));
	}
	
	@Test
	public void testPaths08() {				
		Room rEnd0 = createNewRoom("C", new Room("A"), new Room("A"));
		Room rEnd1 = createNewRoom("A", new Room("B"), new Room("B"), new Room("Z"));
		
		Room r0 = createNewRoom("A", createNewRoom("B", rEnd0), createNewRoom("C", rEnd1));
		
		assertTrue(Labyrinth.pathsWithSameNames(r0, 3));
		assertTrue(Labyrinth.pathsWithSameNames(r0, 4));
		assertFalse(Labyrinth.pathsWithSameNames(r0, 5));
	}
	
	@Test
	public void testPaths09() {
		Room r5 = new Room("5");
		Room r4Other = createNewRoom("4", createNewRoom("2", new Room("5")));
		Room r4 = createNewRoom( "4", createNewRoom("3", r5, new Room("101")), createNewRoom("2", r5, new Room("7")) );
		Room r1 = createNewRoom("1", createNewRoom("2", r4), createNewRoom("3", r4, r4Other));
					
		assertTrue(Labyrinth.pathsWithSameNames(r1, 2));
		assertTrue(Labyrinth.pathsWithSameNames(r1, 3));
		assertFalse(Labyrinth.pathsWithSameNames(r1, 4));				
	}
	
	@Test
	public void testPaths10() {
		Room rOther = createNewRoom("B", createNewRoom("R3", createNewRoom("C", createNewRoom("R1", new Room("R2")))));
		Room C = createNewRoom("C", new Room("R1"), new Room("Q"), new Room("R2"));
		Room B = createNewRoom("B", createNewRoom("R1", createNewRoom("R3", C)), createNewRoom("R2", createNewRoom("R3", C)) );
		
		Room A = createNewRoom("A", B, rOther);
		
		assertTrue(Labyrinth.pathsWithSameNames(A, 3));
		assertFalse(Labyrinth.pathsWithSameNames(A, 4));
	}

	/** Helper function **/
	public Room createNewRoom(String name, Room ... rooms) {
		return new Room(name, rooms);
	}

}
