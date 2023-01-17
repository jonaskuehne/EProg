import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

public class GradingGraphExecutionTest {

	@Test
	public void testB01() {
		Node m1 = graphC1();
		
		assertTrue(GraphExecution.isSubProgram(m1, Node.newAddNode(8)));
		assertFalse(GraphExecution.isSubProgram(m1, Node.newAddNode(101)));
	}
	
	@Test
	public void testB02() {
		Node m3 = Node.newAddNode(2);
		Node m4 = Node.newAddNode(3);
		
		Node m2 = Node.newSeqNode(new Node[] {m3,m4});
		
		Node m1 = Node.newSeqNode(new Node[] {m2});
		
		assertTrue(GraphExecution.isSubProgram(m1, m2));

		Node m2New = Node.newSeqNode(new Node[] {Node.newAddNode(2), Node.newAddNode(3)});
		Node m2NewSwap = Node.newSeqNode(new Node[] {Node.newAddNode(3), Node.newAddNode(2)});			
		assertTrue(GraphExecution.isSubProgram(m1, m2New));
		assertTrue(GraphExecution.isSubProgram(m1, m2NewSwap));

		
		Node m2False = Node.newSeqNode(new Node[] {Node.newAddNode(4), Node.newAddNode(2)});
		
		assertFalse(GraphExecution.isSubProgram(m2, m2False));
	}
	
	@Test
	public void testB03() {
		Node m1 = graphC1();
		
		assertTrue(GraphExecution.isSubProgram(m1, m1));
		assertTrue(GraphExecution.isSubProgram(m1, m1.getSubnodes()[1]));
		
		assertFalse(GraphExecution.isSubProgram(m1, Node.newAddNode(101)));
	}
	
	@Test
	public void testB04() {
		Node m1 = graphC1();
		
		Node m5True = Node.newSeqNode(new Node[] {Node.newAddNode(8), Node.newAddNode(7)});		
		assertTrue(GraphExecution.isSubProgram(m1, m5True));
		
		Node m5False = Node.newSeqNode(new Node[] {Node.newAddNode(3), Node.newAddNode(10)});
		assertFalse(GraphExecution.isSubProgram(m1, m5False));
	}
	
	@Test
	public void testB05() {
		Node m1 = Node.newAddNode(2);
		
		assertTrue(GraphExecution.isSubProgram(m1, Node.newAddNode(2)));
		assertFalse(GraphExecution.isSubProgram(m1, Node.newAddNode(3)));
	}
	
	@Test
	public void testB06() {
		Node m1 = Node.newSeqNode(new Node[] {Node.newAddNode(1), Node.newAddNode(2)});
		
		assertTrue(GraphExecution.isSubProgram(m1, Node.newSeqNode(new Node[] {Node.newAddNode(1), Node.newAddNode(2)})));
		assertFalse(GraphExecution.isSubProgram(m1, Node.newSeqNode(new Node[] {Node.newAddNode(1), Node.newAddNode(4)})));
	}
	
	@Test
	public void testB07() {
		Node m1 = Node.newSeqNode(new Node[] {Node.newAddNode(1), Node.newAddNode(2), Node.newAddNode(10), Node.newAddNode(3)});
		
		assertTrue(GraphExecution.isSubProgram(m1, Node.newAddNode(10)));
		assertFalse(GraphExecution.isSubProgram(m1, Node.newAddNode(4)));
	}
	
	@Test
	public void testB08() {
		Node m1 = graphC2();
		
		assertTrue(GraphExecution.isSubProgram(
				m1, 
				m1.getSubnodes()[1]
				));
		
		assertFalse(GraphExecution.isSubProgram(
				m1, 
				Node.newSeqNode(
					new Node[] {Node.newAddNode(10), Node.newAddNode(12)}
					)
				));
	}
	
	@Test
	public void testB09() {
		Node m1 = graphC3();
		
		assertTrue(GraphExecution.isSubProgram(
				m1,
				Node.newSeqNode( new Node[] { Node.newAddNode(9), Node.newAddNode(11) } )
				));
		
		assertFalse(GraphExecution.isSubProgram(
				m1,
				Node.newSeqNode( new Node[] { Node.newAddNode(25), Node.newAddNode(29) } )
				));
	}
	
	@Test
	public void testB10() {
		Node m1 = graphC3();
		
		assertTrue(GraphExecution.isSubProgram(
				m1,
				m1.getSubnodes()[1]
				));
		
		assertFalse(GraphExecution.isSubProgram(
				m1,
				Node.newSeqNode( new Node[] {
						Node.newSeqNode( new Node[] {							
								Node.newSeqNode(
									new Node[] { Node.newAddNode(17)} )
								}
							)
						}
				)
				));
	}

	@Test
	public void testB11() {
		Node m1 = 
				Node.newSeqNode(new Node[] {
						Node.newSeqNode(new Node[] {Node.newAddNode(1), Node.newAddNode(2), Node.newAddNode(10), Node.newAddNode(3)})
						});
		
		assertTrue(GraphExecution.isSubProgram(
				m1, 
				Node.newSeqNode(new Node[] {Node.newAddNode(10), Node.newAddNode(1), Node.newAddNode(2), Node.newAddNode(3)})
				));
		
		assertFalse(GraphExecution.isSubProgram(
				m1, 
				Node.newSeqNode(new Node[] {Node.newAddNode(10), Node.newAddNode(4), Node.newAddNode(2), Node.newAddNode(3)})
				));
	}
	
	@Test
	public void testB12() {
		Node m1 = 
				Node.newSeqNode(new Node[] {
						Node.newAddNode(4000),
						Node.newSeqNode(new Node[] {Node.newAddNode(101), Node.newAddNode(300), Node.newAddNode(400)})
						});
		
		assertTrue(GraphExecution.isSubProgram(
				m1, 
				Node.newSeqNode(new Node[] {Node.newAddNode(101), Node.newAddNode(300), Node.newAddNode(400)})
				));
		
		assertFalse(GraphExecution.isSubProgram(
				m1, 
				Node.newSeqNode(new Node[] {Node.newAddNode(101), Node.newAddNode(300)})
				));
	}
	
	@Test
	public void testB13() {
		Node m1 = graphC2();
		
		assertTrue(GraphExecution.isSubProgram(
				m1, 
				Node.newSeqNode(
					new Node[] {Node.newAddNode(12), Node.newAddNode(11)}
					)
				));
		
		assertFalse(GraphExecution.isSubProgram(
				m1, 
				Node.newSeqNode(
					new Node[] {Node.newAddNode(2), Node.newAddNode(2)}
					)
				));
	}
	
	@Test
	public void testB14() {
		Node m1 = graphC3();
		
		assertTrue(GraphExecution.isSubProgram(
				m1,
				Node.newSeqNode( new Node[] {
						/** level 2 **/
						Node.newAddNode(1),
						Node.newSeqNode( new Node[] { Node.newAddNode(15), Node.newAddNode(13) }),
						Node.newSeqNode( new Node[] { Node.newAddNode(11), Node.newAddNode(9) } )
						}
					)
				));
		
		assertFalse(GraphExecution.isSubProgram(
				m1,
				Node.newSeqNode( new Node[] {
						/** level 2 **/
						Node.newAddNode(1),
						Node.newSeqNode( new Node[] { Node.newAddNode(15), Node.newAddNode(13) }),
						Node.newSeqNode( new Node[] { Node.newAddNode(12), Node.newAddNode(17) } )
						}
					)
				));
	}

	

	/** example graphs **/
	
	public Node graphC1() {
		Node m2 = Node.newAddNode(1);
				
		Node m7 = Node.newAddNode(8);
		Node m8 = Node.newAddNode(7);
		Node m5 = Node.newSeqNode(new Node[] {m7,m8});
		
		Node m6 = Node.newAddNode(-2);
		Node m3 = Node.newSeqNode(new Node[] {m5,m6});
		
		Node m4 = Node.newAddNode(5);
		
		Node m1 = Node.newSeqNode(new Node[] {m2,m3,m4});
		
		return m1;		
	}
	
	public Node graphC2() {
		return
				Node.newSeqNode( new Node[] {
						Node.newAddNode(10),
						Node.newSeqNode(
								new Node[] {Node.newAddNode(11), Node.newAddNode(12)}
							),
						Node.newAddNode(23)						
				});
	}
	
	public Node graphC3() {
		return
				Node.newSeqNode( new Node[] {
						/** level 1 **/
						Node.newSeqNode( new Node[] {
								/** level 2 **/
								Node.newSeqNode( new Node[] { Node.newAddNode(9), Node.newAddNode(11) } ),
								Node.newSeqNode( new Node[] { Node.newAddNode(13), Node.newAddNode(15) }),
								Node.newAddNode(1)
								}
							),
						Node.newSeqNode( new Node[] {
								/** level 2 **/
								Node.newSeqNode( new Node[] { Node.newAddNode(17)} )
							}
						)
					});
	}



}
