package oop.ex6.main.variable;

/**
 * Created by Dell on 02/06/2016.
 */
public class SjavacVariables {
    final String[] types = {"int","double","boolean", "string", "char"};
    String name;
    String type;
    int intValue;
    double doubleValue;
    String stringValue;
    boolean booleanValue;
    String charValue;
    public SjavacVariables(String line){
        //NEED TO BREAK LINE INTO NAME TYPE AND VALUE!
        this.name = name;
        this.type = type;
        switch (type){
            case("int"):
                try {
                    this.intValue = Integer.parseInt(value);
                    this.doubleValue = Double.parseDouble(value);
                    this.booleanValue = (Integer.parseInt(value) != 0);
                }catch (NumberFormatException e){
                    //NOT A VALID VALUE FOR INT
                }
                break;
            case("double"):
                try{
                    this.intValue = Integer.parseInt(value);
                    this.doubleValue = Double.parseDouble(value);
                    this.booleanValue = (Integer.parseInt(value) != 0);
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
                //ERROR NO SUCH TYPE
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
     * get the value of this variable
     * @return
     */
    public String getValue(){
        //Some thinking needs to be done here, do we want to return it as a string or as something else..
        //this could be like a wrapper thing, for example if its a int it will return this.intValue
        //The problem is with the methods signature.. what should it be?
        return "haha";
    }
}


