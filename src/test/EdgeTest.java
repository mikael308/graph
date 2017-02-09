package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import main.graph.Edge;
import main.graph.Vertex;

public class EdgeTest {

	public Vertex<Integer> randomIntVertex(){
		return new Vertex<Integer>(RandomGenerator.number());
	}
	public Vertex<String> randomStrVertex(){
		return new Vertex<String>(RandomGenerator.string());
	}
	
	@Test
	public void testEquals() {

		for(int i = 0; i < 1_000; i++){
			Vertex<Integer> v = randomIntVertex();
			assertEquals(new Edge<Integer>(v),
					new Edge<Integer>(v));
			
			List<Integer>rnds = RandomGenerator.uniqueNumbers(2);
			assertNotEquals(new Edge<Integer>(new Vertex<Integer>(rnds.get(0))),
					new Edge<Integer>(new Vertex<Integer>(rnds.get(1))));
			
			String str = RandomGenerator.string();
			assertEquals(new Edge<String>(new Vertex<String>(str)),
					new Edge<String>(new Vertex<String>(str)));
			
			String sval1 = RandomGenerator.string();
			String sval2;
			do{
				sval2 = RandomGenerator.string();
			}while(sval1.equals(sval2));
			
			assertNotEquals(new Edge<String>(new Vertex<String>(sval1)),
					new Edge<String>(new Vertex<String>(sval2)));
		}

		// extreme values
		Vertex<Integer> vMin = new Vertex<Integer>(Integer.MIN_VALUE);
		Vertex<Integer> vMax = new Vertex<Integer>(Integer.MAX_VALUE);
		
		assertEquals(new Edge<Integer>(vMin),
				new Edge<Integer>(vMin));
		assertEquals(new Edge<Integer>(vMax),
				new Edge<Integer>(vMax));
		assertNotEquals(new Edge<Integer>(vMax),
				new Edge<Integer>(vMin));
	}
	
	@Test
	public void testCompare(){
		int min = Integer.MIN_VALUE; int max = Integer.MAX_VALUE;
		assertEquals(0,
				new Edge<Integer>(new Vertex<Integer>(max), min)
				.compareTo(new Edge<Integer>(new Vertex<Integer>(min), min)));
		assertEquals(0,
				new Edge<Integer>(new Vertex<Integer>(max), max)
				.compareTo(new Edge<Integer>(new Vertex<Integer>(min), max)));
		assertEquals(0,
				new Edge<Integer>(new Vertex<Integer>(max))
				.compareTo(new Edge<Integer>(new Vertex<Integer>(max))));
		
		for(int i = 0; i < 1_000; i++){
			String s = RandomGenerator.string();
			assertEquals(0,
					new Edge<String>(new Vertex<String>(s))
					.compareTo(new Edge<String>(new Vertex<String>(s))));
			
			List<Integer> rnds = RandomGenerator.uniqueNumbers(2);
			int rmin = Math.min(rnds.get(0), rnds.get(1));
			int rmax = Math.max(rnds.get(0), rnds.get(1));
			// a > b edges
			assertEquals(-1,
					new Edge<Integer>(randomIntVertex(), rmin)
					.compareTo(new Edge<Integer>(randomIntVertex(), rmax)));
			// string vertices values
			assertEquals(-1,
					new Edge<String>(randomStrVertex(), rmin)
					.compareTo(new Edge<String>(randomStrVertex(), rmax)));
			// a < b edges
			assertEquals(1,
					new Edge<Integer>(randomIntVertex(), rmax)
					.compareTo(new Edge<Integer>(randomIntVertex(), rmin)));
			
		}
	}

}
