import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;


public class GradingFourinarowTest {

	
	// ------------------------------------------------------------
	// (a.1) setze Stein
	// ------------------------------------------------------------
	
	@Test
	public void testSetzeStein() {	
		Spielzustand brett = new Spielzustand();	
		brett.setzeStein(true, 0);
		isConsistent(brett);
		assertTopmost(brett, true, 0, 0);
		
		brett.setzeStein(false, 0);
		isConsistent(brett);
		assertTopmost(brett, false, 0, 1);
		
		brett.setzeStein(true, 0);
		isConsistent(brett);
		assertTopmost(brett, true, 0, 2);
		
		brett.setzeStein(false, 0);
		isConsistent(brett);
		assertTopmost(brett, false, 0, 3);
		
		brett.setzeStein(false, 1);
		brett.setzeStein(false, 2);
		brett.setzeStein(false, 0);
		
		isConsistent(brett);
		assertTopmost(brett, false, 1, 0);
		assertTopmost(brett, false, 2, 0);
		assertTopmost(brett, false, 0, 4);
	}
	
	@Test
	public void testSetzeSteinHatGewonnen() {	
		Spielzustand leeresBrett = new Spielzustand();
		Spielzustand neuesBrett = new Spielzustand(leeresBrett);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(false, 2);
		neuesBrett.setzeStein(false, 2);
		neuesBrett.setzeStein(false, 2);
		assertFalse(neuesBrett.hatGewonnen(false));
		assertFalse(neuesBrett.hatGewonnen(true));
		
		Spielzustand brett3 = new Spielzustand(neuesBrett);
		brett3.setzeStein(false, 0);
		brett3.setzeStein(true, 3);
		assertFalse(brett3.hatGewonnen(false));
		assertTrue(brett3.hatGewonnen(true));
		
		Spielzustand brett4 = new Spielzustand(neuesBrett);
		brett4.setzeStein(true, 6);
		brett4.setzeStein(false, 2);
		assertFalse(brett4.hatGewonnen(true));
		assertTrue(brett4.hatGewonnen(false));
	}
	
	@Test
	public void testSetzeSteinFillAllRows() {	
		Spielzustand leeresBrett = new Spielzustand();
		Spielzustand brett = new Spielzustand(leeresBrett);
		boolean current = false;
		for(int i = 0; i < 7; i++) {
			for(int j=0; j < 6; j++) {
				current = (current) ? false : true;	
				brett.setzeStein(current, i);
				assertTopmost(brett, current, i, j);
			}
			isConsistent(brett);
		}
	}
	
	
	
	// ------------------------------------------------------------
	// (a.2) moegliche Spalten
	// ------------------------------------------------------------
	
	@Test
	public void testMoeglicheSpaltenEmpty() {	
		Spielzustand brett = new Spielzustand();
		
		brett.gelbeSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false, false, false, false, false}, 
			{false, false, false, false, false, false}, 
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false}
		};
		brett.roteSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false}
		};
		
		Set<Integer> moeglicheSpalten = brett.moeglicheSpalten();
		assertTrue(moeglicheSpalten.size() == 7);
		for(int i=0; i < 7; i++) {
			assertTrue(moeglicheSpalten.contains(i));	
		}
	}
	
	@Test
	public void testMoeglicheSpaltenFull() {	
		Spielzustand brett = new Spielzustand();
		
		brett.gelbeSteine = new boolean[][] {
			{false, true, false, true, false, true},
			{false, true, false, true, false, true}, 
			{false, true, false, true, false, true}, 
			{false, true, false, true, false, true},
			{false, true, false, true, false, true},
			{false, true, false, true, false, true},
			{false, true, false, true, false, true}
		};
		brett.roteSteine = new boolean[][] {
			{true, false, true, false, true, false},
			{true, false, true, false, true, false},
			{true, false, true, false, true, false},
			{true, false, true, false, true, false},
			{true, false, true, false, true, false},
			{true, false, true, false, true, false},
			{true, false, true, false, true, false}
		};
		
		Set<Integer> moeglicheSpalten = brett.moeglicheSpalten();
		assertTrue(moeglicheSpalten.isEmpty());
	}
	
	@Test
	public void testMoeglicheSpalten01() {	
		Spielzustand brett = new Spielzustand();
		
		brett.gelbeSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{true, false, true, false, true, true}, 
			{false, false, false, false, false, false}, 
			{true, true, true, true, true, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{true, true, true, true, true, false}
		};
		brett.roteSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{false, true, false, true, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{true, true, false, false, false, false},
			{false, false, false, false, false, false}
		};
				
		Set<Integer> moeglicheSpalten = brett.moeglicheSpalten();
		assertEquals(moeglicheSpalten.size(), 6);
		for(int i=0; i < 7; i++) {
			if (i != 1) {
				assertTrue(moeglicheSpalten.contains(i));
			}
		}
	}
	
	@Test
	public void testMoeglicheSpaltenOneColor() {	
		Spielzustand brett = new Spielzustand();
		
		brett.gelbeSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false, false, false, false, false}, 
			{false, false, false, false, false, false}, 
			{false, false, false, false, false, false},
			{true, true, true, true, true, true},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false}
		};
		brett.roteSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{true, true, true, true, true, true},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false}
		};
		
		Set<Integer> moeglicheSpalten = brett.moeglicheSpalten();
		assertEquals(moeglicheSpalten.size(), 5);
		assertFalse(moeglicheSpalten.contains(2));
		assertFalse(moeglicheSpalten.contains(4));
		assertTrue(moeglicheSpalten.contains(0));
		assertTrue(moeglicheSpalten.contains(1));
		assertTrue(moeglicheSpalten.contains(3));
		assertTrue(moeglicheSpalten.contains(5));
		assertTrue(moeglicheSpalten.contains(6));
	}
	
	
	
	// ------------------------------------------------------------
	// (b) kannGewinnen
	// ------------------------------------------------------------
	
	private boolean kannGewinnenHasAtLeastOneTrue = false;
	private boolean kannGewinnenHasAtLeastOneFalse = false;
	
	private boolean kannGewinnenAtLeastOneTrue() {
		
		if(kannGewinnenHasAtLeastOneTrue) {
			return true;
		}
		
		try {
			checkKannGewinnenEinStein();
			kannGewinnenHasAtLeastOneTrue = true;
			return true;
		} catch(Exception e) {}
		
		try {
			checkKannGewinnenZweiSteine();
			kannGewinnenHasAtLeastOneTrue = true;
			return true;
		} catch(Exception e) {}
		
		try {
			checkKannGewinnenDreiSteine();
			kannGewinnenHasAtLeastOneTrue = true;
			return true;
		} catch(Exception e) {}
		
		try {
			checkKannGewinnenDiagonal();
			kannGewinnenHasAtLeastOneTrue = true;
			return true;
		} catch(Exception e) {}
		
		return false;
	}
	
	private boolean kannGewinnenAtLeastOneFalse() {
		
		if(kannGewinnenHasAtLeastOneFalse) {
			return true;
		}
		
		try {
			checkKannNichtGewinnen01();
			kannGewinnenHasAtLeastOneFalse = true;
			return true;
		} catch(Exception e) {}
		
		try {
			checkKannNichtGewinnen02();
			kannGewinnenHasAtLeastOneFalse = true;
			return true;
		} catch(Exception e) {}
		
		return false;
	}
	
	@Test
	public void testKannGewinnenEinStein() {
		checkKannGewinnenEinStein();
		assertTrue(kannGewinnenAtLeastOneTrue());
		assertTrue(kannGewinnenAtLeastOneFalse());
	}
	
	public void checkKannGewinnenEinStein() {
		Spielzustand leeresBrett = new Spielzustand();
		Spielzustand neuesBrett = new Spielzustand(leeresBrett);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(false, 2);
		neuesBrett.setzeStein(false, 2);
		neuesBrett.setzeStein(false, 2);
		Fourinarow fia = new Fourinarow();
		for(int z = 1; z <= 4; z++) {
			assertTrue(fia.kannGewinnen(true, neuesBrett, z));
			assertTrue(fia.kannGewinnen(false, neuesBrett, z));	
		}
	}
	
	@Test
	public void testKannGewinnenZweiSteine() {
		checkKannGewinnenZweiSteine();
		assertTrue(kannGewinnenAtLeastOneTrue());
		assertTrue(kannGewinnenAtLeastOneFalse());
	}
	
	public void checkKannGewinnenZweiSteine() {	
		Spielzustand leeresBrett = new Spielzustand();
		Spielzustand neuesBrett = new Spielzustand(leeresBrett);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(false, 2);
		neuesBrett.setzeStein(false, 2);
		Fourinarow fia = new Fourinarow();
		for(int z = 2; z <= 4; z++) {
			assertTrue(fia.kannGewinnen(true, neuesBrett, z));
			assertTrue(fia.kannGewinnen(false, neuesBrett, z));	
		}
		assertFalse(fia.kannGewinnen(true, neuesBrett, 1));
		assertFalse(fia.kannGewinnen(false, neuesBrett, 1));	
	}
	
	@Test
	public void testKannGewinnenDreiSteine() {
		checkKannGewinnenDreiSteine();
		assertTrue(kannGewinnenAtLeastOneTrue());
		assertTrue(kannGewinnenAtLeastOneFalse());
	}
	
	public void checkKannGewinnenDreiSteine() {	
		Spielzustand leeresBrett = new Spielzustand();
		Spielzustand neuesBrett = new Spielzustand(leeresBrett);
		neuesBrett.setzeStein(true, 3);
		neuesBrett.setzeStein(false, 2);
		Fourinarow fia = new Fourinarow();
		for(int z = 3; z <= 4; z++) {
			assertTrue(fia.kannGewinnen(true, neuesBrett, z));
			assertTrue(fia.kannGewinnen(false, neuesBrett, z));	
		}
		for(int z = 1; z <= 2; z++) {
			assertFalse(fia.kannGewinnen(true, neuesBrett, z));
			assertFalse(fia.kannGewinnen(false, neuesBrett, z));
		}
	}
	
	@Test
	public void testKannNichtGewinnen01() {	
		checkKannNichtGewinnen01();
		assertTrue(kannGewinnenAtLeastOneTrue());
		assertTrue(kannGewinnenAtLeastOneFalse());
	}
		
	public void checkKannNichtGewinnen01() {	
		Spielzustand leeresBrett = new Spielzustand();
		Spielzustand neuesBrett = new Spielzustand(leeresBrett);
		Fourinarow fia = new Fourinarow();
		for(int z = 1; z <= 3; z++) {
			assertFalse(fia.kannGewinnen(true, neuesBrett, z));
			assertFalse(fia.kannGewinnen(false, neuesBrett, z));
		}
	}
	
	@Test
	public void testKannNichtGewinnen02() {	
		checkKannNichtGewinnen02();
		assertTrue(kannGewinnenAtLeastOneTrue());
		assertTrue(kannGewinnenAtLeastOneFalse());
	}
	
	public void checkKannNichtGewinnen02() {	
		Spielzustand brett = new Spielzustand();
		
		brett.gelbeSteine = new boolean[][] {
			{true, false, true, false, false, false},
			{false, false, true, false, true, false},
			{false, true, true, true, false, true},
			{true, true, false, true, false, false},
			{true, false, false, false, true, true},
			{true, false, true, false, true, false},
			{false, false, false, true, false, false}
		};
		brett.roteSteine = new boolean[][] {
			{false, true, false, true, false, false},
			{true, true, false, true, false, false},
			{true, false, false, false, true, false},
			{false, false, true, false, true, true},
			{false, true, true, true, false, false},
			{false, true, false, true, false, true},
			{true, true, true, false, false, false}
		};
		Fourinarow fia = new Fourinarow();
		assertFalse(fia.kannGewinnen(false, brett, 5));
		assertFalse(fia.kannGewinnen(true, brett, 5));
	}
	
	@Test
	public void testKannGewinnenDiagonal() {	
		checkKannGewinnenDiagonal();
		assertTrue(kannGewinnenAtLeastOneTrue());
		assertTrue(kannGewinnenAtLeastOneFalse());
	}
	
	public void checkKannGewinnenDiagonal() {	
		Spielzustand brett = new Spielzustand();
		
		brett.gelbeSteine = new boolean[][] {
			{false, false, true, false, true, false},
			{true, false, false, false, false, false},
			{false, true, false, false, false, false}, 
			{true, false, true, false, true, false},
			{true, false, true, false, true, false},
			{false, true, true, true, false, false},
			{false, true, false, true, false, false}
		};
		brett.roteSteine = new boolean[][] {
			{true, true, false, true, false, true},
			{false, true, false, false, false, false},
			{true, false, false, false, false, false},
			{false, true, false, true, false, false},
			{false, true, false, true, false, false},
			{true, false, false, false, true, false},
			{true, false, true, false, true, false}
		};
		Fourinarow fia = new Fourinarow();
		assertTrue(fia.kannGewinnen(true, brett, 1));
		assertTrue(fia.kannGewinnen(true, brett, 1));
	}
	
	@Test
	public void testKannGewinnenSchonGewonnen() {	
		Spielzustand brett = new Spielzustand();
		
		brett.gelbeSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false, false, false, false, false}, 
			{false, false, false, false, false, false}, 
			{false, false, false, true, false, false},
			{false, true, false, true, false, false},
			{false, true, false, true, false, false},
			{false, true, false, true, false, false}
		};
		brett.roteSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{true, false, true, false, false, false},
			{true, false, true, false, false, false},
			{true, false, true, false, false, true}
		};
		Fourinarow fia = new Fourinarow();
		try {
			fia.kannGewinnen(false, brett, 17);
			fia.kannGewinnen(true, brett, 23);
			assertTrue(false); // should have thrown an exception
		} catch(IllegalStateException e) {}
		
		assertTrue(kannGewinnenAtLeastOneTrue());
		assertTrue(kannGewinnenAtLeastOneFalse());
	}
	
	
	
	// ------------------------------------------------------------
	// part c: unentschieden
	// ------------------------------------------------------------
	
	private boolean unentschiedenHasAtLeastOneTrue = false;
	private boolean unentschiedenHasAtLeastOneFalse = false;
	
	private boolean unentschiedenAtLeastOneTrue() {
		
		if(unentschiedenHasAtLeastOneTrue) {
			return true;
		}
		
		try {
			checkUnentschieden01();
			unentschiedenHasAtLeastOneTrue = true;
			return true;
		} catch(Exception e) {
		}
		
		try {
			checkUnentschieden02();
			unentschiedenHasAtLeastOneTrue = true;
			return true;
		} catch(Exception e) {
		}
		
		try {
			checkUnentschieden03();
			unentschiedenHasAtLeastOneTrue = true;
			return true;
		} catch(Exception e) {
		}
		
		try {
			checkUnentschieden04();
			unentschiedenHasAtLeastOneTrue = true;
			return true;
		} catch(Exception e) {
		}
		
		try {
			checkUnentschieden05();
			unentschiedenHasAtLeastOneTrue = true;
			return true;
		} catch(Exception e) {
		}
		
		try {
			checkUnentschieden06();
			unentschiedenHasAtLeastOneTrue = true;
			return true;
		} catch(Exception e) {
		}
		
		return false;
	}
	
	private boolean unentschiedenAtLeastOneFalse() {
		
		if(unentschiedenHasAtLeastOneFalse) {
			return true;
		}
		
		try {
			checkUnentschiedenFalse01();
			unentschiedenHasAtLeastOneFalse = true;
			return true;
		} catch(Exception e) {}
		
		try {
			checkUnentschiedenFalse02();
			unentschiedenHasAtLeastOneFalse = true;
			return true;
		} catch(Exception e) {}
		
		try {
			checkUnentschiedenFalse03();
			unentschiedenHasAtLeastOneFalse = true;
			return true;
		} catch(Exception e) {}
		
		return false;
	}
	
	@Test
	public void testUnentschieden01() {
		checkUnentschieden01();
		checkUnentschieden02();
		assertTrue(unentschiedenAtLeastOneTrue());
		assertTrue(unentschiedenAtLeastOneFalse());
	}
	
	public void checkUnentschieden01() {
		
		{
			Spielzustand brett = new Spielzustand();
			
			brett.gelbeSteine = new boolean[][] {
				{true, false, true, true, true, false}, 
				{true, true, false, false, false, true}, 
				{false, false, true, true, false, false}, 
				{true, true, false, false, false, true}, 
				{true, false, true, true, true, false}, 
				{false, false, true, false, false, true}, 
				{true, true, false, true, false, false}
			};
			brett.roteSteine = new boolean[][] {
				{false, true, false, false, false, true}, 
				{false, false, true, true, true, false}, 
				{true, true, false, false, true, true}, 
				{false, false, true, true, true, false}, 
				{false, true, false, false, false, true}, 
				{true, true, false, true, true, false}, 
				{false, false, true, false, true, false}
			};
			Fourinarow fia = new Fourinarow();
			assertTrue(fia.unentschieden(true, brett));
			assertTrue(fia.unentschieden(false, brett));
		}
	}
		
	public void checkUnentschieden02() {
			Spielzustand brett = new Spielzustand();
			
			brett.gelbeSteine = new boolean[][] {
				{true, true, false, true, false, false}, 
				{true, false, false, true, false, true}, 
				{false, false, true, true, true, false}, 
				{true, false, true, false, false, false}, 
				{false, true, false, true, false, true}, 
				{false, true, false, true, false, true}, 
				{true, true, true, false, false, false}
			};
			brett.roteSteine = new boolean[][] {
				{false, false, true, false, true, true}, 
				{false, true, true, false, true, false}, 
				{true, true, false, false, false, true}, 
				{false, true, false, true, true, true}, 
				{true, false, true, false, true, false}, 
				{true, false, true, false, true, false}, 
				{false, false, false, false, false, false}
			};
			Fourinarow fia = new Fourinarow();
			assertTrue(fia.unentschieden(true, brett));
			assertFalse(fia.unentschieden(false, brett));
	}
	
	@Test
	public void testUnentschieden02() {
		checkUnentschieden03();
		checkUnentschieden04();
		assertTrue(unentschiedenAtLeastOneTrue());
		assertTrue(unentschiedenAtLeastOneFalse());
	}
		
	public void checkUnentschieden03() {
			Spielzustand brett = new Spielzustand();
			
			brett.gelbeSteine = new boolean[][] {
				{true, false, true, true, true, false}, 
				{false, true, true, false, false, true}, 
				{false, false, false, true, false, false}, 
				{false, true, true, true, false, true}, 
				{true, false, false, false, true, false}, 
				{true, false, true, false, false, true}, 
				{true, true, false, true, false, false}
			};
			brett.roteSteine = new boolean[][] {
				{false, true, false, false, false, true}, 
				{true, false, false, true, true, false}, 
				{true, true, true, false, false, false}, 
				{true, false, false, false, true, false}, 
				{false, true, true, true, false, false}, 
				{false, true, false, true, true, false}, 
				{false, false, true, false, true, true}
			};
			Fourinarow fia = new Fourinarow();
			assertTrue(fia.unentschieden(true, brett));
			
			assertTrue(unentschiedenAtLeastOneTrue());
	}
		
	public void checkUnentschieden04() {
			Spielzustand brett = new Spielzustand();
			
			brett.gelbeSteine = new boolean[][] {
				{false, true, false, true, true, true}, 
				{true, false, false, false, false, false}, 
				{true, false, true, true, true, false}, 
				{false, true, false, false, false, true}, 
				{false, false, true, true, false, false}, 
				{true, false, true, false, true, false}, 
				{true, false, false, true, true, true}
			};
			brett.roteSteine = new boolean[][] {
				{true, false, true, false, false, false}, 
				{false, true, true, true, false, false}, 
				{false, true, false, false, false, true}, 
				{true, false, true, true, true, false}, 
				{true, true, false, false, true, true}, 
				{false, true, false, true, false, true}, 
				{false, true, true, false, false, false}
			};
			Fourinarow fia = new Fourinarow();
			assertTrue(fia.unentschieden(false, brett));
			assertFalse(fia.unentschieden(true, brett));
	}
	
	@Test
	public void testUnentschieden03() {
		checkUnentschieden05();
		checkUnentschieden06();
		assertTrue(unentschiedenAtLeastOneTrue());
		assertTrue(unentschiedenAtLeastOneFalse());
	}
		
	public void checkUnentschieden05() {
			Spielzustand brett = new Spielzustand();
			
			brett.gelbeSteine = new boolean[][] {
				{true, false, true, false, true, false}, 
				{true, false, true, true, false, true}, 
				{true, false, false, true, false, true}, 
				{false, true, false, true, false, false}, 
				{false, true, false, false, true, false}, 
				{true, false, true, true, false, false}, 
				{false, true, true, false, false, true}
			};
			brett.roteSteine = new boolean[][] {
				{false, true, false, true, false, true}, 
				{false, true, false, false, true, false}, 
				{false, true, true, false, true, false}, 
				{true, false, true, false, true, true}, 
				{true, false, true, true, false, true}, 
				{false, true, false, false, false, false}, 
				{true, false, false, true, true, false}
			};
			Fourinarow fia = new Fourinarow();
			assertTrue(fia.unentschieden(false, brett));
			assertFalse(fia.unentschieden(true, brett));
	}
	
	public void checkUnentschieden06() {
			Spielzustand brett = new Spielzustand();
			brett.gelbeSteine = new boolean[][] {
				{true, false, true, false, false, false},
				{false, false, true, false, true, false},
				{false, true, true, true, false, true},
				{true, true, false, true, false, false},
				{true, false, false, false, true, true},
				{true, false, true, false, true, false},
				{false, false, false, true, false, false}
			};
			brett.roteSteine = new boolean[][] {
				{false, true, false, true, false, false},
				{true, true, false, true, false, false},
				{true, false, false, false, true, false},
				{false, false, true, false, true, true},
				{false, true, true, true, false, false},
				{false, true, false, true, false, true},
				{true, true, true, false, false, false}
			};
			Fourinarow fia = new Fourinarow();
			assertTrue(fia.unentschieden(true, brett));
			assertTrue(fia.unentschieden(false, brett));
	}
	
	@Test
	public void testUnentschiedenSchonGewonnen() {
		Spielzustand brett = new Spielzustand();
		
		brett.gelbeSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false, false, false, false, false}, 
			{false, false, false, false, false, false}, 
			{false, false, false, false, false, false},
			{false, true, false, true, false, false},
			{false, true, false, true, false, false},
			{false, true, false, true, false, true}
		};
		brett.roteSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{true, false, false, false, false, false},
			{true, false, true, false, false, false},
			{true, false, true, false, false, false},
			{true, false, true, false, false, false}
		};
		Fourinarow fia = new Fourinarow();
		try {
			fia.unentschieden(true, brett);
			assertTrue(false); // should have thrown an exception
		} catch(IllegalStateException e) {}
		
		assertTrue(unentschiedenAtLeastOneTrue());
		assertTrue(unentschiedenAtLeastOneFalse());
	}
	
	@Test
	public void testUnentschiedenFalse01() {
		checkUnentschiedenFalse01();
		assertTrue(unentschiedenAtLeastOneTrue());
		assertTrue(unentschiedenAtLeastOneFalse());
	}
	
	public void checkUnentschiedenFalse01() {
		Spielzustand brett = new Spielzustand();
		
		brett.gelbeSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false, false, false, false, false}, 
			{false, false, false, false, false, false}, 
			{false, false, false, false, false, false},
			{false, true, false, true, false, false},
			{false, true, false, true, false, false},
			{false, true, false, true, false, false}
		};
		brett.roteSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{true, false, true, false, false, false},
			{true, false, true, false, false, false},
			{true, false, true, false, false, false}
		};
		Fourinarow fia = new Fourinarow();
		assertFalse(fia.unentschieden(true, brett));
		assertFalse(fia.unentschieden(false, brett));
	}
	
	@Test
	public void testUnentschiedenFalse02() {
		checkUnentschiedenFalse02();
		assertTrue(unentschiedenAtLeastOneTrue());
		assertTrue(unentschiedenAtLeastOneFalse());
	}
	
	public void checkUnentschiedenFalse02() {
		Spielzustand brett = new Spielzustand();
		
		brett.gelbeSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false, false, false, false, false}, 
			{false, false, false, false, false, false}, 
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
		};
		brett.roteSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
		};
		Fourinarow fia = new Fourinarow();
		assertFalse(fia.unentschieden(true, brett));
		assertFalse(fia.unentschieden(false, brett));
	}
	
	@Test
	public void testUnentschiedenFalse03() {
		checkUnentschiedenFalse03();
		assertTrue(unentschiedenAtLeastOneTrue());
		assertTrue(unentschiedenAtLeastOneFalse());
	}
	
	public void checkUnentschiedenFalse03() {
		Spielzustand brett = new Spielzustand();
		
		brett.gelbeSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false, false, false, false, false}, 
			{false, false, false, false, false, false}, 
			{false, false, false, false, false, false},
			{false, false, true, false, false, false},
			{true, true, false, false, false, false},
			{true, false, false, true, false, false},
		};
		brett.roteSteine = new boolean[][] {
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, false, false, false},
			{false, false, false, true, false, false},
			{false, false, true, true, false, false},
			{false, true, true, false, true, false},
		};
		Fourinarow fia = new Fourinarow();
		assertFalse(fia.unentschieden(false, brett));
	}
	
	
	
	// -------------------------------------------------------------------
	// Auxiliaries
	// -------------------------------------------------------------------
	
	private void assertTopmost(Spielzustand brett, boolean roterSpieler, int col, int row) {
		for (int r=0; r < Spielzustand.reihen; r++) {
			if (r < row) {
				assertTrue(brett.gelbeSteine[col][r] || brett.roteSteine[col][r]);
			} else if (r == row) {
				if(!roterSpieler) {
					assertTrue(brett.gelbeSteine[col][r] && !brett.roteSteine[col][r]);
				} else {
					assertTrue(!brett.gelbeSteine[col][r] && brett.roteSteine[col][r]);
				}
			} else {
				assertFalse(brett.gelbeSteine[col][r] && brett.roteSteine[col][r]);
			}
		}
	}
	
	private static void isConsistent(Spielzustand brett) {
		for(int col=0; col < Spielzustand.spalten; col++) {
			boolean stoneBelow = true;
			for (int row=0; row < Spielzustand.reihen; row++) {
				assertFalse(brett.gelbeSteine[col][row] && brett.roteSteine[col][row]);
				boolean occupied = brett.gelbeSteine[col][row] || brett.roteSteine[col][row];
				assertTrue(!occupied || stoneBelow); 
				stoneBelow = occupied;
			}
		}
	}
}
