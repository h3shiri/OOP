package oop.ex6.main.ifAndWhileBlock;

import oop.ex6.main.variable.SjavacVariable;

import java.util.ArrayList;

//TODO: test and consider scoping affect from the inside on the outside.
/**
 * This class manages boolean values for if and while blocks
 */
public class BooleanValue {
    /*Class members*/
    ArrayList<SjavacVariable> scopeVars;
    String valueToCheck;
    /**
     * Constructor. Just get  the scopeVars and value to check (as a string)
     * @param scopeVars
     */
    public BooleanValue (ArrayList<SjavacVariable> scopeVars, String valueToCheck)
            throws UnlegalBooleanExpression{
        this.scopeVars = scopeVars;
        this.valueToCheck = valueToCheck;
        if(this.isBoolean()){
            return;
        }else{
            throw new UnlegalBooleanExpression();
        }
    }

    /**
     * Helper function to check if the string is a number
     * @param valueToCheck
     * @return
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
     * @return SjavacVariable if its found, null otherwise
     */
    private SjavacVariable isValueInScope(){
        for(SjavacVariable var: this.scopeVars){
            if(this.valueToCheck.equals(var.getName())){
                return var;
            }
        }
        return null;
    }

    /**
     * Check whether a this.valueToCheck is a legit boolean value
     * @return
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
