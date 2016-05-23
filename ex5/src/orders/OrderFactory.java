package orders;

import errors.*;
import java.lang.*;

public abstract class OrderFactory{

	/** The data set of all the various orders */
	private static String[] types = new String[]{"abs", "type", "size"};

	private static String FLAGSDELIM = "#";

	/**
	 * The factory method for building the various order classes.
	 * @param rawData - the given order rawData from which the order shall be created.
	 * @return - an Order object accordingly.
     */
	public static Order build(String rawData, int lineNumber) throws TypeOneError{
		String[] data = rawData.split(FLAGSDELIM);
		String order;
		if(data.length > 0){
			order = data[0];
		}
		else{
			order = rawData;
		}
		Order res;
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
					throw new TypeOneError(lineNumber);
		}
		if (data.length > 0){
			String revFlag = "REVERSE";
			if (data[data.length-1].equals(revFlag)){
				res.setReverse(true);
			}
		}
		return res;
	}
}