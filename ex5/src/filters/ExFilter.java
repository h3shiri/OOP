package filters;
import java.io.*;

public class ExFilter implements Filter{

    /** The filter's type */
    String name;

    /** A string number setting the type */
    String TYPE = "executable";

    /** An internal value holding the target literal string */
    String literal;

    /** An internal boolean supporting the NOT (negation filter) option */
    boolean negation = false;

    /**
     * The primary constructor.
     * @param literal - The literal target aka YES/NO.
     * @param negation - the negation flag.
     */
    public ExFilter(String literal, boolean negation){
        name = TYPE;
        this.literal = literal;
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
     * @return - true iff the file's has the literal executable permission.
     */
    public boolean test(File file){
        boolean permission = file.canExecute();
        boolean temp;
        if (literal.equals("YES")) {
            temp = true;
        }
        else{
            temp = false;
        }
        boolean res = (temp==permission);
        if (negation) {
            return (!res);
        }
        else{
            return res;
        }
    }
}