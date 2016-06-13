package oop.ex6.main.method;

import oop.ex6.main.line.SjavacLine;
import oop.ex6.main.method.SjavacMethod;
import oop.ex6.main.sJavacUtil.LinkComplexNode;
import oop.ex6.main.sJavacUtil.UtilityRegex;
import oop.ex6.main.variable.SjavacVariable;

import java.util.ArrayList;

/**
 * This class gets a line. It will break it into the relevant info for method calls.
 * It will be initialized during the iteration of each line in the Sjava file.
 * In in the end of the iteration of each line we will have an array of SjavacMethod,
 * And we will be able to call the isLegal method.
 */
public class MethodCall{
    ArrayList<String> variablesForMethod = new ArrayList<>();
    String name;
    ArrayList<SjavacVariable> parsedVariables;
    LinkComplexNode currentNode;
    String rawData;

    /**
     * Constructor - gets a line and extracts the relevant info (name and parameters as variable array)
     * @param rawData - the relevant line data.
     * @param currentNode - the most relevant scope.
     */
    public MethodCall(String name, String rawData, LinkComplexNode currentNode, int lineNumber)
        throws IllegalMethodCallException{

        this.name = name;
        this.rawData = rawData;
        this.currentNode = currentNode;
        this.parsedVariables = new MethodCallVariableFactory(currentNode).process(rawData, lineNumber);
    }
    /**
     * This method gets all the Methods that were defined during the Sjava file reading, and return true if
     * This method call is a legal one.
     * @param definedMethods - the previously defined methods.
     * @throws - An IllegalMethodCallException in case of a non valid call.
     */
    public void isLegal(ArrayList<SjavacMethod> definedMethods) throws IllegalMethodCallException{
        try{
            SjavacMethod method = checkName(definedMethods);
            if(method == null){ //THERE IS NO SUCH METHOD WITH THIS NAME! ERROR
                throw new IllegalMethodCallException();
            }else{
                method.checkVars(this.parsedVariables);
            }
        }catch (IllegalMethodCallException e){//THE PARAMETERS DO NOT MATCH! ERROR
            throw new IllegalMethodCallException();
        }
    }
    /**
     * get the list of methods that are defined and look for a method with the same name
     * @param definedMethods - the previously defined methods.
     * @return an Sjavac method corresponding to the internal field name in this class.
     */
    private SjavacMethod checkName(ArrayList<SjavacMethod> definedMethods) {
        for (SjavacMethod method : definedMethods) {
            if (method.getMethodName().equals(this.name)) {
                return method;
            }
        }
        return null;
    }
}