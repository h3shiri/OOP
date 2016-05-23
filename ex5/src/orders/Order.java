package orders;

import java.lang.*;
import java.io.*;
import java.util.*;

public abstract class Order implements Comparator<File>{
	/** The reverse flag option */
	public boolean reverse = false;

	/**
	 * A getter function for the type of the Order
	 * @return - depends on the inheriting class type.
     */
	public abstract String getName();

	/**
	 * An implementation of compare required by the comparator interface.
	 * @param file1 - A given file to be compared.
	 * @param file2 - A second file to be compared.
     * @return - A positive number in case file1 has a greater value, a negative value in case
	 * file2 has a greater value. Zero in case of equal values.
     */
	public int compare(File file1, File file2){
		String name1 = file1.getAbsolutePath();
		String name2 = file2.getAbsolutePath();
		return name1.compareTo(name2);
	}

	/**
	 * A setter function for the reverse flag.
	 * @param condition - the boolean for setting the flag.
     */
	public void setReverse(boolean condition){
		reverse = condition;
	}

	/**
	 * A getter function for checking the reverse flag condition.
	 * @return
     */
	public boolean checkReverseFlag(){
		return reverse;
	}
} 