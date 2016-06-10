package oop.ex6.main.variable;

import oop.ex6.main.Sjavac;
import oop.ex6.main.line.ParametersFormatException;

public class SjavacVariable {
    final String[] types = {"int","double","boolean", "string", "char"};

    boolean isFinal = false;
    String name;
    String type;
    int intValue;
    double doubleValue;
    String stringValue;
    boolean booleanValue;
    String charValue;
    private int lineNumber;

    /**
     * This constructor creates a copy of an existing variable
     * @param isFinal - is final flag.
     * @param type - the variable type.
     * @param source - the SjavacVariable we copy from.
    */
    public SjavacVariable(boolean isFinal, String name, String type, SjavacVariable source)
            /* Notice that factory can also call this constructor when a line like this is found : int a = b;*/
            throws UnlegalVariableException{
        try{
            this.isFinal = isFinal;
            this.name = name;
            this.type = type;
            this.lineNumber = source.getLineNumber();
            this.stringValue = source.getValue();
            this.parseValue(this.stringValue);
        }catch(Exception e){
            throw new UnlegalVariableException();
        }
    }

    /**
     * Constructor that creates a new variable (not a copy of an existing one)
     * @param isFinal - A final flag.
     * @param type - the relevant type.
     * @param name - the variable's name.
     * @param value - the actual value.
     * @param lineNumber - the relevant line number.
     */
    public SjavacVariable(boolean isFinal, String type, String name, String value, int lineNumber) throws
    UnlegalVariableException{
        this.isFinal = isFinal;
        this.lineNumber = lineNumber;
        this.name = name;
        this.type = type;
        this.stringValue = value;
        try {
            this.parseValue(this.stringValue);
        }catch(Exception e){
            throw new UnlegalVariableException();
        }
    }

    /**
     * An autoboxing constructor for the primitives.
     * @param typeOfConst - the required type.
     * @param lineNumber - the relevant line number.
     * @throws ParametersFormatException - in case of a non valid type.
     */
    public SjavacVariable(String typeOfConst, int lineNumber) throws ParametersFormatException{
        this.isFinal = true;
        this.lineNumber = lineNumber;
        this.name = "CONST";
        switch (typeOfConst) {
            case "String":
                this.type = typeOfConst;
                break;
            case "int":
                this.type = typeOfConst;
                break;
            case "double":
                this.type = typeOfConst;
                break;
            case "char":
                this.type = typeOfConst;
                break;
            case "boolean":
                this.type = typeOfConst;
            default:
                throw new ParametersFormatException();
        }
    }

    /**
     * A parsing method to test legality of various types of values.
     * @param value - the value to be tested
     * @throws UnlegalVariableException - In case of a non valid form.
     */
    private void parseValue(String value) throws UnlegalVariableException{
        switch (this.type){
            case("int"):
                try {
                    this.intValue = Integer.parseInt(value);
                }catch (NumberFormatException e){
                    //NOT A VALID VALUE FOR INT
                }
                break;
            case("double"):
                try{
                    this.intValue = Integer.parseInt(value);
                    this.doubleValue = Double.parseDouble(value);
                }catch (NumberFormatException e){
                    //ERROR
                }
                break;
            case("char"):
                if (value.length() != 1){
                    //NOT A VALID CHAR
                }else{
                    this.charValue = value;
                }
                break;
            case("String"): //Need to check if this needs to be String or string
                this.stringValue = value;
                break;
            case("boolean"):
                if(value == "true") {
                    this.booleanValue = true;
                }else if(value == "false"){
                    this.booleanValue = false;
                }else {
                    //ERROR
                }
                break;
            default:
                throw new UnlegalVariableException();
        }
    }
    /**
     * get the type of the variable
     * @return
     */
    public String getType(){
        return this.type;
    }
    /**
     * get the name of the variable
     */
    public String getName(){
        return this.name;
    }

    /**
     * A setter function for the variable value, only applicable in case of non-final variable.
     * Note it holds that value in a string format.
     */
    public void setValue(String value) throws UnlegalVariableException {
        if (this.isFinal){
            throw new UnlegalVariableException();
        }else{
            if(this.type == "int"){
                try{
                    this.intValue = Integer.parseInt(value);
                }catch (Exception E){
                    throw new UnlegalVariableException();
                }
            }else if(this.type == "double"){
                try{
                    this.intValue = Integer.parseInt(value);
                    this.doubleValue = Double.parseDouble(value);
                }catch (Exception e){
                    throw new UnlegalVariableException();
                }
            }else if(this.type == "char"){
                try{
                    this.charValue = value;
                }catch(Exception e){
                    throw new UnlegalVariableException();
                }
            }else if(this.type == "string"){
                this.stringValue = value;
            } else if(this.type == "boolean"){
                try{
                    this.booleanValue = Boolean.parseBoolean(value);
                }catch(Exception e){
                    throw new UnlegalVariableException();
                }
            }
        }
    }
    //TODO: I'm not sure it is the best way to actually retrieve, data type consideration is crucial.
    /**
     * A getter function.
     * @return - the value of this variable in a String format.
     */
    public String getValue(){

        return this.stringValue;
    }

    /**
     * A getter function for the line number.
     * @return - the relevant lineNumber.
     */
    public int getLineNumber(){
        return this.lineNumber;
    }
}


