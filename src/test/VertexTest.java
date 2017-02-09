package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import main.graph.Vertex;

public class VertexTest {

	
	@Test
	public void testEquals() {
		
		// random values
		for(int i = 0; i < 1_000;i++){
			int vval = RandomGenerator.number();
			assertEquals(new Vertex<Integer>(vval),
					new Vertex<Integer>(vval));
			
			List<Integer> rnds = RandomGenerator.uniqueNumbers(2);
			assertNotEquals(new Vertex<Integer>(rnds.get(0)),
					new Vertex<Integer>(rnds.get(1)));
			
			String str = RandomGenerator.string();
			assertEquals(new Vertex<String>(str),
					new Vertex<String>(str));
			assertEquals(new Vertex<String>(""),
					new Vertex<String>(""));
			
			String sval1 = RandomGenerator.string();
			String sval2;
			do{
				sval2 = RandomGenerator.string();
			}while(sval1.equals(sval2));
			assertNotEquals(new Vertex<String>(sval1),
					new Vertex<String>(sval2));
			
			if(! sval1.isEmpty())
				assertNotEquals(new Vertex<String>(sval1),
						new Vertex<String>(""));
		}
		
		// extreme values
		Vertex<Integer> vMin = new Vertex<Integer>(Integer.MIN_VALUE);
		Vertex<Integer> vMax = new Vertex<Integer>(Integer.MAX_VALUE);
		
		assertEquals(vMin, vMin);
		assertEquals(vMax, vMax);
		assertNotEquals(vMin, vMax);
		
	}
	
}
