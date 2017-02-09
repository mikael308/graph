package main.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * undirected/directed weighted graph<br>
 * graph can only contain unique vertices<br>
 * graph uses {@code Vertex<T>} to store elements. connections 
 * between {@code Vertex<T>} consists of instances of {@code Edge<T>}.
 * To create a connection between two vertices in graph: 
 * both vertices must exist in the graph before the connection is made.<br>
 * 
 * @author Mikael Holmbom
 * @since 2016-02-26
 * @version 1.0
 *
 * @param <T> datatype to store in this graphs vertices
 * @see Edge
 * @see Vertex
 */
public class Graph<T> {

	/**
	 * lock used on this graph container
	 * @see #_graph
	 */
	private Object _graphLock = new Object();
	/**
	 * list of vertices with list of their adjacent vertices ordered by increasing cost  
	 */
	ConcurrentHashMap<Vertex<T>, PriorityQueue<Edge<T>>> _graph 
		= new ConcurrentHashMap<Vertex<T>, PriorityQueue<Edge<T>>>(0);

	/**
	 * empty graph
	 */
	public Graph(){

	}

	/**
	 * determines the amount of vertices
	 * @return amount of vertices
	 */
	public int verticesSize() {
		synchronized(_graphLock){
			return _graph.size();
		}
	}
	/**
	 * determines the amount of edges
	 * @return amount of edges
	 */
	public int edgeSize() {
		int duplicateCounter = 0;
		int edgeCounter = 0;
		synchronized(_graphLock){

			Iterator<Entry<Vertex<T>, PriorityQueue<Edge<T>>>> it = _graph.entrySet().iterator();
			while (it.hasNext()){
				Vertex curVertex = it.next().getKey();

				for (Edge edge : _graph.get(curVertex)){
					edgeCounter++;
					Vertex adjV = edge.getVertex();

					if (	isUndirectedConnected(curVertex, adjV)
							&& ! adjV.equals(curVertex)){
						duplicateCounter++;
					}
				}
			}
		}

		return (edgeCounter - (duplicateCounter / 2));
	}
	/**
	 * determines if this graph is empty of Vertices
	 * @return true: this graph has no Vertices
	 */
	public boolean isEmpty(){
		return this.verticesSize() == 0;
	}
	/**
	 * overload method of {@link #add(Vertex)}<br>
	 * add vertex with value {@code vertexValue}
	 * @param vertexValue new {@link Vertex} value to add to this graph
	 * @see #add(Vertex)
	 */
	public boolean add(T vertexValue){
		return add(new Vertex<T>(vertexValue));
	}
	/**
	 * adds unique vertex to this graph
	 * @param vertex new {@link Vertex} to add to this graph
	 * @return true if {@code newVertex} was added successfully<br>
	 * false if this already contains {@code newVertex}
	 * @see Vertex
	 */
	public boolean add(Vertex<T> vertex) {
		PriorityQueue<Edge<T>> connectedVertices = new PriorityQueue<Edge<T>>();
		synchronized(_graphLock){
			return _graph.putIfAbsent(vertex, connectedVertices) == null; // true if vertex was absent in structure
		}
	}
	/**
	 * overload method of {@link #remove(Vertex)}<br>
	 * remove {@link Vertex} with value {@code vertexValue}
	 * @param vertexValue value of {@link Vertex} to remove
	 * @see #remove(Vertex)
	 */
	public boolean remove(T vertexValue){
		return remove(new Vertex<T>(vertexValue));
	}
	/**
	 * removes {@code vertex } from this graph. Also removes connections 
	 * from other vertices to {@code vertex } 
	 * @param vertex {@link Vertex} to remove
	 * @return true if {@code vertex }  was removed successfully
	 */
	public boolean remove(Vertex<T> vertex){
		boolean wasRemoved = false;
		synchronized(_graphLock){
			try{
				if(_graph.remove(vertex) != null){ wasRemoved = true;	}

				Iterator<Entry<Vertex<T>, PriorityQueue<Edge<T>>>> it = _graph.entrySet().iterator();
				while (it.hasNext()){
					// remove connections with vertex
					Edge e = new Edge<T>(vertex);
					it.next().getValue().remove(e);
				}
			} catch(NullPointerException e){ }

			return wasRemoved;
		}
	}
	/**
	 * overload method of {@link #connectUndirected(Vertex, Vertex, int)}<br>
	 * @param vertex1 first {@link Vertex} value
	 * @param vertex2 second {@link Vertex} value
	 * @param cost cost of this connection
	 * @see #connectUndirected(Vertex, Vertex, int)
	 */
	public boolean connectUndirected(T vertex1, T vertex2, int cost){
		return connectUndirected(new Vertex<T>(vertex1), new Vertex<T>(vertex2), cost);
	}
	/**
	 * connects vertices {@code vertex1} and {@code vertex2} undirected<br>
	 * if connection was made successfully:<br>
	 * <ul>
	 * <li>{@code vertex2} will be adjacent to {@code vertex1} with cost of {@code cost}</li>
	 * <li>{@code vertex1} will be adjacent to {@code vertex2} with cost of {@code cost}</li>
	 * </ul>
	 * @param vertex1 first {@link Vertex}
	 * @param vertex2 second {@link Vertex}
	 * @param cost the cost of the edge. Valid value: &gt; 0
	 * @return true if {@code vertex1 } was connected to {@code vertex2} successfully
	 */
	public boolean connectUndirected(Vertex<T> vertex1, Vertex<T> vertex2, int cost) {
		if (cost <= 0) return false;

		int oldCost12 = 0; // cost of current vertex1 -> vertex2
		int oldCost21 = 0; // cost of current vertex2 -> vertex1
		PriorityQueue<Edge<T>> adjs; // adjacent vertices

		synchronized(_graphLock){
			try{
				// if graph does not contain vertex1 OR vertex2 
				if (_graph.get(vertex1) == null || _graph.get(vertex2) == null){
					return false;
				}

				adjs = _graph.get(vertex1);
				if (adjs.contains(vertex2)){ // if connection already exists
					oldCost12 = getCost(vertex1, vertex2); // tmp store old cost
					adjs.remove(vertex2);
				}
				if(! adjs.add(new Edge<T>(vertex2, cost))){
					throw new Exception(); // rollback
				}

				if (vertex1.equals(vertex2)) return true; // recursive connect

				adjs = _graph.get(vertex2);
				if (adjs.contains(vertex1)){
					oldCost21 = getCost(vertex2, vertex1);// tmp store old cost
					adjs.remove(vertex1);
				}

				if(! adjs.add(new Edge<T>(vertex1, cost))){
					throw new Exception(); // rollback
				}
				return true;

			} catch(Exception e){
				// rollback on unsuccessful add connection

				if(oldCost12 > 0){
					_graph.get(vertex1).add(new Edge<T>(vertex2, oldCost12));
				}
				if(oldCost21 > 0){
					_graph.get(vertex2).add(new Edge<T>(vertex1, oldCost21));
				}

				return false;
			}

		} // ! synchronized
	}
	/**
	 * Overload method of {@link #connectDirected(Vertex, Vertex, int)}
	 * @param vertex1 first {@link Vertex} value
	 * @param vertex2 second {@link Vertex} value
	 * @param cost cost of this connection
	 * @see #connectDirected(Vertex, Vertex, int)
	 */
	public boolean connectDirected(T vertex1, T vertex2, int cost){
		return connectDirected(new Vertex<T>(vertex1), new Vertex<T>(vertex2), cost);
	}
	/**
	 * connects {@code vertex1} to {@code vertex2} directed<br>
	 * if connection was made successfully:<br>
	 * <ul>
	 * 	<li>{@code vertex2} will be adjacent to {@code vertex1} with cost of {@code cost}</li>
	 * 	<li>{@code vertex1} will not be adjacent to {@code vertex2}</li>
	 * </ul>
	 * connection will not be made if prior condition {@code vertex1} 
	 * is connected to {@code vertex2} 
	 * @see #connectDirected(Vertex, Vertex, int, boolean)
	 * @param vertex1 the vertex to connect
	 * @param vertex2 the vertex that {@code vertex1} will be connected to 
	 * @param cost the cost of the edge
	 * @return true if  {@code vertex1}was restrictedly direct connected 
	 * to {@code vertex2} successfully
	 */
	public boolean connectDirected(Vertex<T> vertex1, Vertex<T> vertex2, int cost){
		return connectDirected(vertex1, vertex2, cost, true);
	}
	/**
	 * Overload method of {@link #connectDirected(Vertex, Vertex, int, boolean)}
	 * @param vertex1 first {@link Vertex} value
	 * @param vertex2 second {@link Vertex} value
	 * @param cost cost of this connection
	 * @param restrictDirectConnection restriction: <br>if true: connection will 
	 * be added if postcondition connection between {@code vertex1} and {@code vertex2} 
	 * will be directed and not undirected<br>if false: connection will be added 
	 * regardless of postcondition connection between {@code vertex1} and {@code vertex2}
	 * @see #connectDirected(Vertex, Vertex, int, boolean)
	 */
	public boolean connectDirected(T vertex1, T vertex2, int cost, boolean restrictDirectConnection){
		return connectDirected(new Vertex<T>(vertex1), new Vertex<T>(vertex2), cost, restrictDirectConnection);
	}
	/**
	 * connects {@code vertex1} to {@code vertex2} directed<br>
	 * if connection was made successfully:<br>
	 * <ul>
	 * 	<li>{@code vertex2} will be adjacent to {@code vertex1}</li>
	 * </ul>
	 * @param vertex1 the vertex to connect
	 * @param vertex2 the vertex that {@code vertex1} will be connected to 
	 * @param cost the cost of the edge
	 * @param restrictDirectConnection restriction: <br>
	 * if true: connection will be added if postcondition connection 
	 * between {@code vertex1} and {@code vertex2} will be directed and 
	 * not undirected<br>if false: connection will be added regardless of 
	 * postcondition connection between {@code vertex1} and {@code vertex2}
	 * @return true if  {@code vertex1} was connected to {@code vertex2} successfully
	 */
	public boolean connectDirected(Vertex<T> vertex1, Vertex<T> vertex2, int cost, boolean restrictDirectConnection){
		Queue<Edge<T>> adjs = null;
		int oldCost = -1;
		synchronized(_graphLock){
			try{
				adjs = _graph.get(vertex1);

				// if connection post condition must be directed
				// and current connection is: v1 <- v2 OR v1 <-> v2 
				if (restrictDirectConnection 
						&&	isConnected(vertex2, vertex1) ){
					return false;
				}

				// there is current connection v1 -> v2
				// remove 
				if (adjs.contains(vertex2)){
					oldCost = getCost(vertex1, vertex2);
					adjs.remove(vertex2);
				} 

				// add new connection
				if(! adjs.add(new Edge<T>(vertex2, cost))){
					throw new Exception(); // rollback on error
				}

				if(restrictDirectConnection){
					return isDirectedConnected(vertex1, vertex2); 
				} else {
					return isConnected(vertex1, vertex2);
				}

			} catch (Exception e){
				// rollback
				if (oldCost > 0 && adjs != null){
					adjs.add(new Edge<T>(vertex2, cost));
				}

				return false;
			}
		}
	}
	/**
	 * Overload method of {@link #disconnectUndirected(Vertex, Vertex)}
	 * @param vertex1 first {@link Vertex} value
	 * @param vertex2 second {@link Vertex} value
	 * @see #disconnectUndirected(Vertex, Vertex)
	 */
	public boolean disconnectUndirected(T vertex1, T vertex2){
		return disconnectUndirected(new Vertex<T>(vertex1), new Vertex<T>(vertex2));
	}
	/**
	 * disconnects {@code vertex1} from {@code vertex2} 
	 * and {@code vertex2} from {@code vertex1} 
	 * @param vertex1 first {@link Vertex}
	 * @param vertex2 second {@link Vertex}
	 * @return true if {@code vertex1} was disconnected 
	 * from {@code vertex2} and {@code vertex2} from {@code vertex1}
	 */
	public boolean disconnectUndirected(Vertex<T> vertex1, Vertex<T> vertex2){
		synchronized(_graphLock){
			if (! isUndirectedConnected(vertex1, vertex2)) {return false;}

			_graph.get(vertex1).remove(vertex2);
			_graph.get(vertex2).remove(vertex1);

			return ! isUndirectedConnected(vertex1, vertex2);
		}
	}
	/**
	 * Overload method of {@link #disconnectDirected(Vertex, Vertex)}
	 * @param vertex1 first {@link Vertex} value
	 * @param vertex2 second {@link Vertex} value
	 * @see #disconnectDirected(Vertex, Vertex)
	 */
	public boolean disconnectDirected(T vertex1, T vertex2){
		return disconnectDirected(new Vertex<T>(vertex1), new Vertex<T>(vertex2));
	}
	/**
	 * disconnects {@code vertex1 } from {@code vertex2}
	 * @param vertex1 first {@link Vertex}
	 * @param vertex2 second {@link Vertex}
	 * @return true if {@code vertex1 }  was disconnected from {@code vertex2}
	 */
	public boolean disconnectDirected(Vertex<T> vertex1, Vertex<T> vertex2){
		synchronized(_graphLock){
			if (! isDirectedConnected(vertex1, vertex2)){return false;}
			_graph.get(vertex1).remove(new Edge<T>(vertex2));

			return ! isDirectedConnected(vertex1, vertex2);
		}
	}
	/**
	 * determines if connection exists between {@code vertex1 } and {@code vertex2}<br>
	 * following conditions will return true:
	 * override method of {@link #connectionExists(Vertex, Vertex)}
	 * @param vertex1 first {@link Vertex} value
	 * @param vertex2 second {@link Vertex} value
	 */
	public boolean connectionExists(T vertex1, T vertex2){
		return connectionExists(new Vertex<T>(vertex1), new Vertex<T>(vertex2));
	}
	/**
	 * determines if connection exists between {@code vertex1 } and {@code vertex2}<br>
	 * following conditions will return true:
	 * <ul>
	 * <li>{@code vertex1} directed connected to {@code vertex2}</li>
	 * <li>{@code vertex2} directed connected to {@code vertex1}</li>
	 * <li>{@code vertex1} undirected connected to {@code vertex2}</li>
	 * </ul>
	 * @param vertex1 first {@link Vertex}
	 * @param vertex2 second {@link Vertex}
	 * @return true if connection exists between {@code vertex1 } and {@code vertex2}
	 */
	public boolean connectionExists(Vertex<T> vertex1, Vertex<T> vertex2){
		try{
			synchronized(_graphLock){
				return (isConnected(vertex1, vertex2)
						|| isConnected(vertex2, vertex1));

			}
		} catch (NullPointerException e) {  // vertex1 OR vertex2 was not found 
			return false;			
		}	

	}
	/**
	 * Overload method of {@link #isConnected(Vertex, Vertex)}
	 * @see #isConnected(Vertex, Vertex)
	 * @param vertex1 first {@link Vertex} value
	 * @param vertex2 second {@link Vertex} value
	 */
	public boolean isConnected(T vertex1, T vertex2){
		synchronized(_graphLock){
			return _graph.get(new Vertex<T>(vertex1)).contains(new Vertex<T>(vertex2));
		}
	}
	/**
	 * determines if {@code vertex2} is with connected {@code vertex1}<br>
	 * following conditions will return true:
	 * <ul>
	 * <li>{@code vertex1} directed connected to {@code vertex2}</li>
	 * <li>{@code vertex1} undirected connected to {@code vertex2}</li>
	 * </ul>
	 * @param vertex1 first {@link projb.util.graph.Vertex}
	 * @param vertex2 second {@link projb.util.graph.Vertex}
	 * @return true if {@code vertex2} is adjacent vertex to {@code vertex1}
	 * @see #_graph
	 */
	public boolean isConnected(Vertex<T> vertex1, Vertex<T> vertex2){
		synchronized(_graphLock){
			return _graph.get(vertex1).contains(vertex2);
		}
	}
	/**
	 * determines if {@code vertex1 } is directed connected to {@code vertex2}
	 * override method of {@link #isDirectedConnected(Vertex, Vertex)}
	 * @param vertex1 first {@link Vertex} value
	 * @param vertex2 second {@link Vertex} value
	 */
	public boolean isDirectedConnected(T vertex1, T vertex2) {
		return isDirectedConnected(new Vertex<T> (vertex1), new Vertex<T> (vertex2));
	}
	/**
	 * determines if {@code vertex1 } is directed connected to {@code vertex2}
	 * @param vertex1 first {@link Vertex}
	 * @param vertex2 second {@link Vertex}
	 * @return true if {@code vertex1 } is directed connected 
	 * to {@code vertex2} and {@code vertex2}is not direct connected to {@code vertex1 } 
	 */
	public boolean isDirectedConnected(Vertex<T> vertex1, Vertex<T> vertex2) {
		synchronized(_graphLock){
			try {
				return (_graph.get(vertex1).contains(vertex2)
						&& ! _graph.get(vertex2).contains(vertex1));

			} catch (NullPointerException e) { } // vertex1 was not found	

			return false;				
		}
	}
	/**
	 * determines if {@code vertex1 } is undirected connected to  {@code vertex2}
	 * @param vertex1 first vertex
	 * @param vertex2 second vertex
	 * @return true if {@code vertex1 } is undirected connected 
	 * to {@code vertex2} and {@code vertex2}is undirect connected to {@code vertex1 } 
	 */
	public boolean isUndirectedConnected(T vertex1, T vertex2){
		return isUndirectedConnected(new Vertex<T> (vertex1), new Vertex<T> (vertex2));
	}
	/**
	 * determines if {@code vertex1 } is undirected connected to  {@code vertex2}
	 * @param vertex1 first vertex
	 * @param vertex2 second vertex
	 * @return true if {@code vertex1 } is undirected connected 
	 * to {@code vertex2} and {@code vertex2}is undirect connected to {@code vertex1 } 
	 */
	public boolean isUndirectedConnected(Vertex<T> vertex1, Vertex<T> vertex2){
		synchronized(_graphLock){
			try {
				return ( _graph.get(vertex1).contains(vertex2)
						&&	_graph.get(vertex2).contains(vertex1));

			} catch (NullPointerException e) { 
				return false;
			} // vertex1 was not found	

		}
	}
	/**
	 * gets the cost of an edge<br>
	 * @param vertex1 first {@link Vertex} 
	 * @param vertex2 second {@link Vertex}
	 * @return cost of edge {@code vertex1 } to {@code vertex2}<br>
	 * if {@code vertex1 } is not connected to {@code vertex2} -1 is returned
	 */
	public int getCost(T vertex1, T vertex2) {
		return getCost(new Vertex<T>( vertex1), new Vertex<T> (vertex2)) ;
	}
	/**
	 * gets the cost of an edge<br>
	 * @param vertex1 first {@link Vertex} 
	 * @param vertex2 second {@link Vertex}
	 * @return cost of edge {@code vertex1 } to {@code vertex2}<br>
	 * if {@code vertex1 } is not connected to {@code vertex2} -1 is returned
	 */
	public int getCost(Vertex<T> vertex1, Vertex<T> vertex2) {
		try {
			synchronized(_graphLock){
				_graph.get(vertex1).iterator();
				for(Edge<T> e : _graph.get(vertex1)){
					if (e.getVertex().equals(vertex2)){
						return e.getCost();
					}
				}
			}
		} catch (NullPointerException e){ // if vertex1 was not found
		}

		return -1;
	}
	/**
	 * gets the total cost of {@code vertexPath}<br>
	 * 
	 * @param vertexPath path witch to determine total cost of
	 * @return total cost of path<br>
	 * return 0  : path.size() == 1<br>
	 * return -1 : path is null, path.size() &lt; 1, not correct path<br>
	 */
	public int getPathCost(List<Vertex<T>> vertexPath){
		if(vertexPath == null) 		return -1;
		if(vertexPath.size() == 1) 	return 0;
		if(vertexPath.size() < 1) 	return -1;
		
		int totCost = 0;
		synchronized(_graphLock){
			for(int i = 0; i < vertexPath.size()-1; i++){
				int cost = getCost(vertexPath.get(i), vertexPath.get(i+1));
				if (cost <= 0) return -1;
				
				totCost += cost;
			}
		}
		
		return totCost;
	}
	/**
	 * Overload method of {@link #contains(Vertex)}
	 * @param vertex {@link Vertex} value
	 * @see #contains(Vertex)
	 */
	public boolean contains(T vertex){
		return contains(new Vertex<T>(vertex));
	}
	/**
	 * determines if this graph contains param {@code vertex}
	 * @param vertex {@link Vertex} to determine existence
	 * @return true if this graph contains param {@code vertex}
	 */
	public boolean contains(Vertex<T> vertex){
		synchronized(_graphLock){
			return _graph.containsKey(vertex);

		}
	}
	/**
	 * get all current vertices contained in this graph<br>
	 * return result list is unordered
	 * @return all vertices of this graph
	 */
	public List<Vertex<T>> getAllVertices(){
		List<Vertex<T>> vertices = new ArrayList<Vertex<T>>();
		synchronized(_graphLock){
			Iterator<Entry<Vertex<T>, PriorityQueue<Edge<T>>>> it = _graph.entrySet().iterator();
			while(it.hasNext()){
				vertices.add(it.next().getKey());
			}
		}
		return vertices;
	}
	/**
	 * Overload method of {@link #getAdjacentVertices(Vertex)}<br>
	 * @param vertex {@link Vertex} value to get adjacent adjacent vertices of
	 * @see #getAdjacentVertices(Vertex)
	 */
	public List<Vertex<T>> getAdjacentVertices(T vertex) {
		return getAdjacentVertices(new Vertex<T>(vertex));
	}
	/**
	 * get adjacent vertices to vertex param {@code vertex}
	 * @param vertex vertex to get adjacent vertices to
	 * @return queue of adjacent vertices to vertex param {@code vertex}<br>
	 * if {@code vertex} is not connected in this graph a empty list is returned<br>
	 * null if graph does not contain {@code vertex} return null<br>
	 */
	public List<Vertex<T>> getAdjacentVertices(Vertex<T> vertex) {
		List<Vertex<T>> adjacents;
		synchronized(_graphLock){
			try {
				PriorityQueue<Edge<T>> q = new PriorityQueue<Edge<T>>(_graph.get(vertex));
				adjacents = new ArrayList<Vertex<T>>(q.size());
				
				while (! q.isEmpty()){
					adjacents.add(q.poll().getVertex());
				}

			} catch (NullPointerException e){
				return null;
			}

		}
		return adjacents;
	}
	/**
	 * get a path from a depth first search on this graph
	 * @param start startvertex
	 * @param end endvertex, to find
	 * @return list of vertices, representing path from start to end
	 */
	public List<Vertex<T>> depthFirstSearch(Vertex<T> start, Vertex<T> end) {
		boolean foundEndvertex = start.equals(end)? true:false;
		Map<Vertex<T>, Vertex<T>> depthPath = new HashMap<Vertex<T>, Vertex<T>>();

		synchronized(_graphLock){
			if (! this.contains(start)) 	return new ArrayList<Vertex<T>>(0);

			List<Vertex<T>> checked = new ArrayList<Vertex<T>>(0);
			Stack<Vertex<T>> stack = new Stack<Vertex<T>>();

			Vertex<T> curvertex = start;
			checked.add(curvertex);
			depthPath.put(curvertex, null);
			stack.add(curvertex);

			while(! stack.isEmpty()){
				curvertex = stack.pop();// validate vertex from stack

				// validate its adjacent vertices
				for(Edge<T> edge : _graph.get(curvertex)){
					Vertex<T> adj = edge.getVertex();

					// adjacent is unchecked
					if (! checked.contains(adj)){
						checked.add(adj);
						// add to path, pointing to its previous vertex: curVertex
						depthPath.put(adj, curvertex);
						stack.add(adj);

						if (adj.equals(end)){ // end found
							foundEndvertex = true;
						}
					}
				}
			}
		}


		List<Vertex<T>> shortestPath = new ArrayList<Vertex<T>>(0);
		if(foundEndvertex){
			// search backward from end to its adjacent back to start
			// to avoid leaf vertices and only get correct path
			Vertex<T> next = end;
			while(next != null){ // vertex:start will have adjacent: null
				Vertex<T> adj = depthPath.get(next);
				shortestPath.add(next);
				next = adj;
			}
			//since backwardsearch, reverse path
			Collections.reverse(shortestPath);
		}

		return shortestPath;
	}
	/**
	 * get a path from a breadth first search on this graph
	 * @param start startvertex
	 * @param end endvertex, to find
	 * @return list of vertices, representing path from start to end
	 */
	public List<Vertex<T>> breadthFirstSearch(Vertex<T> start, Vertex<T> end) {
		boolean foundEndVertex = start.equals(end)? true:false;
		Map<Vertex<T>, Vertex<T>> breadthPath = new HashMap<Vertex<T>, Vertex<T>>();
		synchronized(_graphLock){
			if (! this.contains(start)) 	return new ArrayList<Vertex<T>>(0);

			List<Vertex<T>> checked = new ArrayList<Vertex<T>>(0);
			Queue<Vertex<T>> queue = new LinkedList<Vertex<T>>();

			Vertex<T> curVertex = start;
			checked.add(curVertex);
			breadthPath.put(curVertex, null);
			queue.add(curVertex);

			while(! queue.isEmpty()){
				curVertex = queue.poll(); // validate first vertex in queue

				// validate its adjacent vertices
				for( Edge<T> edge : _graph.get(curVertex)){
					Vertex<T> adj = edge.getVertex();

					// adjacent is unchecked
					if(! checked.contains(adj)){
						checked.add(adj);
						// add to path, pointing to its previous vertex: curVertex
						breadthPath.put(adj, curVertex);
						queue.add(adj);

						if (adj.equals(end)){
							foundEndVertex = true;
						}
					}
				}
			}
		}

		List<Vertex<T>> shortestPath = new ArrayList<Vertex<T>>(0);
		if(foundEndVertex){
			// search backward from end to its adjacent back to start
			Vertex<T> next = end;
			while(next != null){ // vertex:start will point to null
				Vertex<T> adj = breadthPath.get(next);
				shortestPath.add(next);
				next = adj;
			}
		}
		//since backwardsearch, reverse path
		Collections.reverse(shortestPath);

		return shortestPath;
	}
	/**
	 * get the minimum spanning tree of this graph
	 * @return minimum spanning tree of this graph
	 */
	public Graph<T> minimumSpanningTree() {
		Graph<T> mst = new Graph<T>();
		ArrayList<Vertex<T>> checked = new ArrayList<Vertex<T>>();

		synchronized(_graphLock){
			Iterator<Entry<Vertex<T>, PriorityQueue<Edge<T>>>> it 
				= _graph.entrySet().iterator();
			Vertex<T> rootVertex = it.next().getKey();
			mst.add(rootVertex);
			checked.add(rootVertex);

			// for all vertices in graph
			while (it.hasNext()) {
				it.next();
				Vertex<T> vertex1 = null;
				Vertex<T> vertex2 = null;
				int lowestCost = Integer.MAX_VALUE;

				for(Vertex<T> mstVertex : checked){ // foreach vertex in mst
					// check adjacent nodes to mstVertex
					for (Edge<T> adjVertex : _graph.get(mstVertex)){
						// find vertex not in mst with lowest cost
						if (		adjVertex.getCost() < lowestCost
								&& ! checked.contains(adjVertex.getVertex())){
							vertex1 = mstVertex;
							vertex2 = adjVertex.getVertex();
							lowestCost = adjVertex.getCost();
						}
					}
				}

				// add to tree
				if (vertex2 != null){
					mst.add(vertex1);
					mst.add(vertex2);
					mst.connectUndirected(vertex1, vertex2, lowestCost);
					checked.add(vertex2);
				}
			}
		}

		return mst;
	}
	/**
	 * 
	 * Overload method of {@link #lowestCostPath(Vertex, Vertex)}
	 * @param start first {@link Vertex} value
	 * @param end second {@link Vertex} value
	 * @return path of lowest cost from {@code start} vertex to {@code end } vertex<br>
	 * returns null if a path between {@code start} vertex 
	 * to {@code end } vertex is not found
	 * @see #lowestCostPath(Vertex, Vertex)
	 */
	public List<Vertex<T>> lowestCostPath(T start, T end){
		return lowestCostPath(new Vertex<T>(start), new Vertex<T>(end));
	}
	/**
	 * determine the path of lowest cost from {@code start } vertex to {@code end} vertex<br>
	 * uses Djikstras algorithm to determine path
	 * @param start first {@link Vertex}
	 * @param end end {@link Vertex}
	 * @return path of lowest cost from {@code start} vertex to {@code end } vertex<br>
	 * returns null if a path between {@code start} vertex 
	 * to {@code end } vertex is not found
	 */
	public List<Vertex<T>> lowestCostPath(Vertex<T> start, Vertex<T> end){
		List<Vertex<T>> lcPath = new ArrayList<Vertex<T>>();

		synchronized(_graphLock){
			if (! this.contains(end)) return lcPath;

			Map<Vertex<T>, Integer> distances = new HashMap<Vertex<T>, Integer>();
			Iterator<Entry<Vertex<T>, PriorityQueue<Edge<T>>>> it = _graph.entrySet().iterator();
			while (it.hasNext()){
				distances.put(it.next().getKey(), Integer.MAX_VALUE);
			}

			List<Vertex<T>> unchecked 	= new ArrayList<Vertex<T>>();

			unchecked.add(start);
			distances.replace(start, 0);
			Map<Vertex<T>, Vertex<T>> path = new HashMap<Vertex<T>, Vertex<T>>();
			path.put(start, null);
			boolean foundEnd = false;
			while(! unchecked.isEmpty()){
				Vertex<T> v = unchecked.get(0);
				unchecked.remove(v);

				if(v.equals(end)){
					foundEnd = true;
				}
				// get lowest cost from adjacents of v
				for(Edge<T> adj : _graph.get(v)){
					if (adj.getVertex().equals(v)) continue; // skip if recursive connection
					int newDist = distances.get(v) + adj.getCost();
					if (newDist < distances.get(adj.getVertex())){
						distances.replace(adj.getVertex(), newDist);
						unchecked.add(adj.getVertex());
						path.put(adj.getVertex(), v);
					}
				}
			}
			if (! foundEnd) return null;
			
			Vertex<T> next = end;
			while(next != null){ // next == null if next is start
				lcPath.add(next);
				next = path.get(next);
			}

			Collections.reverse(lcPath);
			return lcPath;
		}

	}
	@Override
	public String toString(){
		return _graph.toString();
	}

}
