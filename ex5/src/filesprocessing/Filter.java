package filesprocessing;
import java.io.*;

/** the basic structure for any given filter */
public abstract class Filter<T>{
	
	/** the filter's name */
	private final String name;
	
	private T data;

	public Filter(String name, T data){
		this.name = name;
		this.data = data;
	}

	public String getName(){
		return name;
	}

	public T getData(){
		return data;
	}

	public abstract boolean check(File file);
}