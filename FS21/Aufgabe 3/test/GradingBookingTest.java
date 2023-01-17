package Aufgabe_3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

public class GradingBookingTest {

	/** a) **/

	@Test
	public void testFifo01() {
		BookingSystem system = createBookingSystem();
		Ticket t1 = system.reserve("p1", 14);
		assertNotNull(t1);

		Ticket t2 = system.reserve("p2", 2);
		assertNotNull(t2);
	}

	@Test
	public void testFifo02() {
		BookingSystem system = createBookingSystem();
		Ticket t1 = system.reserve("p410", 412);
		assertNotNull(t1);

		assertFalse(t1.isReady());

		Ticket t2 = system.reserve("p92", 918);
		assertNotNull(t2);

		assertFalse(t1.isReady());
		assertFalse(t2.isReady());

		system.createProduct("pOther", "fifo");
		assertFalse(t1.isReady());
		assertFalse(t2.isReady());
	}

	@Test
	public void testFifo03() {
		BookingSystem system = createBookingSystem();
		system.createProduct("someProduct", "fifo");
		system.createProduct("someProduct2", "fifo");
		system.createProduct("someProduct3", "fifo");

		assertThrows(IllegalArgumentException.class, () -> system.createProduct("someProduct", "lifo"));
		assertThrows(IllegalArgumentException.class, () -> system.createProduct("someProduct", "random"));
	}

	@Test
	public void testFifo04() {
		BookingSystem system = createBookingSystem();

		Ticket t = system.reserve("phone", 1);
		assertNotNull(t);

		assertThrows(IllegalArgumentException.class, () -> t.getProduct());
	}

	@Test
	public void testFifo05() {
		BookingSystem system = createBookingSystem();

		Ticket t = system.reserve("phone", 10);
		assertNotNull(t);
		assertFalse(t.isReady());

		system.createProduct("phone", "fifo");

		assertTrue(t.isReady());
	}

	@Test
	public void testFifo06() {
		BookingSystem system = createBookingSystem();

		Ticket t = system.reserve("phone", 10);
		assertNotNull(t);

		system.createProduct("phone", "fifo");

		Product phone = t.getProduct();
		assertNotNull(phone);

		assertEquals("phone", phone.name());
		assertEquals("fifo", phone.kind());
	}

	@Test
	public void testFifo07() {
		BookingSystem system = createBookingSystem();

		Ticket t1 = system.reserve("computer", 25);
		assertNotNull(t1);

		system.createProduct("computer", "fifo");

		Product computer = t1.getProduct();
		assertNotNull(computer);

		Ticket t2 = system.reserve("computer2", 49);
		assertNotNull(t2);

		system.createProduct("computer2", "fifo");

		Product computer2 = t2.getProduct();
		assertNotNull(computer2);

		assertNotSame(computer, computer2);
	}

	@Test
	public void testFifo08() {
		BookingSystem system = createBookingSystem();
		system.createProduct("tv", "fifo");

		Ticket t1 = system.reserve("tv", 401);
		assertNotNull(t1);

		Product tv = t1.getProduct();
		assertNotNull(tv);

		assertEquals("tv", tv.name());
		assertEquals("fifo", tv.kind());
	}

	@Test
	public void testFifo09() {
		BookingSystem system = createBookingSystem();

		Ticket t1 = system.reserve("guitar", 2);
		assertNotNull(t1);

		Ticket t2 = system.reserve("guitar", 1);
		assertNotNull(t2);

		assertFalse(t1.isReady());
		assertFalse(t2.isReady());

		system.createProduct("guitar", "fifo");

		assertTrue(t1.isReady());
		assertFalse(t2.isReady());

		system.createProduct("guitar2", "fifo");

		assertTrue(t1.isReady());
		assertFalse(t2.isReady());
	}

	@Test
	public void testFifo10() {
		BookingSystem system = createBookingSystem();

		Ticket t1 = system.reserve("key", 21);
		assertNotNull(t1);

		Ticket t2 = system.reserve("house", 29);
		assertNotNull(t2);

		system.createProduct("house", "fifo");
		assertFalse(t1.isReady());
		assertTrue(t2.isReady());

		system.createProduct("key", "fifo");
		assertTrue(t1.isReady());
		assertTrue(t2.isReady());
	}

	@Test
	public void testFifo11() {
		BookingSystem system = createBookingSystem();

		Ticket t1 = system.reserve("car", 20);
		assertNotNull(t1);

		Ticket t2 = system.reserve("car", 23);
		assertNotNull(t2);

		system.createProduct("car", "fifo");

		Product p1 = t1.getProduct();
		assertNotNull(p1);

		assertFalse(t2.isReady());

		p1.giveBack();

		assertTrue(t2.isReady());

		Product p2 = t2.getProduct();
		assertNotNull(p2);

		assertSame(p1, p2);
	}

	@Test
	public void testFifo12() {
		BookingSystem system = createBookingSystem();

		Ticket t1 = system.reserve("pen", 500);
		assertNotNull(t1);

		system.createProduct("pen", "fifo");

		Product p1 = t1.getProduct();
		assertNotNull(p1);

		p1.giveBack();

		assertThrows(IllegalArgumentException.class, () -> p1.giveBack());
	}

	@Test
	public void testFifo13() {
		BookingSystem system = createBookingSystem();

		ArrayList<Ticket> pizzaTickets = new ArrayList<Ticket>();

		for (int i = 0; i < 10; i++) {
			Ticket pizzaTicket = system.reserve("pizza", i);
			assertNotNull(pizzaTicket);

			system.reserve("pasta", i);
			pizzaTickets.add(pizzaTicket);
		}

		allTicketsReady(pizzaTickets, false);

		system.createProduct("pizza", "fifo");

		Product uniquePizzaProduct = null;

		for (int i = 0; i < pizzaTickets.size(); i++) {
			Ticket curTicket = pizzaTickets.get(i);
			assertTrue(curTicket.isReady());

			allTicketsReady(pizzaTickets.subList(0, i + 1), true);
			allTicketsReady(pizzaTickets.subList(i + 1, pizzaTickets.size()), false);

			Product newPizzaProduct = curTicket.getProduct();
			assertNotNull(newPizzaProduct);

			if (uniquePizzaProduct != null) {
				assertSame(uniquePizzaProduct, newPizzaProduct);
			} else {
				uniquePizzaProduct = newPizzaProduct;
			}

			newPizzaProduct.giveBack();
		}
	}

	/** b) **/
	@Test
	public void testPriority01() {
		BookingSystem system = createBookingSystem();

		Ticket t1 = system.reserve("football", 10);
		assertNotNull(t1);

		system.createProduct("football", "priority");

		Product p1 = t1.getProduct();
		assertEquals("football", p1.name());
		assertEquals("priority", p1.kind());
	}

	@Test
	public void testPriority02() {
		BookingSystem system = createBookingSystem();

		Ticket t1 = system.reserve("ebook", 10);
		assertNotNull(t1);

		Ticket t2 = system.reserve("ebook", 11);
		assertNotNull(t2);

		assertFalse(t1.isReady());
		assertFalse(t2.isReady());

		system.createProduct("ebook", "priority");

		assertFalse(t1.isReady());
		assertTrue(t2.isReady());
	}

	@Test
	public void testPriority03() {
		BookingSystem system = createBookingSystem();

		Ticket t0 = system.reserve("book", 2);
		assertNotNull(t0);

		Ticket t1 = system.reserve("book", 10);
		assertNotNull(t1);

		Ticket t2 = system.reserve("book", 10);
		assertNotNull(t2);

		assertFalse(t0.isReady());
		assertFalse(t1.isReady());
		assertFalse(t2.isReady());

		system.createProduct("book", "priority");

		assertFalse(t0.isReady());
		assertTrue(t1.isReady());
		assertFalse(t2.isReady());
	}

	@Test
	public void testPriority04() {
		BookingSystem system = createBookingSystem();

		system.createProduct("laptop", "priority");

		Ticket t1 = system.reserve("laptop", 5);
		assertNotNull(t1);

		assertTrue(t1.isReady());

		Ticket t2 = system.reserve("laptop", 5000);
		assertNotNull(t2);

		assertTrue(t1.isReady());
		assertFalse(t2.isReady());
	}

	@Test
	public void testPriority05() {
		BookingSystem system = createBookingSystem();

		Ticket t1 = system.reserve("museum", 20);
		assertNotNull(t1);

		Ticket t2 = system.reserve("hotel", 40);
		assertNotNull(t2);

		Ticket t3 = system.reserve("museum", 60);
		assertNotNull(t3);

		Ticket t4 = system.reserve("hotel", 70);
		assertNotNull(t4);

		system.createProduct("museum", "priority");
		system.createProduct("hotel", "fifo");

		assertFalse(t1.isReady());
		assertTrue(t2.isReady());
		assertTrue(t3.isReady());
		assertFalse(t4.isReady());
	}

	@Test
	public void testPriority06() {
		BookingSystem system = createBookingSystem();

		Ticket t1 = system.reserve("sunglasses", 20);
		assertNotNull(t1);

		Ticket t2 = system.reserve("sunglasses", 40);
		assertNotNull(t2);

		Ticket t3 = system.reserve("sunglasses", 60);
		assertNotNull(t3);

		Ticket t4 = system.reserve("sunglasses", 30);
		assertNotNull(t4);

		system.createProduct("sunglasses", "priority");

		assertFalse(t1.isReady());
		assertFalse(t2.isReady());
		assertTrue(t3.isReady());
		assertFalse(t4.isReady());

		Product p1 = t3.getProduct();
		assertNotNull(p1);

		p1.giveBack();

		assertFalse(t1.isReady());
		assertTrue(t2.isReady());
		assertTrue(t3.isReady());
		assertFalse(t4.isReady());

		assertSame(p1, t2.getProduct());

		p1.giveBack();

		assertFalse(t1.isReady());
		assertTrue(t2.isReady());
		assertTrue(t3.isReady());
		assertTrue(t4.isReady());

		assertSame(p1, t4.getProduct());

		p1.giveBack();

		assertTrue(t1.isReady());
		assertTrue(t2.isReady());
		assertTrue(t3.isReady());
		assertTrue(t4.isReady());

		assertSame(p1, t1.getProduct());
	}

	@Test
	public void testPriority07() {
		BookingSystem system = createBookingSystem();

		Ticket t1 = system.reserve("ebook", 10);
		assertNotNull(t1);

		Ticket t2 = system.reserve("ebook", 9);
		assertNotNull(t2);

		assertFalse(t1.isReady());
		assertFalse(t2.isReady());

		system.createProduct("ebook", "priority");

		assertTrue(t1.isReady());
		assertFalse(t2.isReady());

		Ticket t3 = system.reserve("ebook", 500);
		assertFalse(t3.isReady());

		system.createProduct("ebook", "priority");

		assertFalse(t2.isReady());
		assertTrue(t3.isReady());

		system.createProduct("ebook", "priority");

		assertTrue(t2.isReady());
	}

	@Test
	public void testPriority08() {
		BookingSystem system = createBookingSystem();

		Ticket t1 = system.reserve("chessboard", 25);
		assertNotNull(t1);

		system.createProduct("chessboard", "fifo");

		Product chessboard1 = t1.getProduct();
		assertNotNull(chessboard1);

		Ticket t2 = system.reserve("chessboard", 49);
		assertNotNull(t2);

		system.createProduct("chessboard", "fifo");

		Product chessboard2 = t2.getProduct();
		assertNotNull(chessboard2);

		assertNotSame(chessboard1, chessboard2);
		assertEquals(chessboard1.name(), chessboard2.name());
	}

	@Test
	public void testPriority09() {
		BookingSystem system = createBookingSystem();

		Ticket t1 = system.reserve("guitar", 1);
		assertNotNull(t1);

		Ticket t2 = system.reserve("guitar", 2);
		assertNotNull(t2);

		system.createProduct("guitar", "fifo");

		assertTrue(t1.isReady());
		assertFalse(t2.isReady());

		system.createProduct("guitar", "fifo");

		assertTrue(t1.isReady());
		assertTrue(t2.isReady());
	}

	@Test
	public void testPriority10() {
		BookingSystem system = createBookingSystem();

		ArrayList<Ticket> pizzaTickets = new ArrayList<Ticket>();

		for (int i = 0; i < 5; i++) {
			Ticket pizzaTicket = system.reserve("pizza", i);
			Ticket pizzaTicket2 = system.reserve("pizza", i);

			system.reserve("pasta", i);
			assertNotNull(pizzaTicket);
			assertNotNull(pizzaTicket2);
			pizzaTickets.add(pizzaTicket);
			pizzaTickets.add(pizzaTicket2);
		}

		allTicketsReady(pizzaTickets, false);

		system.createProduct("pizza", "fifo");
		system.createProduct("pizza", "fifo");

		Product uniquePizzaProduct1 = null;
		Product uniquePizzaProduct2 = null;

		for (int i = 0; i < pizzaTickets.size() / 2; i++) {
			Ticket curTicket1 = pizzaTickets.get(2 * i);
			Ticket curTicket2 = pizzaTickets.get(2 * i + 1);
			assertTrue(curTicket1.isReady());
			assertTrue(curTicket2.isReady());

			allTicketsReady(pizzaTickets.subList(0, 2 * i + 2), true);
			allTicketsReady(pizzaTickets.subList(2 * i + 2, pizzaTickets.size()), false);

			Product newPizzaProduct1 = curTicket1.getProduct();
			assertNotNull(newPizzaProduct1);

			Product newPizzaProduct2 = curTicket2.getProduct();
			assertNotNull(newPizzaProduct2);

			if (uniquePizzaProduct1 != null && uniquePizzaProduct2 != null) {
				assertSame(uniquePizzaProduct1, newPizzaProduct1);
				assertSame(uniquePizzaProduct2, newPizzaProduct2);
				assertNotSame(newPizzaProduct1, newPizzaProduct2);
			} else {
				uniquePizzaProduct1 = newPizzaProduct1;
				uniquePizzaProduct2 = newPizzaProduct2;
			}

			newPizzaProduct1.giveBack();
			newPizzaProduct2.giveBack();
		}
	}

	/** c) **/
	@Test
	public void testMulti01() {
		BookingSystem system = createBookingSystem();
		Ticket t = system.multiReserve(new String[] { "A", "B" }, 10);
		assertNotNull(t);
	}

	@Test
	public void testMulti02() {
		BookingSystem system = createBookingSystem();
		assertThrows(IllegalArgumentException.class, () -> system.multiReserve(null, 10));
	}

	@Test
	public void testMulti03() {
		BookingSystem system = createBookingSystem();
		assertThrows(IllegalArgumentException.class, () -> system.multiReserve(new String[] {}, 10));
	}

	@Test
	public void testMulti04() {
		BookingSystem system = createBookingSystem();
		Ticket t = system.multiReserve(new String[] { "A", "B" }, 10);
		assertNotNull(t);

		assertFalse(t.isReady());

		system.createProduct("B", "fifo");

		assertFalse(t.isReady());

		system.createProduct("A", "fifo");

		assertTrue(t.isReady());
	}

	@Test
	public void testMulti05() {
		BookingSystem system = createBookingSystem();
		Ticket t = system.multiReserve(new String[] { "A2", "B2" }, 10);
		assertNotNull(t);

		system.createProduct("B2", "fifo");
		system.createProduct("A2", "fifo");

		Product aProd = t.getProduct();
		Product bProd = t.getProduct();

		assertNotSame(aProd, bProd);

		for (int i = 0; i < 5; i++) {
			assertSame(aProd, t.getProduct());
			assertSame(bProd, t.getProduct());
		}
	}

	@Test
	public void testMulti06() {
		BookingSystem system = createBookingSystem();
		Ticket t = system.multiReserve(new String[] { "chips", "avocado" }, 10);
		assertNotNull(t);

		system.createProduct("avocado", "fifo");
		system.createProduct("chips", "priority");

		Product chipsProd = t.getProduct();
		assertNotNull(chipsProd);

		assertEquals("chips", chipsProd.name());
		assertEquals("priority", chipsProd.kind());

		Product avocadoProd = t.getProduct();
		assertNotNull(avocadoProd);

		assertEquals("avocado", avocadoProd.name());
		assertEquals("fifo", avocadoProd.kind());
	}

	@Test
	public void testMulti07() {
		BookingSystem system = createBookingSystem();
		Ticket t = system.multiReserve(new String[] { "chips", "avocado" }, 10);
		assertNotNull(t);

		assertThrows(IllegalArgumentException.class, () -> t.getProduct());

		system.createProduct("chips", "fifo");

		assertThrows(IllegalArgumentException.class, () -> t.getProduct());
	}

	@Test
	public void testMulti08() {
		BookingSystem system = createBookingSystem();

		Ticket tMulti = system.multiReserve(new String[] { "A", "B", "C" }, 10);
		assertNotNull(tMulti);

		Ticket t0 = system.multiReserve(new String[] { "A" }, 10);
		assertNotNull(t0);

		Ticket t1 = system.multiReserve(new String[] { "B" }, 20);
		assertNotNull(t1);

		Ticket t2 = system.multiReserve(new String[] { "C" }, 30);
		assertNotNull(t2);

		assertFalse(t0.isReady());
		assertFalse(t1.isReady());
		assertFalse(t2.isReady());
		assertFalse(tMulti.isReady());

		system.createProduct("A", "fifo");

		assertFalse(t0.isReady());
		assertFalse(t1.isReady());
		assertFalse(t2.isReady());
		assertFalse(tMulti.isReady());

		system.createProduct("B", "fifo");

		assertFalse(t0.isReady());
		assertFalse(t1.isReady());
		assertFalse(t2.isReady());
		assertFalse(tMulti.isReady());

		system.createProduct("C", "fifo");

		assertFalse(t0.isReady());
		assertFalse(t1.isReady());
		assertFalse(t2.isReady());
		assertTrue(tMulti.isReady());
	}

	@Test
	public void testMulti09() {
		BookingSystem system = createBookingSystem();

		Ticket t0 = system.multiReserve(new String[] { "A" }, 10);
		assertNotNull(t0);

		Ticket t1 = system.multiReserve(new String[] { "B" }, 20);
		assertNotNull(t1);

		Ticket t2 = system.multiReserve(new String[] { "C" }, 30);
		assertNotNull(t2);

		Ticket tMulti = system.multiReserve(new String[] { "A", "B", "C" }, 500);
		assertNotNull(tMulti);

		assertFalse(t0.isReady());
		assertFalse(t1.isReady());
		assertFalse(t2.isReady());
		assertFalse(tMulti.isReady());

		system.createProduct("A", "priority");

		assertFalse(t0.isReady());
		assertFalse(t1.isReady());
		assertFalse(t2.isReady());
		assertFalse(tMulti.isReady());

		system.createProduct("B", "priority");

		assertFalse(t0.isReady());
		assertFalse(t1.isReady());
		assertFalse(t2.isReady());
		assertFalse(tMulti.isReady());

		system.createProduct("C", "priority");

		assertFalse(t0.isReady());
		assertFalse(t1.isReady());
		assertFalse(t2.isReady());
		assertTrue(tMulti.isReady());
	}

	@Test
	public void testMulti10() {
		BookingSystem system = createBookingSystem();

		Ticket t1 = system.multiReserve(new String[] { "B", "D" }, 1);
		assertNotNull(t1);

		Ticket t2 = system.multiReserve(new String[] { "A", "B", "C", "D" }, 1);
		assertNotNull(t2);

		system.createProduct("A", "fifo");
		system.createProduct("B", "fifo");
		system.createProduct("C", "fifo");
		system.createProduct("D", "fifo");

		assertTrue(t1.isReady());
		assertFalse(t2.isReady());

		Product bProd = t1.getProduct();
		assertNotNull(bProd);

		Product dProd = t1.getProduct();
		assertNotNull(dProd);

		bProd.giveBack();

		assertFalse(t2.isReady());

		dProd.giveBack();

		assertTrue(t2.isReady());
	}

	public static void allTicketsReady(Collection<Ticket> tickets, boolean isReady) {
		for (Ticket t : tickets) {
			assertEquals(isReady, t.isReady());
		}
	}

	public static BookingSystem createBookingSystem() {
		return new BookingSystem();
	}

}