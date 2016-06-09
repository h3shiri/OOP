package oop.ex6.main.method;

import java.util.ArrayList;

/**
 * This class represent a Sjavac method, and will carry all its relevant info
 */
public class SjavacMethod {
    //here is start to understand we will need the ? thing that they tought in the lecture. To be honest,
    //I am still not sure how it works..
    ArrayList<MethodVariable> parameters; //The parameters could be anything, we do not know what they are until init
    final String methodName;
    final int firstLine;
    final int lastLine; // These are the lines in the original Sjavac file
    ArrayList<String> sJavacFile;
    /**
     * Constructor for a new method class
     * @param methodName
     * @param parameters
     * @param firstLine
     * @param lastLine
     */
    public SjavacMethod(String methodName, String parameters, int firstLine, int lastLine,
                         ArrayList<String> sJavacFile) throws IllegalMethodDeclerationException{
        try {
            this.parameters = new MethodVariableFactory(parameters).getVariables();
            this.methodName = methodName;
            this.firstLine = firstLine;
            this.lastLine = lastLine;
            this.sJavacFile = sJavacFile; //the original file so we have the content of the method according to the
        }catch (Exception e){
            throw new IllegalMethodDeclerationException();
        }
    }

    /**
     * Check if a method call is a legal method call (given that the name is already checked and it is this).
     */
    public void checkVars(ArrayList<String> vars) throws IllegalMethodCallException{
        if(vars.size() != this.parameters.size()){
            throw new IllegalMethodCallException();
        }else{
            try {
                int counter = 0;
                for (MethodVariable varInMethod : this.parameters) {
                    varInMethod.checkVar(vars.get(counter));// this will throw an exception if not legal
                    counter++;
                }
            }catch(Exception e){
                throw new IllegalMethodCallException();
            }
        }
    }
    public String getMethodName(){return this.methodName;}
}
