package oop.ex6.main.method;
import java.lang.reflect.Array;
import java.util.regex.*;

import oop.ex6.main.variable.SjavacVariable;
import oop.ex6.main.line.*;

import java.util.*;
/**
 * This class creates instances of method variables in a method call given a single line (param1, param2..)
 */
public class MethodCallVariableFactory {
    private ArrayList<SjavacVariable> scopeVars;

    /**
     * A constructor only for the sake of passing the relevant scope variables.
     * @param scopeVars - the relevent scope variables.
     */
    public MethodCallVariableFactory(ArrayList<SjavacVariable> scopeVars){
//        TODO: test it's kosher
        this.scopeVars = scopeVars;
    }

    final static List<String> types = Arrays.asList("int", "double", "char", "String","boolean");

    /**
     * Factory method gets the relevant line and create MethodVariables array
     * @param lineToParse - A raw line beginning with type ending with spaces (no semicolon).
     */
    public ArrayList<SjavacVariable> process(String lineToParse, int lineNumber)
            throws IllegalMethodCallException{
        ArrayList<SjavacVariable> res = new ArrayList<>();
        try{
            String part;
            while(lineToParse.length() > 0){
                //TODO: test for null aka method call with no params.
                if(!lineToParse.contains(",")){
                    part = lineToParse;
                    res.add(parsePart(part, lineNumber));
                    lineToParse = "";
                }else{
                    part = lineToParse.substring(lineToParse.lastIndexOf(",") + 1);
                    lineToParse = lineToParse.substring(0,lineToParse.lastIndexOf(","));
                    res.add(parsePart(part, lineNumber));
                }
            }
            Collections.reverse(res);
            return res;
        }catch(ParametersFormatException e){
            throw new IllegalMethodCallException();
        }
    }

    /**
     * This method gets a part (a parameter for the method call)
     * @param part - the slice of parameters call between the commas.
     * @return - An Sjavac appropriate, aka name, type and value.
     * In case of a constant we shall return in the name field (CONST) with appropriate type..etc
     */
    public SjavacVariable parsePart(String part, int lineNumber) throws ParametersFormatException {
        part.trim(); // removes trailing/preceding spaces.
        SjavacVariable res;
        /* In case of a literal string/char */
        List<String> lst = Arrays.asList(part.split("\\s"));
        /* test parameters format is legal no spaces within an argument */
        if (lst.size() > 1){
            throw new ParametersFormatException();
        }
        if (part.matches("^\"(.*?)\"$")){
            Pattern tempPat = Pattern.compile("\"(.*?)\"");
            Matcher mat = tempPat.matcher(part);
            String temp = mat.group(1);
            if (temp.length() == 1){
                return new SjavacVariable("char", lineNumber);
            }
            else{
                return new SjavacVariable("String", lineNumber);
            }
        }
        else if (part.matches("^\\d+$")){
            return new SjavacVariable("int", lineNumber);
        }
        else if (part.matches("^\\d+[.]\\d+$")){
            return new SjavacVariable("double", lineNumber);
        }
        else if (part.matches("(true|false)")){
            return new SjavacVariable("boolean", lineNumber);
        }
        /* testing for valid variable name on the go */
        else if (part.matches("^[a-zA-Z]+[\\w]*|_+[a-zA-Z]+\\w*$")){
            for (SjavacVariable temp : scopeVars) {
                if (temp.getName().equals(part)){
                    //TODO: test final internal issue, perhaps mend the final field.
                    return new SjavacVariable(temp.getType(), lineNumber);
                }
            }
        }
        else{
            throw new ParametersFormatException();
        }
        // non reachable an exception would be thrown earlier.
        return null;
    }

}
