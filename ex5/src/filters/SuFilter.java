package filters;
import java.io.*;

public class SuFilter implements Filter{

    /** The filter's type */
    String name;

    /** A magic string setting the type */
    String TYPE = "suffix";

    /** An internal value holding the target prefix string */
    String suffix;

    /** An internal boolean supporting the NOT (negation filter) option */
    boolean negation = false;

    /**
     * The primary constructor for the negation filter.
     * @param suffix - The suffix target.
     * @param negation - the negation flag.
     */
    public SuFilter(String suffix, boolean negation){
        name = TYPE;
        this.suffix = suffix;
        this.negation = negation;
    }

    /**
     * A getter function for the Filter's name.
     * @return - the Filter's type.
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
        boolean res = name.endsWith(suffix);
        if (negation) {
            return (!res);
        }
        else{
            return res;
        }
    }
}