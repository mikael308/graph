package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import main.graph.Edge;
import main.graph.Graph;
import main.graph.Vertex;

public class GraphTest {
	
	private static final int _graph1VerticesSize = 10;
	private static final int _graph2VerticesSize = 10;
	private static final int _graph3VerticesSize = 7;
	private static final int _graph4VerticesSize = 6;
	
	private static final int _uniqueVerticesSize = 100;
	private static List<Vertex<String>> _unqVs = new ArrayList<Vertex<String>>(_uniqueVerticesSize);
	
	@BeforeClass
	public static void setupUniqueVertices(){
		for(int i = 0; i < _uniqueVerticesSize; i++){
			String s = null;
			do {
				s = RandomGenerator.string(32);
				
			} while(_unqVs.contains(s));
			Vertex<String> v = new Vertex<String>(s);
			_unqVs.add(v);
		}
	}

	private Graph<String> getGraph1(){
		Graph<String> graph = new Graph<String>();
		// add all to graph
		for(int i = 0; i < _graph2VerticesSize; i++){
			graph.add(_unqVs.get(i));
		}
		graph.connectUndirected(_unqVs.get(0), _unqVs.get(1), 4);
		graph.connectUndirected(_unqVs.get(1), _unqVs.get(1), 1);
		graph.connectUndirected(_unqVs.get(1), _unqVs.get(2), 7);
		graph.connectUndirected(_unqVs.get(2), _unqVs.get(2), 2);
		graph.connectUndirected(_unqVs.get(2), _unqVs.get(3), 4);
		graph.connectUndirected(_unqVs.get(2), _unqVs.get(4), 23);
		graph.connectUndirected(_unqVs.get(2), _unqVs.get(9), 9);
		graph.connectUndirected(_unqVs.get(3), _unqVs.get(7), 19);
		graph.connectUndirected(_unqVs.get(4), _unqVs.get(5), 9);
		graph.connectUndirected(_unqVs.get(4), _unqVs.get(7), 4);
		graph.connectUndirected(_unqVs.get(4), _unqVs.get(8), 1);
		graph.connectUndirected(_unqVs.get(5), _unqVs.get(8), 2);
		graph.connectUndirected(_unqVs.get(7), _unqVs.get(8), 4);
		graph.connectUndirected(_unqVs.get(8), _unqVs.get(9), 13);
		
		return graph;
	}
	private Graph<String> getGraph2(){
		Graph<String> graph = new Graph<String>();
		// add all to graph
		for(int i = 0; i < _graph2VerticesSize; i++){
			graph.add(_unqVs.get(i));
		}
		
		graph.connectUndirected(_unqVs.get(0), _unqVs.get(0), 1);
		graph.connectUndirected(_unqVs.get(0), _unqVs.get(4), 4);
		graph.connectUndirected(_unqVs.get(0), _unqVs.get(7), 2);
		graph.connectUndirected(_unqVs.get(1), _unqVs.get(1), 2);
		graph.connectUndirected(_unqVs.get(1), _unqVs.get(4), 4);
		graph.connectUndirected(_unqVs.get(2), _unqVs.get(5), 4);
		graph.connectUndirected(_unqVs.get(3), _unqVs.get(4), 7);
		graph.connectUndirected(_unqVs.get(4), _unqVs.get(5), 4);
		graph.connectUndirected(_unqVs.get(4), _unqVs.get(6), 23);
		graph.connectUndirected(_unqVs.get(4), _unqVs.get(7), 11);
		graph.connectUndirected(_unqVs.get(4), _unqVs.get(8), 19);
		graph.connectUndirected(_unqVs.get(5), _unqVs.get(6), 1);
		graph.connectUndirected(_unqVs.get(5), _unqVs.get(9), 13);
		graph.connectUndirected(_unqVs.get(6), _unqVs.get(8), 9);
		graph.connectUndirected(_unqVs.get(6), _unqVs.get(9), 9);
		graph.connectUndirected(_unqVs.get(8), _unqVs.get(9), 2);
		
		return graph;
	}
	private Graph<String> getGraph3(){
		Graph<String> graph = new Graph<String>();

		// add all to graph
		for(int i = 0; i < _graph3VerticesSize; i++){
			graph.add(_unqVs.get(i));
		}
		
		graph.connectUndirected(_unqVs.get(0), _unqVs.get(1), 2);
		graph.connectUndirected(_unqVs.get(0), _unqVs.get(2), 4);
		graph.connectUndirected(_unqVs.get(0), _unqVs.get(3), 1);
		graph.connectUndirected(_unqVs.get(1), _unqVs.get(3), 3);
		graph.connectUndirected(_unqVs.get(1), _unqVs.get(4), 10);
		graph.connectUndirected(_unqVs.get(2), _unqVs.get(3), 2);
		graph.connectUndirected(_unqVs.get(2), _unqVs.get(5), 5);
		graph.connectUndirected(_unqVs.get(3), _unqVs.get(4), 7);
		graph.connectUndirected(_unqVs.get(3), _unqVs.get(5), 8);
		graph.connectUndirected(_unqVs.get(3), _unqVs.get(6), 4);
		graph.connectUndirected(_unqVs.get(4), _unqVs.get(6), 6);
		graph.connectUndirected(_unqVs.get(5), _unqVs.get(6), 1);

		return graph;
	}
	private Graph<String> getGraph4(){
		Graph<String> graph = new Graph<String>();
		
		graph.add(_unqVs.get(0));
		graph.add(_unqVs.get(1));
		graph.add(_unqVs.get(2));
		graph.add(_unqVs.get(3));
		graph.add(_unqVs.get(4));
		graph.add(_unqVs.get(5));
		
		graph.connectDirected(_unqVs.get(0), _unqVs.get(0), 1);
		graph.connectUndirected(_unqVs.get(0), _unqVs.get(3), 2);
		graph.connectUndirected(_unqVs.get(1), _unqVs.get(1), 2);
		graph.connectUndirected(_unqVs.get(1), _unqVs.get(4), 10);
		graph.connectDirected(_unqVs.get(2), _unqVs.get(0), 10);
		graph.connectDirected(_unqVs.get(3), _unqVs.get(5), 6);
		graph.connectDirected(_unqVs.get(4), _unqVs.get(3), 4);
		graph.connectDirected(_unqVs.get(5), _unqVs.get(2), 9);
		
		return graph;
	}
	
	@Test
	public void testGetAllVertices(){
		List<Vertex<String>> added = new ArrayList<Vertex<String>>();
		Graph<String> graph = new Graph<String>();
		for(int i = 0; i  <43; i++){
			graph.add(_unqVs.get(i));
			added.add(_unqVs.get(i));
			
			List<Vertex<String>> allVertices = graph.getAllVertices();
			for(Vertex<String> v : allVertices){
				assertEquals(i+1, allVertices.size());
				assertTrue(added.contains(v));
			}
		}
		
		// ------------------------------------------
		
		// get and count vertices 
		Graph<String> graph1 = getGraph1();
		List<Vertex<String>> graph1Vs = graph1.getAllVertices();
		assertEquals(graph1Vs.size(), graph1.verticesSize());
		assertEquals(_graph1VerticesSize, graph1.verticesSize());
		
		// remove all graph1s vertices
		for (int i = 0; i < graph1.verticesSize(); i++){
			Vertex<String> v = _unqVs.get(i);
			assertTrue(graph1Vs.remove(v));
		}
		assertEquals(0, graph1Vs.size());
		
		// get and count vertices 
		graph1Vs = graph1.getAllVertices();
		assertEquals(graph1Vs.size(), graph1.verticesSize());
		assertEquals(_graph1VerticesSize, graph1.verticesSize());
		
		
	}
	
	@Test
	public void testAdjacentVertices(){
		Graph<String> graph1 = getGraph1();
		int nVertices = _graph1VerticesSize;
		int[] expectedSizes = new int[]{
				1, 3, 5,
				2, 4, 2,
				0, 3, 4, 2
		};
		
		for(int i = 0; i < nVertices; i++){
			List<Vertex<String>> adjs = graph1.getAdjacentVertices(_unqVs.get(i));
			try {
				assertEquals(expectedSizes[i], adjs.size());
				
			} catch (AssertionError e){
				fail("vertex: "+_unqVs.get(i)+"  " +e.getMessage());
			}
		}
		
		List<String> expected = new ArrayList<String>();
		expected.add(_unqVs.get(8).getValue());
		expected.add(_unqVs.get(7).getValue());
		expected.add(_unqVs.get(5).getValue());
		expected.add(_unqVs.get(2).getValue());
		List<Vertex<String>> adjsV4 = graph1.getAdjacentVertices(_unqVs.get(4));
		
		assertEquals(adjsV4.size(), expected.size());
		for (int i = 0; i < adjsV4.size(); i++){
			Vertex<String> v = new Vertex<String>(expected.get(i));
			assertEquals(v, adjsV4.get(i));
		}
		 // remove vertices
		graph1.remove(_unqVs.get(3));
		List<Vertex<String>> adjs = graph1.getAdjacentVertices(_unqVs.get(2));
		assertEquals(4, adjs.size());
		
		graph1.remove(_unqVs.get(9));
		adjs = graph1.getAdjacentVertices(_unqVs.get(5));
		assertEquals(2, adjs.size());
		
	}
	@Test
	public void testAdd(){
		Graph<String> graph = new Graph<String>();
		
		// add all from _unqVs
		// ===================================
		for(int i = 0; i < _unqVs.size(); i++){
			assertTrue(graph.add(_unqVs.get(i)));
		}
		for(int i = 0; i < _unqVs.size(); i++){
			assertFalse(graph.add(_unqVs.get(i)));
		}
		
		// ------------------------------------------
		
		// add all from _unqVs in random order
		// ===================================
		Graph<String> graph2 = new Graph<String>();
		List<Integer> uIdxs = RandomGenerator.uniqueNumbers(_unqVs.size()-1, 0, _unqVs.size()-1);
		int i = 0;
		try{
			for (i = 0; i < uIdxs.size(); i++){
				assertTrue(graph2.add(_unqVs.get(uIdxs.get(i))));
			}
			for (i = 0; i < uIdxs.size(); i++){
				assertFalse(graph2.add(_unqVs.get(uIdxs.get(i))));
			}
			
		} catch(AssertionError e){
			fail("at index: ["+ i +"]"+ e.getMessage());
		}
		// ----------------------------------------------------
		// add as string param
		// ====================
		graph = new Graph<String>();
		assertTrue(graph.add(new Vertex<String>(_unqVs.get(0).getValue())));
		assertFalse(graph.add(new Vertex<String>(_unqVs.get(0).getValue())));
		graph = new Graph<String>();
		
		for(i  = 0; i < 40; i++){
			assertTrue(graph.add(_unqVs.get(i).getValue()));
		}
		for(i = 0; i < 40; i++){
			boolean b = graph.add(_unqVs.get(i).getValue());
			assertFalse(b);
		}
		// ----------------------------------------------------
		
		// integer Vertex values
		Graph<Integer> iGraph = new Graph<Integer>();
		int vval1 = RandomGenerator.positiveNumber();
		int vval2;
		do{
			vval2 = RandomGenerator.positiveNumber();
		} while(vval2 == vval1);
		
		assertTrue(iGraph.add(vval1));
		assertFalse(iGraph.add(vval1));
		assertEquals(1, iGraph.verticesSize());
		assertTrue(iGraph.add(vval2));
		assertFalse(iGraph.add(vval2));
		assertEquals(2, iGraph.verticesSize());
		
	}
	
	@Test
	public void testRemove(){
		Graph<String> graph = new Graph<String>();
		
		Vertex<String> v1;
		// add then remove vertex not connected
		for(int i = 0; i < 30; i++){
			int idx = RandomGenerator.number(0, _unqVs.size()-1);
			v1 = _unqVs.get(idx);
			assertTrue(graph.add(v1));
			assertTrue(graph.contains(v1));
			assertTrue(graph.remove(v1));
			assertFalse(graph.contains(v1));
		}
		
		// remove connected vertices
		Graph<String> graph4 = getGraph4();
		
		// undirected connected
		Vertex<String>
				vA = _unqVs.get(0),
				vB = _unqVs.get(3);
		
		assertTrue(graph4.remove(vA));
		assertFalse(graph4.contains(vA));
		List<Vertex<String>> adjToB = graph4.getAdjacentVertices(vB);
		assertFalse(adjToB.contains(vA));
		
		//directed connected
		vA = _unqVs.get(2);
		vB = _unqVs.get(5);
		
		assertTrue(graph4.remove(vA));
		assertFalse(graph4.contains(vA));
		adjToB = graph4.getAdjacentVertices(vB);
		assertFalse(adjToB.contains(vA));
		
		// recursive connected
		vA = _unqVs.get(1);
		adjToB = graph4.getAdjacentVertices(vA);
		assertTrue(adjToB.contains(vA));
		assertTrue(graph4.remove(vA));
		adjToB = graph4.getAdjacentVertices(vA);
		assertNull(adjToB);
		
		// ----------------------------------------------------
		
		// integer Vertex values
		Graph<Integer> iGraph = new Graph<Integer>();
		int vval1 = RandomGenerator.positiveNumber();
		int vval2;
		do{
			vval2 = RandomGenerator.positiveNumber();
		} while(vval2 == vval1);
		
		iGraph.add(vval1);
		iGraph.add(vval2);
		assertEquals(2, iGraph.verticesSize());
		assertTrue(iGraph.contains(vval1));
		
		assertTrue(iGraph.remove(vval1));
		assertEquals(1, iGraph.verticesSize());
		assertTrue(iGraph.remove(new Vertex<Integer>(vval2)));
		assertEquals(0, iGraph.verticesSize());
		assertFalse(iGraph.contains(vval1));
		
	}
	
	@Test
	public void testCompareObjects(){
		Vertex<String> v1 = _unqVs.get(0);
		Vertex<String> v1cpy = _unqVs.get(0);
		Vertex<String> v2 = _unqVs.get(1);
		
		assertTrue(v1.equals(v1));
		assertTrue(v1.equals(v1cpy));
		
		assertFalse(v1.equals(v2));
		assertFalse(v1.equals(v2.getValue()));
		
		Edge<String> e1 = new Edge<String>(v1, 1);
		Edge<String> e1cpy = new Edge<String>(v1, 1);
		Edge<String> e2 = new Edge<String>(v2, 1);
		
		assertTrue(e1.equals(e1));
		assertTrue(e1.equals(e1cpy));
		assertFalse(e1.equals(e2));
		
		// compare Edge with Vertex
		assertTrue(e1.equals(v1));
		assertTrue(e1.equals(v1cpy));
		assertFalse(e1.equals(v2));
		assertFalse(e2.equals(v1));
		assertFalse(e2.equals(v1cpy));
		assertTrue(e2.equals(v2));
		
	}

	@Test
	public void testDirectedConnect(){
		Graph<String> graph = new Graph<String>();

		int testCost1 = RandomGenerator.number(0,1_000_000);
		
		List<Integer> idxs = RandomGenerator.uniqueNumbers(6, 0,_unqVs.size());
		
		Vertex<String> v1 = _unqVs.get(idxs.get(0));
		Vertex<String> v2 = _unqVs.get(idxs.get(1));
		
		graph.add(v1);
		graph.add(v2);
		assertTrue(graph.connectDirected(v1, v2, testCost1));
		/*
		 * connected v1 -> v2
		 */
		assertTrue(graph.isDirectedConnected(v1, v2));
		assertFalse(graph.isDirectedConnected(v2, v1));
		assertFalse(graph.isDirectedConnected(v1, v1));
		assertFalse(graph.isDirectedConnected(v2, v2));
		assertFalse(graph.isUndirectedConnected(v1, v2));
		assertFalse(graph.isUndirectedConnected(v2, v1));
		assertFalse(graph.isUndirectedConnected(v1, v1));
		assertFalse(graph.isUndirectedConnected(v2, v2));
		
		assertEquals(testCost1, graph.getCost(v1, v2));
		assertEquals(-1, graph.getCost(v2, v1));
		/*
		 * connected v1 <-> v2
		 */
		assertTrue(graph.connectDirected(v2, v1, testCost1, false));
		assertTrue(graph.connectionExists(v1, v2));
		assertTrue(graph.connectionExists(v2, v1));
		assertTrue(graph.isUndirectedConnected(v2, v1));
		assertTrue(graph.isUndirectedConnected(v1, v2));
		
		/*
		 * connected v3 -> v4
		 */
		Vertex<String> v3 = _unqVs.get(idxs.get(2));
		Vertex<String> v4 = _unqVs.get(idxs.get(3));
		graph.add(v3);
		graph.add(v4);
		assertTrue(graph.connectDirected(v3, v4, testCost1));
		assertFalse(graph.connectDirected(v4, v3, testCost1));
		assertTrue(graph.connectionExists(v3, v4));
		assertTrue(graph.isDirectedConnected(v3, v4));
		assertFalse(graph.isDirectedConnected(v4, v3));
		assertFalse(graph.isUndirectedConnected(v3, v4));
		assertFalse(graph.isUndirectedConnected(v4, v3));
		/*
		 * connected v5 -> v6
		 */
		Vertex<String> v5 = _unqVs.get(idxs.get(4));
		Vertex<String> v6 = _unqVs.get(idxs.get(5));
		graph.add(v5);
		graph.add(v6);
		assertTrue(graph.connectDirected(v5, v6, testCost1, true));
		assertFalse(graph.connectDirected(v6, v5, testCost1, true));
		assertTrue(graph.connectionExists(v5, v6));
		assertTrue(graph.isDirectedConnected(v5, v6));
		assertFalse(graph.isDirectedConnected(v6, v5));
		assertFalse(graph.isUndirectedConnected(v5, v6));
		assertFalse(graph.isUndirectedConnected(v6, v5));
			
		// ----------------------------------------------------------
		
		// test edit edge cost value
		graph = new Graph<String>();
		
		Vertex<String> vA = _unqVs.get(0);
		Vertex<String> vB = _unqVs.get(1);
		graph.add(vA);
		graph.add(vB);
		testCost1 = RandomGenerator.positiveNumber();
		assertTrue(graph.connectDirected(vA, vB, testCost1));
		assertEquals(graph.getCost(vA, vB), testCost1);
		assertNotEquals(graph.getCost(vB, vA), testCost1);
		
		int testCost2 = RandomGenerator.positiveNumber();
		assertTrue(graph.connectDirected(vA, vB, testCost2));
		assertEquals(testCost2, graph.getCost(vA, vB));
		assertNotEquals(testCost2, graph.getCost(vB, vA));
		assertNotEquals(testCost1, graph.getCost(vA, vB));
		assertNotEquals(testCost1, graph.getCost(vB, vA));
		
	}
	
	@Test
	public void testUndirectedConnect(){
		Graph<String> graph = new Graph<String>();
		Vertex<String>
				v0 = _unqVs.get(0),
				v1 = _unqVs.get(1);
		
		assertTrue(graph.add(v0));
		assertTrue(graph.add(v1));
		assertFalse(graph.isUndirectedConnected(v0, v0));
		assertFalse(graph.isUndirectedConnected(v0, v1));
		assertTrue(graph.connectUndirected(v0, v0, 1));
		assertTrue(graph.connectUndirected(v0, v1, 1));
		assertTrue(graph.isUndirectedConnected(v0, v0));
		assertTrue(graph.isUndirectedConnected(v0, v1));
		
		// connect vertices not added
		Vertex<String> v2 = _unqVs.get(2);
		Vertex<String> v3 = _unqVs.get(3);
		assertFalse(graph.connectUndirected(v2, v3, 1));
		assertFalse(graph.isUndirectedConnected(v2, v3));
		assertFalse(graph.isUndirectedConnected(v3, v2));
		assertFalse(graph.connectUndirected(v3, v2, 1));
		assertFalse(graph.isUndirectedConnected(v3, v2));
		assertFalse(graph.isUndirectedConnected(v2, v3));
		// connect vertices not added with vertices added
		assertFalse(graph.connectUndirected(v1, v3, 1));
		assertFalse(graph.isUndirectedConnected(v1, v3));
		assertFalse(graph.isUndirectedConnected(v3, v1));
		assertFalse(graph.connectUndirected(v2, v0, 1));
		assertFalse(graph.isUndirectedConnected(v1, v3));
		assertFalse(graph.isUndirectedConnected(v3, v1));
		
		// ----------------------------------------
		
		Graph<String> graph4 = getGraph4();
		// test graph4
		assertTrue(graph4.isUndirectedConnected(_unqVs.get(0), _unqVs.get(3)));
		assertTrue(graph4.isUndirectedConnected(_unqVs.get(3), _unqVs.get(0)));
		assertFalse(graph4.isUndirectedConnected(_unqVs.get(4), _unqVs.get(3)));
		assertFalse(graph4.isUndirectedConnected(_unqVs.get(3), _unqVs.get(4)));
		assertTrue(graph4.isUndirectedConnected(_unqVs.get(1), _unqVs.get(1)));
		assertFalse(graph4.isUndirectedConnected(_unqVs.get(0), _unqVs.get(1)));
		assertFalse(graph4.isUndirectedConnected(_unqVs.get(1), _unqVs.get(0)));
		
		// test modify graph4
		// ====================
		// add undir A <-> B  when pre connection: not connected
		Vertex<String>
				vA = _unqVs.get(0),
				vB = _unqVs.get(1);
		assertTrue(graph4.connectUndirected(vA, vB, 1));
		assertTrue(graph4.isUndirectedConnected(vA, vB));
		assertTrue(graph4.isUndirectedConnected(vB, vA));
		
		// add undir A <-> B  when pre connection: A -> B
		vA = _unqVs.get(5);
		vB = _unqVs.get(2);
		assertTrue(graph4.connectUndirected(vA, vB, 1));
		assertTrue(graph4.isUndirectedConnected(vA, vB));
		assertTrue(graph4.isUndirectedConnected(vB, vA));
		assertEquals(1, graph4.getCost(vA,  vB));
		
		// add undir A <-> B  when pre connection: A <- B
		vA = _unqVs.get(3);
		vB = _unqVs.get(4);
		assertTrue(graph4.connectUndirected(vA, vB, 1));
		assertTrue(graph4.isUndirectedConnected(vA, vB));
		assertTrue(graph4.isUndirectedConnected(vB, vA));
		
		// edit edge cost values
		Graph<String> graph2 = new Graph<String>();
		
		vA = _unqVs.get(0);
		vB = _unqVs.get(1);
		graph2.add(vA);
		graph2.add(vB);
		// set edge
		int testCost1 = RandomGenerator.number(0, Integer.MAX_VALUE);
		graph2.connectUndirected(vA, vB, testCost1);
		assertEquals(graph2.getCost(vA, vB), testCost1);
		assertEquals(graph2.getCost(vB, vA), testCost1);
		// edit
		int testCost2 = RandomGenerator.positiveNumber();
		assertTrue(graph2.connectUndirected(vA, vB, testCost2));
		assertNotEquals(testCost1, graph2.getCost(vA, vB));
		assertNotEquals(testCost1, graph2.getCost(vB, vA));
		assertEquals(testCost2, graph2.getCost(vA, vB));
		assertEquals(testCost2, graph2.getCost(vB, vA));
		
	}
	
	@Test
	public void testHasConnection(){
		Graph<String> graph = new Graph<String>();
		Vertex<String> v1 = _unqVs.get(0);
		Vertex<String> v2 = _unqVs.get(1);
		int testCost = RandomGenerator.positiveNumber();
		
		graph.add(v1);
		graph.add(v2);
		// directed connection 
		assertFalse(graph.connectionExists(v1, v2));
		assertFalse(graph.connectionExists(v2, v1));
		graph.connectDirected(v1, v2, testCost);
		assertTrue(graph.connectionExists(v1, v2));
		assertTrue(graph.connectionExists(v2, v1));
		graph.connectDirected(v2, v1, testCost, false);
		assertTrue(graph.connectionExists(v1, v2));
		assertTrue(graph.connectionExists(v2, v1));
		// undirected connection on directed vertices
		graph.connectUndirected(v1, v2, testCost);
		assertTrue(graph.connectionExists(v1, v2));
		assertTrue(graph.connectionExists(v2, v1));
		
		// ------------------------------------------ 
		
		Vertex<String> v3 = _unqVs.get(2);
		Vertex<String> v4 = _unqVs.get(3);
		graph.add(v3);
		graph.add(v4);
		
		// undirected connection
		assertFalse(graph.connectionExists(v3, v4));
		assertFalse(graph.connectionExists(v4, v3));
		graph.connectUndirected(v3, v4, testCost);
		assertTrue(graph.connectionExists(v3, v4));
		assertTrue(graph.connectionExists(v4, v3));
		// directed connections on undirected connection
		graph.connectDirected(v3, v4, testCost, false);
		assertTrue(graph.connectionExists(v3, v4));
		assertTrue(graph.connectionExists(v4, v3));
		graph.connectDirected(v4, v3, testCost, false);
		assertTrue(graph.connectionExists(v4, v3));
		assertTrue(graph.connectionExists(v3, v4));
		
		// -----------------------------------------------
		String v0str = _unqVs.get(0).getValue();
		String v1str = _unqVs.get(1).getValue();
		graph = new Graph<String>();
		graph.add(v0str);
		graph.add(v1str);
		
		// undirected connection
		assertFalse(graph.connectionExists(v1str, v0str));
		assertFalse(graph.connectionExists(v0str, v1str));
		graph.connectUndirected(v1str, v0str, testCost);
		assertTrue(graph.connectionExists(v1str, v0str));
		assertTrue(graph.connectionExists(v0str, v1str));
		// directed connections on undirected connection
		graph.connectDirected(v1str, v0str, testCost, false);
		assertTrue(graph.connectionExists(v1str, v0str));
		assertTrue(graph.connectionExists(v0str, v1str));
		graph.connectDirected(v0str, v1str, testCost, false);
		assertTrue(graph.connectionExists(v0str, v1str));
		assertTrue(graph.connectionExists(v1str, v0str));
		
	}
	
	@Test
	public void testDirectedDisconnect(){
		Graph<String> graph = getGraph4();
		
		Vertex<String> v1 = _unqVs.get(5);
		Vertex<String> v2 = _unqVs.get(2);
		assertTrue(graph.isDirectedConnected(v1, v2));
		assertTrue(graph.disconnectDirected(v1, v2));
		assertFalse(graph.isDirectedConnected(v1, v2));
		assertEquals(-1, graph.getCost(v1, v2));
		
		v1 = _unqVs.get(0);	
		v2 = _unqVs.get(3);
		assertFalse(graph.isDirectedConnected(v1, v2));
		assertFalse(graph.disconnectDirected(v1, v2));
		assertFalse(graph.isDirectedConnected(v1, v2));
		assertNotEquals(-1, graph.getCost(v1, v2));
		
		v1 = _unqVs.get(2);
		v2 = _unqVs.get(4);
		assertFalse(graph.isDirectedConnected(v1, v2));
		assertFalse(graph.disconnectDirected(v1, v2));
		assertFalse(graph.isDirectedConnected(v1, v2));
		assertEquals(-1, graph.getCost(v1, v2));
	}
	
	@Test
	public void testUndirecteddisconnect(){
		Graph<String> graph = getGraph4();
		
		int v1 = 0, v2 = 3;
		assertTrue(graph.isUndirectedConnected(_unqVs.get(v1), _unqVs.get(v2)));
		assertTrue(graph.disconnectUndirected(_unqVs.get(v1), _unqVs.get(v2)));
		assertFalse(graph.isUndirectedConnected(_unqVs.get(v1), _unqVs.get(v2)));
		assertEquals(-1, graph.getCost(_unqVs.get(v1), _unqVs.get(v2)));
		
		v1 = 5; v2 = 2;
		assertFalse(graph.isUndirectedConnected(_unqVs.get(v1), _unqVs.get(v2)));
		assertFalse(graph.disconnectUndirected(_unqVs.get(v1), _unqVs.get(v2)));
		assertFalse(graph.isUndirectedConnected(_unqVs.get(v1), _unqVs.get(v2)));
		assertNotEquals(-1, graph.getCost(_unqVs.get(v1), _unqVs.get(v2)));
		
		v1=0;	v2=2;
		assertFalse(graph.isUndirectedConnected(_unqVs.get(v1), _unqVs.get(v2)));
		assertFalse(graph.disconnectUndirected(_unqVs.get(v1), _unqVs.get(v2)));
		assertFalse(graph.isUndirectedConnected(_unqVs.get(v1), _unqVs.get(v2)));
		assertEquals(-1, graph.getCost(_unqVs.get(v1), _unqVs.get(v2)));
	}
	
	@Test
	public void testMinimumSpanningTree(){
		int nVertices = _graph1VerticesSize;
		
		Graph<String> graph = getGraph2();
		Graph<String> mst = graph.minimumSpanningTree();
		
		int exptEdges = 9;
		int exptCost = 37;
		int actualEdges = 0;
		int actualCost = 0;
		
		for (int v1 = 0; v1 < nVertices; v1++) {
			for (int v2 = v1; v2 < nVertices; v2++) {
				int cost = mst.getCost(_unqVs.get(v1), _unqVs.get(v2));
				if (cost > -1) {
					actualEdges++;
					actualCost += cost;
				}
			}
		}

		assertEquals(exptEdges, actualEdges);
		assertEquals(exptCost, actualCost);
		
		// -------------------------------------- graph3
		
		graph = getGraph3();
		mst = graph.minimumSpanningTree();
		
		exptEdges = 6;
		exptCost = 16;
		actualEdges = 0;
		actualCost = 0;
		
		for (int v1 = 0; v1 < nVertices; v1++) {
			for (int v2 = v1; v2 < nVertices; v2++) {
				int cost = mst.getCost(_unqVs.get(v1), _unqVs.get(v2));
				if (cost > -1) {
					actualEdges++;
					actualCost += cost;
				}
			}
		}

		assertEquals(exptEdges, actualEdges);
		assertEquals(exptCost, actualCost);
	}
	
	/**
	 * 
	 * @param graph original graph
	 * @param path the path to test
	 * @param idxs expected indexes
	 * @return total cost of path
	 */
	private int testPath(Graph<String> graph, List<Vertex<String>> path, int idxs[]){
		int totPathCost = 0;
		assertEquals(idxs.length, path.size());
		for(int i = 0 ; i < idxs.length; i++){
			if (i+1 < path.size()){ // counting total cost of path
				totPathCost += graph.getCost(path.get(i), path.get(i+1));
			}
			try{
				assertEquals(_unqVs.get(idxs[i]), path.get(i));
			} catch(AssertionError e){
				fail("error on idx:"+i+" : expected vertex "+idxs[i]+ "   e.message:"+e.getMessage());
			}
		}
		return totPathCost;
	}
	
	@Test
	public void testLowestCostPath1(){
		Graph<String> graph1 = getGraph1();
		List<Vertex<String>> lcpath;
		int pathCost;
		int expectedPathCost;
		
		lcpath = graph1.lowestCostPath(_unqVs.get(4), _unqVs.get(5));
		pathCost = testPath(graph1, lcpath, new int[]{4, 8, 5});
		expectedPathCost = 3;
		assertEquals(expectedPathCost, pathCost);
		pathCost = graph1.getPathCost(lcpath);
		assertEquals(expectedPathCost, pathCost);
		// -----------------------------------------	
		lcpath = graph1.lowestCostPath(_unqVs.get(0), _unqVs.get(5));
		pathCost = testPath(graph1, lcpath, new int[]{ 0, 1, 2, 9, 8, 5 });
		expectedPathCost = 35;
		assertEquals(expectedPathCost, pathCost);
		pathCost = graph1.getPathCost(lcpath);
		assertEquals(expectedPathCost, pathCost);
		// -----------------------------------------	
		lcpath = graph1.lowestCostPath(_unqVs.get(0), _unqVs.get(6));
		assertNull(lcpath);
		pathCost = graph1.getPathCost(lcpath);
		assertEquals(-1, pathCost);
		// -----------------------------------------	
		lcpath = graph1.lowestCostPath(_unqVs.get(5), _unqVs.get(6));
		assertNull(lcpath);
		pathCost = graph1.getPathCost(lcpath);
		assertEquals(-1, pathCost);
		// -----------------------------------------	
		lcpath = graph1.lowestCostPath(_unqVs.get(7), _unqVs.get(5));
		pathCost = testPath(graph1, lcpath, new int[]{ 7, 8, 5 });
		expectedPathCost = 6;
		assertEquals(expectedPathCost, pathCost);
		pathCost = graph1.getPathCost(lcpath);
		assertEquals(expectedPathCost, pathCost);
		
	}
	@Test
	public void testLowestCostPath2(){
		Graph<String> graph2 = getGraph2();
		List<Vertex<String>> lcpath;
		int pathCost;
		int expectedPathCost;
		
		lcpath = graph2.lowestCostPath(_unqVs.get(5), _unqVs.get(9));
		pathCost = testPath(graph2, lcpath, new int[]{ 5, 6, 9 });
		expectedPathCost = 10;
		assertEquals(expectedPathCost, pathCost);
		pathCost = graph2.getPathCost(lcpath);
		assertEquals(expectedPathCost, pathCost);
		// -----------------------------------------	
		lcpath = graph2.lowestCostPath(_unqVs.get(7), _unqVs.get(9));
		pathCost = testPath(graph2, lcpath, new int[]{ 7, 0, 4, 5, 6, 9 });
		expectedPathCost = 20;
		assertEquals(expectedPathCost, pathCost);
		pathCost = graph2.getPathCost(lcpath);
		assertEquals(expectedPathCost, pathCost);
		// -----------------------------------------	
		lcpath = graph2.lowestCostPath(_unqVs.get(2), _unqVs.get(4));
		pathCost = testPath(graph2, lcpath, new int[]{ 2, 5, 4 });
		expectedPathCost = 8;
		assertEquals(expectedPathCost, pathCost);
		pathCost = graph2.getPathCost(lcpath);
		assertEquals(expectedPathCost, pathCost);
		// -----------------------------------------	
		lcpath = graph2.lowestCostPath(_unqVs.get(2), _unqVs.get(9));
		pathCost = testPath(graph2, lcpath, new int[]{ 2, 5, 6, 9 });
		expectedPathCost = 14;
		assertEquals(expectedPathCost, pathCost);
		pathCost = graph2.getPathCost(lcpath);
		assertEquals(expectedPathCost, pathCost);
		
	}
	@Test
	public void testLowestCostPath3(){
		Graph<String> graph3 = getGraph3();
		List<Vertex<String>> lcpath;
		int pathCost;
		int expectedPathCost;
		
		lcpath = graph3.lowestCostPath(_unqVs.get(5), _unqVs.get(4));
		pathCost = testPath(graph3, lcpath, new int[]{  5, 6, 4 });
		expectedPathCost = 7;
		assertEquals(expectedPathCost, pathCost);
		pathCost = graph3.getPathCost(lcpath);
		assertEquals(expectedPathCost, pathCost);
		// -----------------------------------------
		lcpath = graph3.lowestCostPath(_unqVs.get(5), _unqVs.get(4));
		pathCost = testPath(graph3, lcpath, new int[]{  5, 6, 4 });
		expectedPathCost = 7;
		assertEquals(expectedPathCost, pathCost);
		pathCost = graph3.getPathCost(lcpath);
		assertEquals(expectedPathCost, pathCost);
	}
	@Test
	public void testLowestCostPath4(){
		Graph<String> graph4 = getGraph4();
		List<Vertex<String>> lcpath;
		int pathCost;
		int expectedPathCost;
		
		lcpath = graph4.lowestCostPath(_unqVs.get(2), _unqVs.get(5));
		pathCost = testPath(graph4, lcpath, new int[]{  2, 0, 3, 5 });
		expectedPathCost = 18;
		assertEquals(expectedPathCost, pathCost);
		pathCost = graph4.getPathCost(lcpath);
		assertEquals(expectedPathCost, pathCost);
		
		// -----------------------------------------
		lcpath = graph4.lowestCostPath(_unqVs.get(1), _unqVs.get(2));
		pathCost = testPath(graph4, lcpath, new int[]{   1, 4, 3, 5, 2 });
		expectedPathCost = 29;
		assertEquals(expectedPathCost, pathCost);
		pathCost = graph4.getPathCost(lcpath);
		assertEquals(expectedPathCost, pathCost);
		// -----------------------------------------
		lcpath = graph4.lowestCostPath(_unqVs.get(0), _unqVs.get(1));
		assertNull(lcpath);
		pathCost = graph4.getPathCost(lcpath);
		assertEquals(-1, pathCost);

	}
	
	@Test
	public void testAmountVertices(){
		
		for(int i = 0; i < 100; i++){
			Graph<String> graph = new Graph<String>();
			
			int nVertices = RandomGenerator.number(1, 99);
			int nVertexCounter = 0;
			// add
			for (int j = 0;j < nVertices; j++){
				if (graph.add(_unqVs.get(j)))
					nVertexCounter++;
				
				assertEquals(nVertexCounter, graph.verticesSize());
			}
			// remove
			for (int j = 0; j < nVertices; j++){
				if (RandomGenerator.booleanValue()){
					if (graph.remove(_unqVs.get(RandomGenerator.number(0, 99))))
						nVertexCounter--;
				}
				assertEquals(nVertexCounter, graph.verticesSize());
			}
			// add
			int amount = RandomGenerator.number(1, 99);
			for (int j = 0;j < amount; j++){
				if (graph.add(_unqVs.get(RandomGenerator.number(0, 99))))
					nVertexCounter++;
				
				assertEquals(nVertexCounter, graph.verticesSize());
			}
		}
		for (int i = 0; i < 30; i++){
			int nV  = 0;
			Graph<String> graph = new Graph<String>();
			int top = RandomGenerator.number(10, 10_000);
			
			for (int j = 0; j < top; j++){
				if (graph.add(new Vertex<String>(RandomGenerator.string(50))))
					nV++;
				
			}
			try{
				assertEquals(nV, graph.verticesSize());
			} catch (AssertionError e){
				System.err.println(e.getMessage());
			}
		}
		
		Graph<String> graph = getGraph1();
		assertEquals(_graph1VerticesSize, graph.verticesSize());
		
		graph = getGraph2();
		assertEquals(_graph2VerticesSize, graph.verticesSize());
		
		graph = getGraph3();
		assertEquals(_graph3VerticesSize, graph.verticesSize());
		
		graph = getGraph4();
		assertEquals(_graph4VerticesSize, graph.verticesSize());
		
	}
	
	@Test
	public void testEdgesPriority(){
		Graph<String> graph = new Graph<String>();
		int nV = 99;
		for(int i = 0; i < nV; i++){
			graph.add(_unqVs.get(i));
		}
		
		Vertex<String> rootV = _unqVs.get(0); 
		for(int i = 0; i < nV; i++){
			graph.connectUndirected(rootV, _unqVs.get(RandomGenerator.number(1, nV)), RandomGenerator.positiveNumber());
		}
		
		List<Vertex<String>> adjs = graph.getAdjacentVertices(rootV);
		
		int cost;
		int lastCost = graph.getCost(rootV, adjs.get(0));
		int i = 1;
		try {
			for (Vertex<String> v : adjs){
				cost = graph.getCost(rootV, v);
				assertTrue(cost > 0);
				assertTrue(cost >= lastCost);
				lastCost = cost;
			}
		} catch (AssertionError e){
			fail("cost of "+rootV+"-"+adjs.get(i)+ " < " +rootV+"-"+ adjs.get(i-1) + " : " + e.getMessage());
		}
		
	}
	
	@Test
	public void testAmountEdges(){
		
		Graph<String> graph = getGraph1();
		assertEquals(14, graph.edgeSize());
		
		// remove vertices
		graph.remove(_unqVs.get(0));
		assertEquals(13, graph.edgeSize());
		graph.remove(_unqVs.get(6));
		assertEquals(13, graph.edgeSize());
		graph.remove(_unqVs.get(7));
		assertEquals(10, graph.edgeSize());
		graph.remove(_unqVs.get(2));
		assertEquals(5, graph.edgeSize());
		
		graph = getGraph2();
		assertEquals(16, graph.edgeSize());
		// remove vertices
		graph.remove(_unqVs.get(7));
		assertEquals(14, graph.edgeSize());
		graph.remove(_unqVs.get(8));
		assertEquals(11, graph.edgeSize());
		graph.remove(_unqVs.get(5));
		assertEquals(7, graph.edgeSize());
		graph.remove(_unqVs.get(0));
		assertEquals(5, graph.edgeSize());
		
		graph = getGraph3();
		assertEquals(12, graph.edgeSize());
		// disconnect
		graph.disconnectUndirected(_unqVs.get(0), _unqVs.get(3));
		assertEquals(11, graph.edgeSize());
		graph.disconnectUndirected(_unqVs.get(6), _unqVs.get(4));
		assertEquals(10, graph.edgeSize());
		
		graph = getGraph4();
		assertEquals(8, graph.edgeSize());
		// disconnect
		graph.disconnectDirected(_unqVs.get(4), _unqVs.get(3));
		assertEquals(7, graph.edgeSize());
		graph.disconnectUndirected(_unqVs.get(3), _unqVs.get(0));
		assertEquals(6, graph.edgeSize());
		graph.disconnectUndirected(_unqVs.get(0), _unqVs.get(0));
		assertEquals(5, graph.edgeSize());
		
	}
	
	@Test
	public void testContains() {
		Graph<String> graph = new Graph<String>();
		graph.add(_unqVs.get(2));
		graph.add(_unqVs.get(3));
		graph.add(_unqVs.get(4));
		graph.add(_unqVs.get(5));
		
		assertFalse(graph.contains(_unqVs.get(0)));
		assertFalse(graph.contains(_unqVs.get(1)));
		assertTrue(graph.contains(_unqVs.get(2)));
		assertTrue(graph.contains(_unqVs.get(3)));
		assertTrue(graph.contains(_unqVs.get(4)));
		assertTrue(graph.contains(_unqVs.get(5)));
		assertFalse(graph.contains(_unqVs.get(6)));
		assertFalse(graph.contains(_unqVs.get(7)));
		
		assertFalse(graph.contains(_unqVs.get(0).getValue()));
		assertFalse(graph.contains(_unqVs.get(1).getValue()));
		assertTrue(graph.contains(_unqVs.get(2).getValue()));
		assertTrue(graph.contains(_unqVs.get(3).getValue()));
		assertTrue(graph.contains(_unqVs.get(4).getValue()));
		assertTrue(graph.contains(_unqVs.get(5).getValue()));
		assertFalse(graph.contains(_unqVs.get(6).getValue()));
		assertFalse(graph.contains(_unqVs.get(7).getValue()));
		
	}
	
}
