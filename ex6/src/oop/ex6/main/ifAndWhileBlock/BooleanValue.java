package oop.ex6.main.ifAndWhileBlock;

import oop.ex6.main.sJavacUtil.LinkComplexNode;
import oop.ex6.main.sJavacUtil.NonExistingVariableException;
import oop.ex6.main.variable.SjavacVariable;

//TODO: test and consider scoping affect from the inside on the outside.
/**
 * This class manages boolean values for if and while blocks
 */
public class BooleanValue {
    /*Class members*/
    /* A reference to the current node in the code */
    LinkComplexNode currentNode;
    /* A string to actually check holding the raw data. */
    String valueToCheck;
    /**
     * Constructor Just get the scopeVars and value to check (as a string)
     * @param currentNode - the relevant Node traces us to the right scope.
     */
    public BooleanValue (LinkComplexNode currentNode, String valueToCheck)
            throws UnlegalBooleanExpression{
        this.currentNode = currentNode;
        this.valueToCheck = valueToCheck;
        if(this.isBoolean()){
            return;
        }else{
            throw new UnlegalBooleanExpression();
        }
    }

    /**
     * Helper function to check if the string is a number
     * @param valueToCheck - whether it is a number.
     * @return - true iff it is a number.
     */
    private static boolean isNumber (String valueToCheck){
        try {
            double v = Double.parseDouble(valueToCheck);
        }catch(NumberFormatException e){
            return false;
        }return true;
    }

    /**
     * Check whether this.valueToCheck is a name of an existing var in scope
     * @return SjavacVariable if its found, null otherwise.
     */
    private SjavacVariable isValueInScope(){
        try {
            return currentNode.getVariable(valueToCheck);
        }
        catch (NonExistingVariableException e){
            return null;
        }
    }

    /**
     * Check whether a this.valueToCheck is a legit boolean value
     * @return true iff the value to check is actually boolean.
     */
    public boolean isBoolean (){
        SjavacVariable varInScope = isValueInScope();
        if(varInScope != null){
            if(varInScope.getType().equals("boolean") || varInScope.getType().equals("int")
                    ||varInScope.getType().equals("double")){
                return true;
            }else{
                return false;
            }

        }else{
            if (isNumber(this.valueToCheck)) {
                return true;
            } else if (this.valueToCheck.equals("true") || this.valueToCheck.equals("false")) {
                return true;
            }else{
                return false;
            }
        }
    }
}
