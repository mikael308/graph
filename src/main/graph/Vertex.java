package main.graph;
/**
 * Vertex containing a value used in a Graph<br>
 * Can compare by equals(Object) with Edge: will compare 
 * this Vertex to Edges Vertex
 * 
 * @author Mikael Holmbom
 * @since 2016-03-05
 * @version 1.0
 * 
 * @see projb.util.graph.Edge
 * @see projb.util.graph.Graph
 * @param <T> type of Vertex value
 */
public class Vertex<T> {
	
	/**
	 * value of this Vertex
	 */
	private T _value;
	/**
	 * creates Vertex with {@link #_value} set to {@code value}
	 * @param value this {@link #_value}
	 */
	public Vertex(T value){
		_value = value;
	}
	/**
	 * gets this {@link #_value}
	 * @return this {@link #_value}
	 */
	public T getValue(){
		return _value;
	}
	/**
	 * sets this {@link #_value}
	 * @param value new value
	 */
	public void setValue(T value){
		this._value = value;
	}
	
	@Override
	public int hashCode() {
		return _value.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Vertex){
			return _value.equals(((Vertex<T>) obj).getValue());
		} 
		if(obj instanceof Edge){
			return _value.equals(((Edge<T>) obj).getVertex().getValue());
		}
		
		return false;
	}
	
	@Override
	public String toString(){
		return _value.toString();
	}

}
