package filters;
import java.io.*;

public class AlFilter implements Filter{
	/** The filter's type */
	String name;

	/** A magic number setting the type */
	String TYPE = "all";

	/** An internal boolean supporting the NOT (negation filter) option */
	boolean negation = false;

	/**
	 * The primary constructor.
	 */
	public AlFilter(){
		name = TYPE;
	}

	/**
	 * secondary constructor in case of negation.
	 * @param negation - the flag for deciding whether to use the opposite filter.
     */
	public AlFilter(boolean negation){
		name = TYPE;
		this.negation = negation;
	}

	/**
	 * A getter function for the Filter's type.
	 * @return
     */
	public String getName(){
		return name;
	}

	/**
	 * The predicate test function.
	 * @param file - the target file to be tested
	 * @return - true for all the files.
	 */
	public boolean test(File file){
		return (!negation);
	}
}