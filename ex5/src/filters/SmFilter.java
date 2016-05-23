package filters;
import java.io.*;

public class SmFilter implements Filter{

    /** The filter's type */
    String name;

    /** A magic number setting the type */
    String TYPE = "smaller_than";

    /** A conversion rate from bytes to K-bytes */
    double CONVERT = 1024;

    /** An internal parameter for the test function */
    double ceiling;

    /** An internal boolean supporting the NOT (negation filter) option */
    boolean negation = false;


    /**
     * The primary constructor.
     * @param ceiling - the upper bound value for the size.
     * @param negation - the negation flag option.
     */
    public  SmFilter(double ceiling, boolean negation){
        name = TYPE;
        this.ceiling = ceiling;
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
     * @return - true iff the file's size is less then the ceiling value.
     */
    public boolean test(File file){
        long size =  file.length();
        double appSize = (size/CONVERT);
        if(appSize < ceiling){
            return true;
        }
        else{
            return false;
        }
    }
}
