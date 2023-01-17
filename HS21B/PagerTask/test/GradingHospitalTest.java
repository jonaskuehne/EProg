import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GradingHospitalTest {
	
	/** a) **/
	
	@Test
	public void testA01Normal() {		
		Hospital h = new Hospital();
		
		Pager alice = normalCreateAndRegister(h, "alice");
		
		assertNotNull(alice.inbox());
		assertEquals(List.of(), alice.inbox());
	}
	
	@Test
	public void testA02Normal() {
		Hospital h = new Hospital();
		
		Pager alice = normalCreateAndRegister(h, "alice");
		Pager bob = normalCreateAndRegister(h, "bob");
		
		alice.command("bob", new TextMessage("Hi Bob"));
		
		assertEquals(List.of(new TextMessage("Hi Bob")), bob.inbox());
	}
	
	@Test
	public void testA03Normal() {
		Hospital h = new Hospital();
		
		Pager alice = normalCreateAndRegister(h, "alice");
		Pager bob = normalCreateAndRegister(h, "bob");
		
		alice.command("bob", new TextMessage("Hi Bob"));
		
		assertEquals(List.of(new TextMessage("Hi Bob")), bob.inbox());
		
		alice.command("bob", new TextMessage("Hello Bob"));
		
		assertEquals(List.of(new TextMessage("Hi Bob"), new TextMessage("Hello Bob")), bob.inbox());
		
		alice.command("bob", new TextMessage("Bye Bob"));
		
		assertEquals(List.of(new TextMessage("Hi Bob"), new TextMessage("Hello Bob"), new TextMessage("Bye Bob")), bob.inbox());
	}
	
	@Test
	public void testA04Normal() {
		Hospital h = new Hospital();
		
		Pager alice = normalCreateAndRegister(h, "alice");
		Pager bob = normalCreateAndRegister(h, "bob");
		Pager charlie = normalCreateAndRegister(h, "charlie");

		alice.command("bob", new TextMessage("Hallo Bob"));

		assertEquals(List.of(new TextMessage("Hallo Bob")), bob.inbox());
		
		bob.command("charlie", new TextMessage("Hallo Charlie"));
		
		assertEquals(List.of(new TextMessage("Hallo Bob")), bob.inbox());
		assertEquals(List.of(new TextMessage("Hallo Charlie")), charlie.inbox());
		
		charlie.command("alice", new TextMessage("Hallo Alice"));
		
		assertEquals(List.of(new TextMessage("Hallo Alice")), alice.inbox());	
		assertEquals(List.of(new TextMessage("Hallo Bob")), bob.inbox());
		assertEquals(List.of(new TextMessage("Hallo Charlie")), charlie.inbox());		
	}
	
	@Test
	public void testA05Normal() {
		Hospital h = new Hospital();
		
		Pager [] pagers = new Pager[5];
		String [] names = new String[5];
		
		for(int i = 0; i < pagers.length; i++) {
			names[i] = "p"+i;
			pagers[i] = normalCreateAndRegister(h, names[i]);
		}
		
		List<List<Message>> expectedMessages = new ArrayList<List<Message>>();
		for(int i = 0; i < pagers.length; i++) {
			expectedMessages.add(new ArrayList<Message>());
		}
		
		for(int i = 0; i < pagers.length; i++) {
			for(int j = 0; j < pagers.length; j++) {
				if(i != j) {
					String payload = "From p" + i + " to p"+j;
					pagers[i].command(names[j], new TextMessage(payload));
					
					expectedMessages.get(j).add(new TextMessage(payload));
					
					for(int k = 0; k < pagers.length; k++) {
						assertEquals(expectedMessages.get(k), pagers[k].inbox());
					}
				}
			}
		}
	}
	
	@Test
	public void testA06Normal() {
		Hospital h = new Hospital();
		
		Pager alice = normalCreateAndRegister(h, "alice");
		
		alice.command("bob", new TextMessage("test"));
		
		assertEquals(List.of(new PagerNotRegisteredMessage("bob")), alice.inbox());
	}
	
	@Test
	public void testA07Normal() {
		Hospital h = new Hospital();
		
		Pager alice = normalCreateAndRegister(h, "alice");
		
		alice.command("bob", new TextMessage("test"));

		assertEquals(List.of(new PagerNotRegisteredMessage("bob")), alice.inbox());
		
		Pager bob = normalCreateAndRegister(h, "bob");
		
		assertEquals(List.of(new PagerNotRegisteredMessage("bob")), alice.inbox());
		assertEquals(List.of(), bob.inbox());
		
		alice.command("charlie", new TextMessage("test"));

		assertEquals(List.of(new PagerNotRegisteredMessage("bob"), new PagerNotRegisteredMessage("charlie")), alice.inbox());
		assertEquals(List.of(), bob.inbox());
		
		bob.command("alice", new TextMessage("Hi Alice"));

		assertEquals(List.of(new PagerNotRegisteredMessage("bob"), new PagerNotRegisteredMessage("charlie"), new TextMessage("Hi Alice")), alice.inbox());
		assertEquals(List.of(), bob.inbox());
	}
	
	@Test
	public void testA08Normal() {
		Hospital h = new Hospital();
		
		Pager alice = normalCreateAndRegister(h, "alice");
		
		Pager alice2 = h.createPager("normal");
		assertThrows(IllegalArgumentException.class, () -> alice2.register("alice"));
	}
	
	@Test
	public void testA09Normal() {
		Hospital h = new Hospital();
		
		Pager james = normalCreateAndRegister(h, "james");
		Pager bill = normalCreateAndRegister(h, "bill");		
		
		
		bill.register("bill2");

		james.command("bill", new TextMessage("report to floor 1"));
		
		assertEquals(List.of(new PagerNotRegisteredMessage("bill")), james.inbox());
	}
	
	@Test
	public void testA10Normal() {
		Hospital h = new Hospital();
		
		Pager james = normalCreateAndRegister(h, "james");
		Pager bill = normalCreateAndRegister(h, "bill");		
		
		james.command("bill", new TextMessage("report to floor 1"));
		
		assertEquals(List.of(new TextMessage("report to floor 1")), bill.inbox());
		
		bill.register("bill2");

		assertEquals(List.of(new TextMessage("report to floor 1")), bill.inbox());
		
		james.command("bill2", new TextMessage("Bye Bill"));
		
		assertEquals(List.of(new TextMessage("report to floor 1"), new TextMessage("Bye Bill")), bill.inbox());
	}
	
	@Test
	public void testA11Slow() {
		Hospital h = new Hospital();
		
		Pager alice = slowCreateAndRegister(h, "alice");
		
		assertNotNull(alice.inbox());
		assertEquals(List.of(), alice.inbox());
	}
	
	@Test
	public void testA12Slow() {
		Hospital h = new Hospital();
		
		Pager alice = slowCreateAndRegister(h, "alice");
		Pager bob = slowCreateAndRegister(h, "bob");
		
		alice.command("bob", new TextMessage("Hi Bob"));
		
		assertEquals(List.of(), bob.inbox());
		
		alice.command("bob", new TextMessage("Hi Bob 2"));

		assertEquals(List.of(), bob.inbox());
		
		alice.command("bob", new TextMessage("Hi Bob 3"));

		assertEquals(List.of(new TextMessage("Hi Bob"), new TextMessage("Hi Bob 2"), new TextMessage("Hi Bob 3")), bob.inbox());
	}
	
	@Test
	public void testA13Slow() {
		Hospital h = new Hospital();
		
		Pager alice = slowCreateAndRegister(h, "alice");
		Pager bob = slowCreateAndRegister(h, "bob");
		
		List<Message> expectedMessages = new ArrayList<Message>();
		
		for(int i = 0; i < 4; i++) {
			alice.command("bob", new TextMessage("msg"+3*i));
			
			assertEquals(expectedMessages, bob.inbox());
			
			alice.command("bob", new TextMessage("msg"+(3*i+1)));

			assertEquals(expectedMessages, bob.inbox());
			
			alice.command("bob", new TextMessage("msg"+(3*i+2)));
			
			expectedMessages.add(new TextMessage("msg"+3*i));
			expectedMessages.add(new TextMessage("msg"+(3*i+1)));
			expectedMessages.add(new TextMessage("msg"+(3*i+2)));

			assertEquals(expectedMessages, bob.inbox());
		}
	}
	
	@Test
	public void testA14Slow() {
		Hospital h = new Hospital();
		
		Pager [] pagers = new Pager[3];
		String [] names = new String[3];
		
		for(int i = 0; i < 3; i++) {
			names[i] = "p"+i;
			pagers[i] = slowCreateAndRegister(h, names[i]);
		}
		
		List<List<Message>> expectedMessages = new ArrayList<List<Message>>();
		for(int i = 0; i < 3; i++) {
			expectedMessages.add(new ArrayList<Message>());
		}
		
		for(int i = 0; i < 3; i++) {
			int dest1 = (i+1)%3;
			int dest2 = (i+2)%3;
			
			pagers[i].command(names[dest1], new TextMessage("msg "+i+" "+dest1));
			pagers[i].command(names[dest2], new TextMessage("msg "+i+" "+dest2));
			
			expectedMessages.get(dest1).add(new TextMessage("msg "+i+" "+dest1));
			expectedMessages.get(dest2).add(new TextMessage("msg "+i+" "+dest2));
			
			for(int j = 0; j < 3; j++) {
				assertEquals(List.of(), pagers[j].inbox());
			}
		}
		
			
		pagers[0].command(names[1], new TextMessage("msgB 0 1"));
		//message from 0 to 1 skips ahead of the first message from p2 to p1
		expectedMessages.get(1).add(1, new TextMessage("msgB 0 1"));

		assertEquals(List.of(), pagers[0].inbox());
		assertEquals(2, pagers[1].inbox().size());
		assertEquals(1, pagers[2].inbox().size());
			
		pagers[1].command(names[2], new TextMessage("msgB 1 2"));
		expectedMessages.get(2).add(new TextMessage("msgB 1 2"));

		assertEquals(1, pagers[0].inbox().size());
		assertEquals(2, pagers[1].inbox().size());
		assertEquals(expectedMessages.get(2), pagers[2].inbox());

		pagers[2].command(names[0], new TextMessage("msgB 2 0"));
		expectedMessages.get(0).add(new TextMessage("msgB 2 0"));

		assertEquals(expectedMessages.get(0), pagers[0].inbox());
		assertEquals(expectedMessages.get(1), pagers[1].inbox());
		assertEquals(expectedMessages.get(2), pagers[2].inbox());
	}
	
	@Test
	public void testA15Slow() {
		Hospital h = new Hospital();
		
		Pager alice = slowCreateAndRegister(h, "alice");
		
		alice.command("bob", new TextMessage("test 1"));
		alice.command("bob", new TextMessage("test 2"));
		alice.command("bob", new TextMessage("test 3"));
		
		assertEquals(List.of(new PagerNotRegisteredMessage("bob"),
				             new PagerNotRegisteredMessage("bob"),
				             new PagerNotRegisteredMessage("bob")), 
				     alice.inbox());
	}
	
	@Test
	public void testA16Slow() {
		Hospital h = new Hospital();
		
		Pager alice = slowCreateAndRegister(h, "alice");
				
		alice.command("bob", new TextMessage("msg 1"));
		
		Pager bob = slowCreateAndRegister(h, "bob");
		
		alice.command("bob", new TextMessage("msg 2"));		
		alice.command("charlie", new TextMessage("msg 3"));
		
		
		assertEquals(List.of(new PagerNotRegisteredMessage("charlie")), alice.inbox());
		assertEquals(List.of(new TextMessage("msg 1"), new TextMessage("msg 2")), bob.inbox());	
	}
	
	@Test
	public void testA17Slow() {
		Hospital h = new Hospital();
		
		Pager alice = slowCreateAndRegister(h, "alice");
		
		Pager alice2 = h.createPager("slow");
		assertThrows(IllegalArgumentException.class, () -> alice2.register("alice"));
	}
	
	@Test
	public void testA18SlowNormal() {
		Hospital h = new Hospital();
		
		Pager pNormal = normalCreateAndRegister(h, "p1");
		Pager pSlow = slowCreateAndRegister(h, "p2");
		
		pNormal.command("p2", new TextMessage("p1-p2-1"));
		
		assertEquals(List.of(), pNormal.inbox());
		assertEquals(List.of(new TextMessage("p1-p2-1")), pSlow.inbox());
		
		pSlow.command("p1", new TextMessage("p2-p1-1"));
		
		assertEquals(List.of(), pNormal.inbox());
		assertEquals(List.of(new TextMessage("p1-p2-1")), pSlow.inbox());

		pSlow.command("p1", new TextMessage("p2-p1-2"));
		
		assertEquals(List.of(), pNormal.inbox());
		assertEquals(List.of(new TextMessage("p1-p2-1")), pSlow.inbox());

		pSlow.command("p1", new TextMessage("p2-p1-3"));
		
		assertEquals(List.of(new TextMessage("p2-p1-1"), new TextMessage("p2-p1-2"), new TextMessage("p2-p1-3")), pNormal.inbox());
		assertEquals(List.of(new TextMessage("p1-p2-1")), pSlow.inbox());
	}
	
	/** b) **/

	@Test
	public void testB01() {
		Hospital h = new Hospital();
		
		Pager eve = adminCreateAndRegister(h, "eve");
		
		assertNotNull(eve.inbox());
		assertEquals(List.of(), eve.inbox());
	}
	
	@Test
	public void testB02Text() {
		Hospital h = new Hospital();
		
		Pager eve = adminCreateAndRegister(h, "eve");
		
		eve.command("broadcast", new TextMessage("msg"));
		
		assertEquals(List.of(new TextMessage("msg")), eve.inbox());
	}
	
	@Test
	public void testB03Text() {
		Hospital h = new Hospital();
		
		Pager eve = adminCreateAndRegister(h, "eve");
		
		Pager bob = normalCreateAndRegister(h, "bob");
		
		eve.command("broadcast", new TextMessage("broadcast_msg"));
		
		assertEquals(List.of(new TextMessage("broadcast_msg")), bob.inbox());
		assertEquals(List.of(new TextMessage("broadcast_msg")), eve.inbox());
		
		eve.command("dest", new TextMessage("broadcast_msg2"));
		
		assertEquals(List.of(new TextMessage("broadcast_msg"), new TextMessage("broadcast_msg2")), bob.inbox());
		assertEquals(List.of(new TextMessage("broadcast_msg"), new TextMessage("broadcast_msg2")), eve.inbox());
	}
	
	@Test
	public void testB04Text() {
		Hospital h = new Hospital();
		
		Pager p1 = adminCreateAndRegister(h, "p1");
		
		Pager [] normalPagers = new Pager[3];
		for(int i = 0; i < normalPagers.length; i++) {
			normalPagers[i] = normalCreateAndRegister(h, "pNormal"+i);
		}
		
		List<Message> expectedMessages = new ArrayList<Message>();
		for(int i = 0; i < 10; i++) {
			p1.command("test", new TextMessage("msg"+i+" admin"));
			expectedMessages.add(new TextMessage("msg"+i+" admin"));
			
			for(Pager normalPager : normalPagers) {
				assertEquals(expectedMessages, normalPager.inbox());
			}
		}				
	}
	
	@Test
	public void testB05Text() {
		Hospital h = new Hospital();
		
		Pager [] pagers = new Pager [7];
		
		for(int i = 0; i < pagers.length; i++) {
			pagers[i] = adminCreateAndRegister(h, "p"+i);
		}
		
		List<Message> expectedMessages = new ArrayList<Message>();
		
		for(int i = 0; i < pagers.length; i++) {
			String payload = "msg_"+i;
			
			pagers[i].command("broadcast", new TextMessage(payload));
			expectedMessages.add(new TextMessage(payload));
			
			for(int j = 0; j < pagers.length; j++) {
				assertEquals(expectedMessages, pagers[j].inbox());
			}
		}
	}

	@Test
	public void testB06Text() {
		Hospital h = new Hospital();

		Pager eve = adminCreateAndRegister(h, "eve");
		Pager bob = normalCreateAndRegister(h, "bob");		
		
		eve.command("a", new TextMessage("someMessage"));
		
		Pager anna = normalCreateAndRegister(h, "anna");
		
		eve.command("a", new TextMessage("someMessage2"));
		
		assertEquals(List.of(new TextMessage("someMessage"), new TextMessage("someMessage2")), bob.inbox());
		assertEquals(List.of(new TextMessage("someMessage2")), anna.inbox());
	}
	
	@Test
	public void testB07Text() {
		Hospital h = new Hospital();

		Pager eve = adminCreateAndRegister(h, "eve");
		
		Pager eve2 = h.createPager("admin");
		
		assertThrows(IllegalArgumentException.class, () -> eve2.register("eve"));
	}

	@Test
	public void testB08Pause() {
		Hospital h = new Hospital();
		
		Pager anna = adminCreateAndRegister(h, "anna");
		
		Pager bob = normalCreateAndRegister(h, "bob");
		
		bob.command("anna", new TextMessage("Hi Anna"));
		
		assertEquals(List.of(new TextMessage("Hi Anna")), anna.inbox());
		
		anna.command("bob", new PauseMessage());
		
		bob.command("anna", new TextMessage("Hi Anna 2"));
		
		assertEquals(List.of(new TextMessage("Hi Anna")), anna.inbox());
	}
	
	@Test
	public void testB09Pause() {
		Hospital h = new Hospital();
		
		Pager anna = adminCreateAndRegister(h, "anna");
		
		Pager bob = normalCreateAndRegister(h, "bob");
		
		bob.command("anna", new TextMessage("Hi Anna"));
		
		assertEquals(List.of(new TextMessage("Hi Anna")), anna.inbox());
		
		anna.command("bob", new PauseMessage());
		
		bob.command("anna", new TextMessage("Hi Anna 2"));
		
		assertEquals(List.of(new TextMessage("Hi Anna")), anna.inbox());
		
		anna.command("bob", new PauseMessage());

		bob.command("anna", new TextMessage("Hi Anna 3"));

		assertEquals(List.of(new TextMessage("Hi Anna"), new TextMessage("Hi Anna 3")), anna.inbox());
	}
	
	@Test
	public void testB10Pause() {
		Hospital h = new Hospital();
		
		Pager anna = adminCreateAndRegister(h, "anna");
		Pager eve = adminCreateAndRegister(h, "eve");
		Pager alex = adminCreateAndRegister(h, "alex");
		
		anna.command("broadcast", new TextMessage("Hi all"));
		
		alex.command("anna", new PauseMessage());
		
		anna.command("broadcast", new TextMessage("Hi all 2"));

		alex.command("anna", new PauseMessage());

		anna.command("broadcast", new TextMessage("Hi all 3"));

		assertEquals(List.of(new TextMessage("Hi all"), new TextMessage("Hi all 3")), alex.inbox());
		assertEquals(List.of(new TextMessage("Hi all"), new TextMessage("Hi all 3")), eve.inbox());
	}
	
	@Test
	public void testB11Pause() {
		Hospital h = new Hospital();
		
		Pager anna = adminCreateAndRegister(h, "anna");
		Pager charlie = normalCreateAndRegister(h, "charlie");
		
		anna.command("bob", new PauseMessage());
		
		Pager bob = normalCreateAndRegister(h, "bob");
		
		bob.command("charlie", new TextMessage("Hello Charlie"));
		
		assertEquals(List.of(), charlie.inbox());
		
		anna.command("bob", new PauseMessage());

		bob.command("charlie", new TextMessage("Hello Charlie 2"));
		
		assertEquals(List.of(new TextMessage("Hello Charlie 2")), charlie.inbox());
	}
	
	@Test
	public void testB12Pause() {
		Hospital h = new Hospital();

		Pager p1 = adminCreateAndRegister(h, "p1");
		
		Pager p2 = normalCreateAndRegister(h, "p2");
		
		p1.command("p2", new PauseMessage());
		
		p2.register("p2Other");
		
		p2.command("p1", new TextMessage("msg"));
		
		assertEquals(List.of(new TextMessage("msg")), p1.inbox());
	}
	
	@Test
	public void testB13Pause() {
		Hospital h = new Hospital();
		
		Pager anna = adminCreateAndRegister(h, "anna");				
		Pager bob = normalCreateAndRegister(h, "bob");
		Pager charlie = normalCreateAndRegister(h, "charlie");
		
		anna.command("bob", new PauseMessage());
		
		bob.command("charlie", new TextMessage("Hi Charlie"));
		
		assertEquals(List.of(), charlie.inbox());
		
		bob.register("bob2");
		
		bob.command("charlie", new TextMessage("Hi Charlie 2"));
		
		assertEquals(List.of(new TextMessage("Hi Charlie 2")), charlie.inbox());
	}
	
	@Test
	public void testB14Pause() {
		Hospital h = new Hospital();
		
		Pager anna = adminCreateAndRegister(h, "anna");		
		
		Pager bob = normalCreateAndRegister(h, "bob");
		Pager charlie = slowCreateAndRegister(h, "charlie");
		Pager david = slowCreateAndRegister(h, "david");
		
		anna.command("charlie", new PauseMessage());
		
		charlie.command("bob", new TextMessage("Hi Bob"));
		charlie.command("anna", new TextMessage("Hi Anna"));
		
		assertEquals(List.of(), bob.inbox());
		assertEquals(List.of(), anna.inbox());
		
		anna.command("charlie", new PauseMessage());
		
		charlie.command("david", new TextMessage("Hi David"));
		
		assertEquals(List.of(new TextMessage("Hi Anna")), anna.inbox());
		assertEquals(List.of(new TextMessage("Hi Bob")), bob.inbox());
		assertEquals(List.of(new TextMessage("Hi David")), david.inbox());
		
		anna.command("david", new PauseMessage());
		
		david.command("anna", new TextMessage("david msg 1"));
		david.command("anna", new TextMessage("david msg 2"));
		david.command("anna", new TextMessage("david msg 3"));
		
		assertEquals(List.of(new TextMessage("Hi Anna")), anna.inbox());
	}
	
	/** c) **/
	@Test
	public void testC01Normal() {
		Hospital h = new Hospital();
		
		Pager anna = normalCreateAndRegister(h, "anna");
		Pager bob = normalCreateAndRegister(h, "bob");		
		
		anna.command("bob", new QueryMessage());
		
		assertEquals(List.of(new QueryAnswerMessage("bob", 0)), anna.inbox());
	}
	
	@Test
	public void testC02Normal() {
		Hospital h = new Hospital();
		
		Pager anna = normalCreateAndRegister(h, "anna");
		Pager bob = normalCreateAndRegister(h, "bob");
		
		bob.command("anna", new TextMessage("Hi Anna"));
		
		anna.command("bob", new QueryMessage());
		
		assertEquals(List.of(new TextMessage("Hi Anna"), new QueryAnswerMessage("bob", 1)), anna.inbox());		
	}
	
	@Test
	public void testC03Normal() {
		Hospital h = new Hospital();
		
		Pager anna = normalCreateAndRegister(h, "anna");
		Pager bob = normalCreateAndRegister(h, "bob");		
		
		bob.command("anna", new TextMessage("Hi Anna"));
		anna.command("bob", new TextMessage("Hi Bob"));
		anna.command("bob", new TextMessage("msg1"));
		anna.command("bob", new TextMessage("msg2"));
		anna.command("bob", new TextMessage("msg3"));
		anna.command("bob", new TextMessage("msg4"));


		bob.command("anna", new QueryMessage());
		
		assertEquals(List.of(
				new TextMessage("Hi Bob"),
				new TextMessage("msg1"),
				new TextMessage("msg2"),
				new TextMessage("msg3"),
				new TextMessage("msg4"),
				new QueryAnswerMessage("anna", 5)), bob.inbox());		
	}
	
	@Test
	public void testC04Normal() {
		Hospital h = new Hospital();
		
		Pager anna = normalCreateAndRegister(h, "anna");
		Pager bob = normalCreateAndRegister(h, "bob");		
		Pager charlie = normalCreateAndRegister(h, "charlie");
		
		charlie.command("anna", new TextMessage("Hi Anna"));
		
		anna.command("bob", new QueryMessage());
		
		assertEquals(List.of(new TextMessage("Hi Anna"), new QueryAnswerMessage("bob", 0)), anna.inbox());
		
		bob.command("anna", new TextMessage("msg bob"));
		
		charlie.command("anna", new TextMessage("msg charlie"));
		charlie.command("anna", new TextMessage("msg charlie 2"));
		charlie.command("anna", new TextMessage("msg charlie 3"));
		
		anna.command("charlie", new QueryMessage());
		anna.command("bob", new QueryMessage());

		
		assertEquals(List.of(
				new TextMessage("Hi Anna"), 
				new QueryAnswerMessage("bob", 0),
				new TextMessage("msg bob"),
				new TextMessage("msg charlie"),
				new TextMessage("msg charlie 2"),
				new TextMessage("msg charlie 3"),
				new QueryAnswerMessage("charlie", 4),
				new QueryAnswerMessage("bob", 1)), anna.inbox());		
	}

	@Test
	public void testC05Normal() {
		Hospital h = new Hospital();
		
		Pager p1 = normalCreateAndRegister(h, "p1");

		p1.command("p2", new QueryMessage());
		
		assertEquals(List.of(new PagerNotRegisteredMessage("p2")), p1.inbox());		
	}
	
	@Test
	public void testC06Normal() {
		Hospital h = new Hospital();
		
		Pager p1 = normalCreateAndRegister(h, "p1");		
		Pager p2 = normalCreateAndRegister(h, "p2");
		
		p1.command("p2", new TextMessage("msg1"));
		p1.command("p2", new TextMessage("msg2"));
		
		p2.register("p2New");
		
		p1.command("p2New", new TextMessage("msg3"));
		p1.command("p2New", new TextMessage("msg4"));
		p1.command("p2New", new TextMessage("msg5"));
		p1.command("p2New", new TextMessage("msg6"));
		p1.command("p2New", new TextMessage("msg7"));
		
		p2.command("p1", new QueryMessage());
		
		assertEquals(List.of(
				 new TextMessage("msg1"), 
	             new TextMessage("msg2"), 
	             new TextMessage("msg3"),
	             new TextMessage("msg4"),
	             new TextMessage("msg5"),
	             new TextMessage("msg6"),
	             new TextMessage("msg7"),
	             new QueryAnswerMessage("p1", 5)), p2.inbox());
	}
	
	@Test
	public void testC07Normal() {
		Hospital h = new Hospital();
		
		Pager p1 = normalCreateAndRegister(h, "p1");		
		Pager p2 = normalCreateAndRegister(h, "p2");
		
		p1.command("p2", new TextMessage("msg1"));
		p1.command("p2", new TextMessage("msg2"));
		
		p1.register("p1New");
		
		p1.command("p2", new TextMessage("msg3"));
		p1.command("p2", new TextMessage("msg4"));
		p1.command("p2", new TextMessage("msg5"));
		p1.command("p2", new TextMessage("msg6"));
		
		p2.command("p1New", new QueryMessage());
		
		assertEquals(List.of(
				 new TextMessage("msg1"), 
	             new TextMessage("msg2"), 
	             new TextMessage("msg3"),
	             new TextMessage("msg4"),
	             new TextMessage("msg5"),
	             new TextMessage("msg6"),
	             new QueryAnswerMessage("p1New", 4)), p2.inbox());
	}

	@Test
	public void testC08Admin() {
		Hospital h = new Hospital();
		
		Pager p1 = adminCreateAndRegister(h, "p1");
		Pager p2 = adminCreateAndRegister(h, "p2");
		
		p2.command("test", new TextMessage("msg1"));
		p2.command("test", new TextMessage("msg2"));
		
		p1.command("p2", new QueryMessage());
		
		assertEquals(List.of(new TextMessage("msg1"), new TextMessage("msg2"), new QueryAnswerMessage("p2", 2)), p1.inbox());				
	}
	
	@Test
	public void testC09Slow() {
		Hospital h = new Hospital();
		
		Pager p1 = normalCreateAndRegister(h, "p1");
		Pager p2 = slowCreateAndRegister(h, "p2");
		
		p2.command("p1", new TextMessage("msg1"));
		p2.command("p1", new TextMessage("msg2"));
		
		p1.command("p2", new QueryMessage());
		
		assertEquals(List.of(new QueryAnswerMessage("p2", 0)), p1.inbox());		
	}
	
	@Test
	public void testC10Slow() {
		Hospital h = new Hospital();
		
		Pager p1 = normalCreateAndRegister(h, "p1");
		Pager p2 = slowCreateAndRegister(h, "p2");
		
		p2.command("p1", new QueryMessage());
		p2.command("p1", new TextMessage("msg1"));
		
		p1.command("p2", new TextMessage("msg2"));
		p1.command("p2", new TextMessage("msg3"));
		p1.command("p2", new TextMessage("msg4"));
		p1.command("p2", new TextMessage("msg5"));
		
		p2.command("p1", new TextMessage("msg6"));
		
		assertEquals(List.of(
				new TextMessage("msg2"),
				new TextMessage("msg3"),
				new TextMessage("msg4"),
				new TextMessage("msg5"),
				new QueryAnswerMessage("p1", 4)
				), p2.inbox()
				);
	}
	
	
	/** Hilfsfunktionen **/
	
	public static Pager normalCreateAndRegister(Hospital h, String name) {
		return createAndRegister(h, name, "normal");
	}
	
	public static Pager slowCreateAndRegister(Hospital h, String name) {
		return createAndRegister(h, name, "slow");
	}
	
	public static Pager adminCreateAndRegister(Hospital h, String name) {
		return createAndRegister(h, name, "admin");
	}
	
	public static Pager createAndRegister(Hospital h, String name, String role) {
		if( ! ( role.equals("normal") || role.equals("slow") || role.equals("admin") ) ) {
			throw new IllegalArgumentException();
		}
		Pager p = h.createPager(role);
		assertNotNull(p);
		p.register(name);
		return p;
	}
}
