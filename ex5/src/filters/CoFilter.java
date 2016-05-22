package filters;
import java.io.*;

public class CoFilter implements Filter{
	/** The filter's type */
    String name;

    /** A magic number setting the type */
    String TYPE = "contains";

    /** An internal value holding the target string */
    String target;

    /** An internal boolean supporting the NOT (negation filter) option */
    boolean negation = false;

    /**
     * The primary constructor.
     * @param target - the containment target string.
     */
    public CoFilter(String target){
    	name = TYPE;
    	this.target = target;
    }

    /**
     * A secondry constructor for the negation filter.
     * @param target - the containment target string.
     * @param negation - the negation flag.
     */
    public CoFilter(String target, boolean negation){
    	name = TYPE;
    	this.target = target;
    	this.negation = negation;
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
     * @return - true iff the file's name contains the filter's target string.
     */
    public boolean test(File file){
    	String name = file.getName();
    	boolean res = name.contains(target);
    	if (negation) {
    		return (!res);
    	}
    	else{
    		return res;
    	}
    }
}