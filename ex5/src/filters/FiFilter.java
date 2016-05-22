package filters;
import java.io.*;

public class FiFilter implements Filter{

    /** The filter's type */
    String name;

    /** A magic number setting the type */
    String TYPE = "file";

    /** An internal value holding the target (A file's name) string */
    String fileName;

    /** An internal boolean supporting the NOT (negation filter) option */
    boolean negation = false;

    /**
     * The primary constructor.
     * @param fileName - the target file (name).
     */
    public FiFilter(String fileName){
        this.fileName = fileName;
        name = TYPE;
    }

    /**
     * Secondry constructor for the negation option.
     * @param fileName - the target file (name).
     * @param negation - the negation flag.
     */
    public FiFilter(String fileName, boolean negation){
        this.fileName = fileName;
        name = TYPE;
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
        boolean res = name.equals(fileName);
        if (negation) {
            return (!res);
        }
        else{
            return res;
        }
    }

}
