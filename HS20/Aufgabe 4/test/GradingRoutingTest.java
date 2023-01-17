import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class GradingRoutingTest {
	
	class GradingTestNode2021 implements Node {
		public int numberOfReceivedMessages = 0;
		
		@Override 
		public void receive(Message msg) {
			numberOfReceivedMessages += 1;
		}
	}
	
	@Test
	public void test01Candidates() {
		final GradingTestNode2021 t0 = new GradingTestNode2021();
		final GradingTestNode2021 t1 = new GradingTestNode2021();
		
		final Map<Integer, Node> routingTable = new HashMap<Integer, Node>();
		routingTable.put(0, t0);
		routingTable.put(201, t1);
		RoutingNode rNode = new RoutingNode(routingTable);
		
		{
			List<Set<Integer>> path1 = List.of(Set.of(1,2,201,8), Set.of(1,2,3,4,5), Set.of(9,8,20));		
			assertEquals(Set.of(201), rNode.candidates(path1));
		}
	}
	
	@Test
	public void test02Candidates() {
		final GradingTestNode2021 t1 = new GradingTestNode2021();
		final GradingTestNode2021 t2 = new GradingTestNode2021();
		final GradingTestNode2021 t3 = new GradingTestNode2021();
		
		final Map<Integer, Node> routingTable = new HashMap<Integer, Node>();		
		routingTable.put(4, t1);
		routingTable.put(5, t2);
		routingTable.put(8, t3);
		
		RoutingNode rNode = new RoutingNode(routingTable);
		
		{
			List<Set<Integer>> path1 = List.of(Set.of(1,2,8), Set.of(20), Set.of(20));		
			assertEquals(Set.of(8), rNode.candidates(path1));
		}
		
		{
			List<Set<Integer>> path2 = List.of(Set.of(5,200,4,500,700,8), Set.of(20), Set.of(20));		
			assertEquals(Set.of(4,5,8), rNode.candidates(path2));
		}
		
		{
			List<Set<Integer>> path3 = List.of(Set.of(5,200,500,700,8), Set.of(20), Set.of(20));		
			assertEquals(Set.of(5,8), rNode.candidates(path3));
		}
	}

	@Test
	public void test03SelectConnection() {
		final GradingTestNode2021 t1 = new GradingTestNode2021();
		final GradingTestNode2021 t2 = new GradingTestNode2021();
		final GradingTestNode2021 t3 = new GradingTestNode2021();
		final GradingTestNode2021 t4 = new GradingTestNode2021();

		{
			Map<Integer, Node> routingTable = new HashMap<Integer, Node>();		
			routingTable.put(42, t1);
			routingTable.put(11, t2);
			routingTable.put(54, t3);
			
			RoutingNode rNode = new RoutingNode(routingTable);
		
			assertEquals(11, rNode.selectConnection(Set.of(11,42,54)));
		}
		
		{
			Map<Integer, Node> routingTable2 = new HashMap<Integer, Node>();		
			routingTable2.put(59, t1);
			routingTable2.put(60, t2);
			routingTable2.put(29, t3);
			routingTable2.put(51, t4);

			RoutingNode rNode2 = new RoutingNode(routingTable2);
		
			assertEquals(29, rNode2.selectConnection(Set.of(59,60,29,51)));
		}
	}
	
	@Test
	public void test04SelectConnection() {
		final GradingTestNode2021 t1 = new GradingTestNode2021();
		final GradingTestNode2021 t2 = new GradingTestNode2021();
		final GradingTestNode2021 t3 = new GradingTestNode2021();

		
		final Map<Integer, Node> routingTable = new HashMap<Integer, Node>();		
		routingTable.put(7, t1);
		routingTable.put(8, t2);
		routingTable.put(9, t3);
		
		final RoutingNode rNode = new RoutingNode(routingTable);
		
		assertEquals(7, rNode.selectConnection(Set.of(7)));
		rNode.incrementCount(7);
		
		assertEquals(9, rNode.selectConnection(Set.of(9)));
		rNode.incrementCount(9);
				
		assertEquals(8, rNode.selectConnection(Set.of(7,8,9)));
		rNode.incrementCount(8);
		
		assertEquals(8, rNode.selectConnection(Set.of(8)));
		rNode.incrementCount(8);
		
		assertEquals(7, rNode.selectConnection(Set.of(7)));
		rNode.incrementCount(7);
		
		assertEquals(7, rNode.selectConnection(Set.of(7)));
		rNode.incrementCount(7);
		
		assertEquals(9, rNode.selectConnection(Set.of(7,8,9)));
	}
	
	@Test
	public void test05SelectConnection() {
		final GradingTestNode2021 t1 = new GradingTestNode2021();
		final GradingTestNode2021 t2 = new GradingTestNode2021();
		final GradingTestNode2021 t3 = new GradingTestNode2021();
		final GradingTestNode2021 t4 = new GradingTestNode2021();

		
		final Map<Integer, Node> routingTable = new HashMap<Integer, Node>();		
		routingTable.put(24, t1);
		routingTable.put(29, t2);
		routingTable.put(32, t3);
		routingTable.put(34, t4);
		
		final RoutingNode rNode = new RoutingNode(routingTable);
		
		assertEquals(24, rNode.selectConnection(Set.of(24)));
		rNode.incrementCount(24);
		
		assertEquals(29, rNode.selectConnection(Set.of(34,32,29,24)));
		rNode.incrementCount(29);
		
		assertEquals(34, rNode.selectConnection(Set.of(24,29,34)));
		rNode.incrementCount(34);
		
		assertEquals(32, rNode.selectConnection(Set.of(24,32,29,34)));
		rNode.incrementCount(32);
	}
	
	@Test
	public void test06ProcessRouting() {
		final RoutingNode r0 = new RoutingNode(new HashMap<Integer, Node>());
		final GradingTestNode2021 t0 = new GradingTestNode2021();
		final GradingTestNode2021 t1 = new GradingTestNode2021();
		final GradingTestNode2021 t2 = new GradingTestNode2021();
		
		final Map<Integer, Node> expectedMap = new HashMap<Integer, Node>();
		
		{
			UpdateMessage msg1 = new UpdateMessage(0, t0, List.of());
			r0.process(msg1);
			
			expectedMap.put(0, t0);
			assertEquals(expectedMap, r0.getRoutingTable());
		}
		
		{
			UpdateMessage msg2 = new UpdateMessage(1420, t1, List.of());
			r0.process(msg2);
			
			expectedMap.put(1420, t1);
			assertEquals(expectedMap, r0.getRoutingTable());
		}
		
		{
			UpdateMessage msg3 = new UpdateMessage(54, t2, List.of());
			r0.process(msg3);
			
			expectedMap.put(54, t2);
			assertEquals(expectedMap, r0.getRoutingTable());
		}
	}
	
	@Test
	public void test07ProcessRouting() {
		final RoutingNode r0 = new RoutingNode(new HashMap<Integer, Node>());
		final GradingTestNode2021 t0 = new GradingTestNode2021();
		final GradingTestNode2021 t1 = new GradingTestNode2021();
		
		final Map<Integer, Node> expectedMap = new HashMap<Integer, Node>();
		
		{
			r0.process(new UpdateMessage(20, t0, List.of()));
			
			expectedMap.put(20, t0);
			assertEquals(expectedMap, r0.getRoutingTable());
		}

		{
			r0.process(new BasicMessage(List.of()));
			r0.process(new IntMessage(10, List.of()));

			assertEquals(expectedMap, r0.getRoutingTable());			
		}
		
		{
			r0.process(new UpdateMessage(5040, t1, List.of()));
			
			expectedMap.put(5040, t1);
			assertEquals(expectedMap, r0.getRoutingTable());
		}
	}
	
	@Test
	public void test08CountingNode() {
		final CountingNode c0 = new CountingNode(new HashMap<Integer, Node>());
		
		c0.receive(new IntMessage(10, List.of()));
		assertEquals(10, c0.getSum());
		
		c0.receive(new IntMessage(251, List.of()));
		assertEquals(261, c0.getSum());
	}
	
	@Test
	public void test09CountingNode() {
		final GradingTestNode2021 t1 = new GradingTestNode2021();
		final GradingTestNode2021 t2 = new GradingTestNode2021();
		final GradingTestNode2021 t3 = new GradingTestNode2021();
		
		Map<Integer, Node> routingTable = new HashMap<Integer, Node>();		
		routingTable.put(42, t1);
		routingTable.put(11, t2);
		routingTable.put(54, t3);

		CountingNode c0 = new CountingNode(routingTable);
				
		c0.receive(new BasicMessage(List.of()));
		
		c0.receive(new IntMessage(5, List.of()));
		assertEquals(5, c0.getSum());
	}
	
	@Test
	public void test10ReceiveBasic() {
		final GradingTestNode2021 t1A = new GradingTestNode2021();
		final GradingTestNode2021 t1B = new GradingTestNode2021();
		final GradingTestNode2021 t2A = new GradingTestNode2021();
		final GradingTestNode2021 t2B = new GradingTestNode2021();
		
		RoutingNode r1;
		{
			Map<Integer, Node> r1Table = new HashMap<Integer, Node>();
			r1Table.put(10, t1A);
			r1Table.put(11, t1B);
			r1 = new RoutingNode(r1Table);
		}
		
		RoutingNode r2;
		{
			Map<Integer, Node> r2Table = new HashMap<Integer, Node>();
			r2Table.put(20, t2A);
			r2Table.put(21, t2B);
			r2 = new RoutingNode(r2Table);
		}
		
		RoutingNode r0;
		{
			Map<Integer, Node> r0Table = new HashMap<Integer, Node>();
			r0Table.put(0, r1);
			r0Table.put(1, r2);
			r0 = new RoutingNode(r0Table);
		}
		
		{
			Message msg1 = new BasicMessage(List.of(Set.of(0,1), Set.of(10,11,20,21)));
			r0.receive(msg1);
			assertEquals(1, t1A.numberOfReceivedMessages);
			assertEquals(0, t1B.numberOfReceivedMessages);
			assertEquals(0, t2A.numberOfReceivedMessages);
			assertEquals(0, t2B.numberOfReceivedMessages);			
		}
		
		{
			Message msg1 = new BasicMessage(List.of(Set.of(0,1), Set.of(10,11,20,21)));
			r0.receive(msg1);
			assertEquals(1, t1A.numberOfReceivedMessages);
			assertEquals(0, t1B.numberOfReceivedMessages);
			assertEquals(1, t2A.numberOfReceivedMessages);
			assertEquals(0, t2B.numberOfReceivedMessages);			
		}
		
		{
			Message msg1 = new BasicMessage(List.of(Set.of(0,1), Set.of(11,12,20,21)));
			r0.receive(msg1);
			assertEquals(1, t1A.numberOfReceivedMessages);
			assertEquals(1, t1B.numberOfReceivedMessages);
			assertEquals(1, t2A.numberOfReceivedMessages);
			assertEquals(0, t2B.numberOfReceivedMessages);			
		}
		
		{
			Message msg1 = new BasicMessage(List.of(Set.of(0,1), Set.of(11,12,20,21)));
			r0.receive(msg1);
			assertEquals(1, t1A.numberOfReceivedMessages);
			assertEquals(1, t1B.numberOfReceivedMessages);
			assertEquals(1, t2A.numberOfReceivedMessages);
			assertEquals(1, t2B.numberOfReceivedMessages);			
		}
	}
	
	@Test
	public void test11UpdateSelect() {
		final GradingTestNode2021 t1 = new GradingTestNode2021();
		final GradingTestNode2021 t2 = new GradingTestNode2021();
		final GradingTestNode2021 t3 = new GradingTestNode2021();
		final GradingTestNode2021 t4 = new GradingTestNode2021();

		
		final Map<Integer, Node> map = new HashMap<Integer, Node>();
		map.put(15, t1);
		map.put(47, t2);
		
		final RoutingNode r0 = new RoutingNode(map);
				
		assertEquals(15, r0.selectConnection(Set.of(15)));		
		r0.incrementCount(15);
		
		assertEquals(47, r0.selectConnection(Set.of(47)));
		r0.incrementCount(47);

		
		r0.process(new UpdateMessage(0, t3, List.of()));		
		assertEquals(0, r0.selectConnection(Set.of(0,15,47)));
		
		r0.incrementCount(0);
		
		r0.process(new UpdateMessage(47, t4, List.of()));
		
		assertEquals(47, r0.selectConnection(Set.of(0,15,47)));
	}
	
	@Test
	public void test12ReceiveCountingNode() {
		final GradingTestNode2021 t0 = new GradingTestNode2021();
		final GradingTestNode2021 t1 = new GradingTestNode2021();
		final GradingTestNode2021 t2 = new GradingTestNode2021();
		
		final HashMap<Integer, Node> c2Table = new HashMap<Integer, Node>();
		c2Table.put(300, t1);
		c2Table.put(400, t2);
		final CountingNode c2 = new CountingNode(c2Table);
		
		final HashMap<Integer, Node> c1Table = new HashMap<Integer, Node>();
		c1Table.put(100, t0);
		c1Table.put(200, c2);
		final CountingNode c1 = new CountingNode(c1Table);
		
		
		c1.receive(new IntMessage(5, List.of(Set.of(100))));
		
		assertEquals(1, t0.numberOfReceivedMessages);
		assertEquals(0, t1.numberOfReceivedMessages);
		assertEquals(0, t2.numberOfReceivedMessages);
		
		assertEquals(0, c1.getSum());
		assertEquals(0, c2.getSum());
		
		c1.receive(new IntMessage(17, List.of(Set.of(200), Set.of(300))));
		
		assertEquals(1, t0.numberOfReceivedMessages);
		assertEquals(1, t1.numberOfReceivedMessages);
		assertEquals(0, t2.numberOfReceivedMessages);
		
		assertEquals(0, c1.getSum());
		assertEquals(0, c2.getSum());		
				
		c1.receive(new IntMessage(17, List.of(Set.of(0,100))));

		assertEquals(2, t0.numberOfReceivedMessages);
		assertEquals(1, t1.numberOfReceivedMessages);
		assertEquals(0, t2.numberOfReceivedMessages);
		
		assertEquals(0, c1.getSum());
		assertEquals(0, c2.getSum());
		
		c1.receive(new IntMessage(17, List.of(Set.of(200))));
		
		assertEquals(0, c1.getSum());
		assertEquals(17, c2.getSum());
		
		c1.receive(new IntMessage(25, List.of()));
		
		assertEquals(25, c1.getSum());
		assertEquals(17, c2.getSum());
	}

}
