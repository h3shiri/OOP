package filesprocessing;
import java.io.*;

public class BeFilter implements Filter{

    /** The filter's type */
    String name;

    /** A magic number setting the type */
    String TYPE = "between";

    /** A conversion rate from bytes to K-bytes */
    double CONVERT = 1024;

    /** The size lower bound*/
    double lowerBound;

    /** The size upper bound */
    double upperBound;

    /** An internal boolean supporting the NOT (negation filter) option */
    boolean negation = false;


    /**
     * The basic constructor.
     * @param lowerBound - size of the lower bound for the filter.
     * @param upperBound - size of the upper bound for the filter.
     */
    public BeFilter(double lowerBound, double upperBound)
    {
        name = TYPE;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * Secondary constructor for the NOT flag.
     * @param lowerBound - size of the lower bound for the filter.
     * @param upperBound - size of the upper bound for the filter.
     * @param notOption - flag for the negation option.
     */
    public BeFilter(double lowerBound, double upperBound, boolean notOption){
        name = TYPE;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        negation = notOption;
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
     * @return - true iff the file's size is between the lower and upper bounds.
     */
    public boolean test(File file){
        long size =  file.length();
        double appSize = (size/CONVERT);
        boolean res;
        if ((appSize < upperBound) && (appSize > lowerBound)){
            res = true;
        }
        else{
            res = false;
        }
        if (!negation){
            return res;
        }
        /** In case the negation flag is on */
        else{
            return (!res);
        }
    }
}