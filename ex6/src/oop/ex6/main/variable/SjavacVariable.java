package oop.ex6.main.variable;

import oop.ex6.main.Sjavac;
import oop.ex6.main.line.ParametersFormatException;

import java.util.Arrays;
import java.util.List;

public class SjavacVariable {
    /** class members*/
    final String[] types = {"int","double","boolean", "string", "char"};
    /**IMPORTANT REMARK: All these are public for a reason.
     * If you want to get the value, you need to ask for the specific type. This is OK because we have exceptions
     * that handles situation when someone tries using the wrong type. The point is that a getter function is not
     * Going to work because its return type can be only 1 type, when we have several types of data.
     */
    public boolean isFinal = false;
    public String unParsedValue;
    public String name;
    public String type;
    public int intValue;
    public double doubleValue;
    public String stringValue;
    public boolean booleanValue;
    public String charValue;
    private int lineNumber;

    /**
     * This constructor creates a copy of an existing variable
     * @param isFinal - is final flag.
     * @param type - the variable type.
     * @param source - the SjavacVariable we copy from.
    */
    public SjavacVariable(boolean isFinal, String name, String type, SjavacVariable source)
            throws UnlegalVariableException{
        try{
            this.isFinal = isFinal;
            this.name = name;
            this.type = type;
            this.lineNumber = source.getLineNumber();
            this.stringValue = source.unParsedValue;
            this.parseValue(this.stringValue);
        }catch(UnlegalVariableException e){
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
        this.unParsedValue = value;
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
                    throw new UnlegalVariableException();
                }
                break;
            case("double"):
                try{
                    this.intValue = Integer.parseInt(value);
                    this.doubleValue = Double.parseDouble(value);
                }catch (NumberFormatException e){
                    throw new UnlegalVariableException();
                }
                break;
            case("char"):
                value = value.trim();
                if(value.matches("^\"(.*?)\"$")){
                    value = value.substring(1,value.length() -1);//remove " "
                }else{
                    throw new UnlegalVariableException();
                }
                if (value.length() != 1){
                    throw new UnlegalVariableException();
                }else{
                    this.charValue = value;
                }
                break;
            case("String"):
                if(value.matches("^\"(.*?)\"$")) {
                    value = value.trim();// remove the spaces (only before and after , not between words in string)
                    this.stringValue = value.substring(1,value.length() -1); //remove the  " "
                }else{
                    throw new UnlegalVariableException();
                }
                break;
            case("boolean"):
                if(value.equals("true")) {
                    this.booleanValue = true;
                }else if(value.equals("false")){
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
            if(this.type.equals("int")){
                try{
                    this.intValue = Integer.parseInt(value);
                }catch (NumberFormatException e){
                    throw new UnlegalVariableException();
                }
            }else if(this.type.equals("double")){
                try{
                    this.intValue = Integer.parseInt(value);
                    this.doubleValue = Double.parseDouble(value);
                }catch (NumberFormatException e){
                    throw new UnlegalVariableException();
                }
            }else if(this.type.equals("char")){
                try{
                    this.charValue = value;
                }catch(Exception e){
                    throw new UnlegalVariableException();
                }
            }else if(this.type.equals("string")){
                this.stringValue = value;
            } else if(this.type.equals("boolean")){
                try{
                    this.booleanValue = Boolean.parseBoolean(value);
                }catch(Exception e){
                    throw new UnlegalVariableException();
                }
            }
        }
    }

    /**
     * A morphing process from one variable to another.
     * @param source - the SjavacVariable we try to insert.
     * @throws NonCompatibleTypes - In case of a non matching type
     */
    public void morph(SjavacVariable source) throws NonCompatibleTypes{
        String sourceType = source.getType();
        if (sourceType != getType()){
            /* Note the exercise specification is an equal time */
            throw new NonCompatibleTypes();
        }
        switch (getType()) {
            case ("int"):
                intValue = source.intValue;
                break;
            case ("double"):
                doubleValue = source.doubleValue;
                break;
            case ("String"):
                stringValue = source.stringValue;
                break;
            case ("char"):
                charValue = source.charValue;
                break;
            case ("boolean"):
                booleanValue = source.booleanValue;
                break;
        }
    }



    /**
     * A getter function for the line number.
     * @return - the relevant lineNumber.
     */
    public int getLineNumber(){
        return this.lineNumber;
    }
}


