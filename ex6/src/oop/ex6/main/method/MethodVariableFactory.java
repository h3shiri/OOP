package oop.ex6.main.method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class gets a method raw data and create instances of method variables (variables with no values, just type)
 */
public class MethodVariableFactory {
    /*class members*/
    public final static String variableName = "([a-zA-Z]+[\\w]*|_+[a-zA-Z]+\\w*)";
    Pattern variablePattern = Pattern.compile(variableName);
    ArrayList<MethodVariable> methodVars = new ArrayList<>();
    final static List<String> types = Arrays.asList("int", "double", "char", "String","boolean");    /**
     * constructor - get the parameters and create methodVars class member by process and parsing.
     * @param parameters - the raw data
     */
    public MethodVariableFactory(String parameters) throws IllegalMethodDeclerationException {
        try {
            String[] parsed = parameters.split(",");
            for (String x : parsed) {
                x.trim();
                methodVars.add(this.stringToMethodVar(x));
            }
        }catch(IllegalMethodDeclerationException e){
            throw new IllegalMethodDeclerationException();
        }

    }

    /**
     * Get a string x, create a method variable out of it
     * @param x - raw line for parameter creation.
     * @return
     */
    private MethodVariable stringToMethodVar(String x) throws IllegalMethodDeclerationException {
        String[] wordsInX = x.split("\\s+");
        String type;
        /**we expect either this format: type name or final type name.
         * So if wordsInX is length 2, we expect type name, if its 3, we expect final type name otherwise
         * It is not a legal method declaration.
         */
        if (wordsInX.length == 2) {
            if (types.contains(wordsInX[0]) && isLegalName(wordsInX[1])) {
                return new MethodVariable(false, wordsInX[0]);
            } else { // either not legal type or not legal name
                throw new IllegalMethodDeclerationException();
            }//Finished the case of type name, starting to check final type name:
        }else if (wordsInX.length == 3) {
            if (wordsInX[0] != "final") {
                throw new IllegalMethodDeclerationException();
            } else if (types.contains(wordsInX[1])) {
                if(isLegalName(wordsInX[2])) {
                    return new MethodVariable(true, wordsInX[1]);
                }else { //not a legal variable name
                    throw new IllegalMethodDeclerationException();
                }
            }else { // type is not a legal type
                throw new IllegalMethodDeclerationException();
            }
        }else{ //length is not 2 or 3, so it is not legal
            throw new IllegalMethodDeclerationException();
        }

    }

    /**
     * This method checks if a name of the variable is legal (aka starts with a letter and ext)
     * @param name - the name of the variable.
     * @return - true iff it is a legal name.
     */
    private boolean isLegalName(String name){
        Matcher matcher = this.variablePattern.matcher(name);
        return matcher.matches();
    }
    /**
     * get the variables created by this factory. Only package is allowed, this is supposed to be called by
     * The constructor of a new SjavacMethod only.
     * @return - the constructed parameters.
     */
    ArrayList<MethodVariable> getVariables(){
        return this.methodVars;
    }
}
