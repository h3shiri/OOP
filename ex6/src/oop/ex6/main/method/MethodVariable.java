package oop.ex6.main.method;

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

    public void checkVar(String value) throws IllegalMethodCallException, Exception{
        try {
            switch (this.type) {
                case "String":
                    break;
                case "int":
                    Integer.parseInt(value);
                    break;
                case "double":
                    Double.parseDouble(value);
                    break;
                case "char":
                    if(value.length() != 1) {
                        throw new Exception();
                    }
                    break;
                case "boolean":
                    Boolean.parseBoolean(value);
                default:
                    throw new Exception();
            }
        }catch(Exception e){
                throw new IllegalMethodCallException();

        }
    }
}
