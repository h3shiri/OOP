package filesprocessing;

import java.lang.*;
import java.io.*;
import java.util.*;

public class AbsOrder extends Order{
	/** the order's type*/
	String name;

	/** The magic string for this spesific order*/
	String TYPE = "abs";

    /**
     * constructor function.
     */
    public AbsOrder(){
		name = TYPE;
	}

    /**
     * A getter function fot the Order's name.
     * @return - the order's type/name.
     */
	public String getName(){
		return name;
	}
}
