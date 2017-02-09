package main.graph;
/**
 * Represent a connection between two Vertex instances<br>
 * Is compared by value of {@link #_cost}<br>
 * Can compare by equals(Object) with Vertex: compares Vertex with this Edges Vertex 
 * 
 * @author Mikael Holmbom
 * @since 2016-03-05
 * @version 1.0
 * 
 * @see projb.util.graph.Vertex
 * @see projb.util.graph.Graph
 * @param <T> type of Vertex value
 */
public class Edge <T> implements Comparable<Edge<T>>{

	/**
	 * this vertex
	 */
	private Vertex<T> _vertex;
	/**
	 * this cost<br>
	 * by standard set to -1
	 */
	private int _cost = -1;
	/**
	 * create edge
	 * cost is set to -1
	 * @param vertex this vertex
	 */
	public Edge(Vertex<T> vertex){
		this._vertex = vertex;
	}
	/**
	 * create edge
	 * @param vertex this vertex
	 * @param cost this cost of edge
	 */
	public Edge(Vertex<T> vertex, int cost){
		this._vertex = vertex;
		this._cost = cost;
	}
	/**
	 * get this vertex
	 * @return this vertex
	 */
	public Vertex<T> getVertex(){
		return _vertex;
	}
	/**
	 * get this cost
	 * @return this cost
	 */
	public int getCost(){
		return _cost;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Vertex){
			return this.getVertex().equals(obj);
		}
		if(obj instanceof Edge){
			return this.getVertex().equals(((Edge) obj).getVertex());
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "(" + this._vertex +"):" + this._cost; 
	}
	@Override
	public int compareTo(Edge<T> e) {
		if (this.getCost() < e.getCost())
			return -1;
		if (this.getCost() > e.getCost())
			return 1;
		return 0;
	}
	
}
