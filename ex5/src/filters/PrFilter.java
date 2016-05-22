package filters;
import java.io.*;

public class PrFilter implements Filter{

    /** The filter's type */
    String name;

    /** A string number setting the type */
    String TYPE = "prefix";

    /** An internal value holding the target prefix string */
    String prefix;

    /** An internal boolean supporting the NOT (negation filter) option */
    boolean negation = false;

    /**
     * The primary constructor.
     * @param prefix - The prefix target.
     */
    public PrFilter(String prefix){
        name = TYPE;
        this.prefix = prefix;
    }

    /**
     * The secondry constructor for the negation filter.
     * @param prefix - The prefix target.
     * @param negation - the negation flag.
     */
    public PrFilter(String prefix, boolean negation){
        name = TYPE;
        this.prefix = prefix;
        this.negation = negation;
    }

    /**
     * A getter function for the Filter's name.
     * @return - The Filter's type.
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
        boolean res = name.startsWith(prefix);
        if (negation) {
            return (!res);
        }
        else{
            return res;
        }
    }

}
