package oop.ex6.main.method;

import oop.ex6.main.variable.SjavacVariable;

/**
 * This class represents a method parameters aka (final int a, String b)
 * thus each instant is simply an indication og type and flag used for initializing
 * a new function.
 */
public class MethodVariable {
    /** A flag indicating whether it's final */
    boolean isFinal;
    /** The argument type */
    String type;

    /**
     * constructor for the methods parameters.
     * @param isFinal - A flag indicting whether its final.
     * @param type - the parameter type.
     */
    public MethodVariable (boolean isFinal, String type){
        this.isFinal = isFinal;
        this.type = type;
    }

    /**
     * A getter function for the final flag.
     * @return - true iff the final flag is on.
     */
    public boolean getIsFinal(){return this.isFinal;}

    /**
     * A getter function for the parameter type.
     * @return - A string representing the type in question.
     */
    public String getType(){return this.type;}

    /**
     * This method gets a SjavacVariable and will throw an exception if the types could not be matched.
     * @param var - the SjavacVariable that we will check
     * @throws IllegalMethodCallException
     * @throws Exception
     */
    public void checkVar(SjavacVariable var) throws IllegalMethodCallException{
        try {
            switch (this.type) {
                case "String":
                    if(!(var.getType().equals("String") || var.getType().equals("char"))){
                        throw new IllegalMethodCallException();
                    } break;
                case "int":
                    if(!var.getType().equals("int")){
                        throw new IllegalMethodCallException();
                    } break;
                case "double":
                    if(!(var.getType().equals("int") || var.getType().equals("double")) ){
                        throw new IllegalMethodCallException();
                    }break;
                case "char":
                    if(!var.getType().equals("char")) {
                        throw new IllegalMethodCallException();
                    }break;
                case "boolean":
                    if(!(var.getType().equals("boolean") || var.getType().equals("int")
                            || var.getType().equals("double"))){
                        throw new IllegalMethodCallException();
                    }break;
                default:
                    throw new IllegalMethodCallException();
            }
        }catch(Exception e){
                throw new IllegalMethodCallException();

        }
    }
}
