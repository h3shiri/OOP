package oop.ex6.main.variable;
import java.util.*;
import java.nio.charset.CharacterCodingException;

/**
 * Created by Dell on 02/06/2016.
 */
public class SjavacVariables{
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
     * @param isFinal
     * @param type
     * @param a
    */
    public SjavacVariables (boolean isFinal,String name, String type, SjavacVariables a)
            /* Notice that factory can also call this constructor when a line like this is found : int a = b;*/
            throws UnlegalVariableException{
        try{
            this.isFinal = isFinal;
            this.name = name;
            this.type = type;
            this.stringValue = a.getValue();
            this.parseValue(this.stringValue);
        }catch(Exception e){
            throw new UnlegalVariableException();
        }
    }
    /**
     * Constructor that creates a new variable (not a copy of an existing one)
     * @param isFinal
     * @param type
     * @param name
     * @param value
     * @param lineNumber
     */
    public SjavacVariables(boolean isFinal,String type, String name, String value, int lineNumber) throws
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
     * this method let's you set a value only if the variable is not final
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
                }catch (Exception E){
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
    /**
     * get the value of this variable
     * @return
     */
    public String getValue(){

        return this.stringValue;
    }
}


