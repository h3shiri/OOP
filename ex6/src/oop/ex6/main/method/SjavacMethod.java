package oop.ex6.main.method;

import oop.ex6.main.sJavacUtil.UtilityRegex;
import oop.ex6.main.variable.SjavacVariable;

import java.util.ArrayList;

/**
 * This class represent a Sjavac method, and will carry all its relevant info
 */
public class SjavacMethod {
    /* class members*/
    /* various parameters */
    ArrayList<MethodVariable> parameters;
    /* The function name */
    final String methodName;
    /**
     * Constructor for a new method class
     * @param methodName - the method's name.
     * @param parameters - the various parameters.
     */
    public SjavacMethod(String methodName, String parameters) throws IllegalMethodDeclerationException{
        try {
            if (!UtilityRegex.checkLineIsEmpty(parameters)) {
                this.parameters = new MethodVariableFactory(parameters).getVariables();
            }
            this.methodName = methodName;

        }catch (Exception e){
            throw new IllegalMethodDeclerationException();
        }
    }

    /**
     * Check if a method call is a legal method call (given that the name is already checked and it is this).
     */
    public void checkVars(ArrayList<SjavacVariable> vars) throws IllegalMethodCallException{
        if(vars.size() != this.parameters.size()){
            throw new IllegalMethodCallException();
        }else{
            try {
                int counter = 0;
                for (MethodVariable varInMethod : this.parameters) {
                    varInMethod.checkVar(vars.get(counter));
                    //Notice that checkVar now gets a SjavacVariable and not a value, we do the check by the type.
                    counter++;
                }
            }catch(Exception e){
                throw new IllegalMethodCallException();
            }
        }
    }

    /**
     * A getter function for the method name.
     * @return - the relevant method name.
     */
    public String getMethodName(){return this.methodName;}

    /**
     * A getter function for the parameters.
     * @return - the relevant parameters.
     */
    public ArrayList<MethodVariable> getParameters() {
        return parameters;
    }
}
