package oop.ex6.main.variable;
import java.util.*;
/**
 * This class gets a type of variable, and creates instances of SjavacVariables
 */
public class VariableFactory {
    String line;
    String type;
    ArrayList<SjavacVariables> variables = new ArrayList<>();
    int lineNumber;
    /**
     * Constructor
     * @param type
     * @param lineWithoutType
     */
    public VariableFactory(String type, String lineWithoutType, int lineNumber){
        this.type = type;
        this.line = lineWithoutType;
        this.lineNumber = lineNumber;
        this.breakLineToVariables();
    }

    /**
     * Break lineWithoutType into SjavacVariables instances, and add them to variables array list
     */
    public void breakLineToVariables(){
        String value;
        String name;
        String part;
        while (this.line.length() != 0){
            if(!this.line.contains(",")){
                part = this.line;
                this.line ="";
            }else {
                part = this.line.substring(this.line.lastIndexOf(",") + 1);
                this.line = this.line.substring(0,this.line.lastIndexOf(","));
            }

            if(part.contains("=")){
                name = part.substring(0,part.indexOf("=")).replaceAll("\\s","");
                value = part.substring(part.lastIndexOf("=") +1).replaceAll("\\s","");

                SjavacVariables newVar = new SjavacVariables(this.type, name, value, this.lineNumber);
                this.variables.add(newVar);

            }else{
                name = part.replaceAll("\\s","");
                SjavacVariables newVar = new SjavacVariables(this.type,name,null,this.lineNumber);
                this.variables.add(newVar);
            }

        }
    }

    /**
     * return the array list of variables that were created by this class
     */
    public ArrayList<SjavacVariables> getVariables(){
        return this.variables;
    }

    public static void main(){
        String line = "a=3, b=4, hey";
        String type = "int";
        VariableFactory fac = new VariableFactory(type,line,3);
        ArrayList<SjavacVariables> vars = fac.getVariables();
        System.out.println(vars.size());
    }
}
