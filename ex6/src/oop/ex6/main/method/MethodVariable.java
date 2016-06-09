package oop.ex6.main.method;

/**
 * this class represents a method parameter call (e.g type1 name1)
 */
public class MethodVariable {
    boolean isFinal;
    String type;
    /**
     * constructor
     * @param isFinal
     * @param type
     */
    public MethodVariable (boolean isFinal, String type){
        this.isFinal = isFinal;
        this.type = type;

    }
    /*get the isFinal flag*/
    public boolean getIsFinal(){return this.isFinal;}
    /*get the type*/
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
