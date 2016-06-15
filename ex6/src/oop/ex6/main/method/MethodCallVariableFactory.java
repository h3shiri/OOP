package oop.ex6.main.method;
import java.lang.reflect.Array;
import java.util.regex.*;

import oop.ex6.main.sJavacUtil.LinkComplexNode;
import oop.ex6.main.sJavacUtil.NonExistingVariableException;
import oop.ex6.main.sJavacUtil.UtilityRegex;
import oop.ex6.main.variable.SjavacVariable;
import oop.ex6.main.line.*;

import java.util.*;
/**
 * This class creates instances of method variables in a method call given a single line (param1, param2..)
 */
public class MethodCallVariableFactory {
    /**class members*/
    private LinkComplexNode currentNode;
    final static List<String> types = Arrays.asList("int", "double", "char", "String","boolean");
    /**
     * A constructor only for the sake of passing the relevant scope variables.
     * @param currentNode - the relevant scope.
     */
    public MethodCallVariableFactory(LinkComplexNode currentNode){
        this.currentNode = currentNode;
    }
    /**
     * Factory method gets the relevant line and create ArrayList of SjavacVariables
     * @param lineToParse - A raw line beginning with type ending with spaces (no semicolon).
     */
    public ArrayList<SjavacVariable> process(String lineToParse, int lineNumber)
            throws IllegalMethodCallException{
        ArrayList<SjavacVariable> res = new ArrayList<>();
        try{
            String[] parts = lineToParse.split(",");
            for(String part: parts){
                res.add(parsePart(part,lineNumber));
            }
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
        List<String> lst = Arrays.asList(part.split("\\s+"));
        /* test parameters format is legal no spaces within an argument */
        int length = lst.size();
        ArrayList<String> lst2 = new ArrayList<String>();
        for(int i = 0; i < length; i++ ){
            if(!UtilityRegex.checkLineIsEmpty(lst.get(i))){
                lst2.add(lst.get(i));
            }
        }
        if (lst2.size() > 1){
            throw new ParametersFormatException();
        }
        part = part.replaceAll(" ", "");
        if (part.matches("^\"(.*?)\"$")){
            Pattern tempPat = Pattern.compile("\"(.*?)\"");
            Matcher mat = tempPat.matcher(part);
            try{
                String temp;
                if (mat.matches()) {
                    temp = mat.group(1);
                }
                else {
                    throw new ParametersFormatException();
                }
                if (temp.length() == 1){
                    return new SjavacVariable("char", lineNumber);
                }
                else{
                    return new SjavacVariable("String", lineNumber);
                }
            }catch(Exception e){
                throw new ParametersFormatException();
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
            try {
                SjavacVariable temp = currentNode.getVariable(part);
                if (temp.getName().equals(part)) {
                    //TODO: test final internal issue, perhaps mend the final field.
                    return new SjavacVariable(temp.getType(), lineNumber);
                }
            } catch (NonExistingVariableException e){
                throw new ParametersFormatException();
            }
        }
        else{
            throw new ParametersFormatException();
        }
        // non reachable an exception would be thrown earlier.
        return null;
    }

}
