package orders;

import java.lang.*;
import java.io.*;
import java.util.*;

public abstract class OrderFactory{

	/** The data set of all the various orders */
	String[] types = new String[]{"abs", "type", "size"};
	// TODO: add exceptions to the factory make up.

	/**
	 * The factory method for building the various order classes.
	 * @param order - the given order that shall be created.
	 * @return - an Order object accordingly.
     */
	public static Order build(String order){
		Order res = new AbsOrder();
		switch(order){
			case "abs" :
				res = new AbsOrder();
				break;
			case "":
				/** In case of empty order line */
				res = new AbsOrder();
				break;
			case "type" :
				res = new TypeOrder();
				break;
			case "size" :
				res = new SizeOrder();
				break;
			default:
					System.out.println("Invalid Order");	
		}
		return res;
	}
}