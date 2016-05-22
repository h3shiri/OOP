package filters;
import java.io.*;

public class GrFilter implements Filter{

	/** The filter's type */
	String name;

	/** A magic number setting the type */
	String TYPE = "greater_than";

	/** A conversion rate from bytes to K-bytes */
	double CONVERT = 1024;

	/** An internal parameter for the test function */
	double floor;

	/** An internal boolean supporting the NOT (negation filter) option */
	boolean negation = false;

	/**
	 * Primary constructor
	 * @param floor - the lower bound for the file's size.
	 * @param NotOption - the negation flag option.
     */
	public GrFilter(double floor, boolean NotOption){
		name = TYPE;
		this.floor = floor;
		negation = NotOption;
	}

	/**
	 * A getter function for the Filter's name.
	 * @return
     */
	public String getName(){
		return name;
	}

	/**
	 * The predicate test function.
	 * @param file - the target file to be tested
	 * @return - true iff the file's size is more then the floor value.
     */
	public boolean test(File file){
		long size =  file.length();
		double appSize = (size/CONVERT);
		boolean res;
		if(appSize > floor){
			res = true;
		}
		else{
			res = false;
		}
		if (!negation){
			return res;
		}
		else{
			return (!res);
		}
	}
}