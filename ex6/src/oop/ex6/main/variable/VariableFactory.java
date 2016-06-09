package oop.ex6.main.variable;
import java.security.spec.ECField;
import java.util.*;
/**
 * This class gets a type of variable, and creates instances of SjavacVariables
 */
public class VariableFactory {
    String line;
    String type;
    boolean isFinal;
    ArrayList<SjavacVariables> scopeVars;
    ArrayList<SjavacVariables> variables = new ArrayList<>();

    int lineNumber;
    /**
     * Constructor
     * @param type
     * @param lineWithoutType
     */
    public VariableFactory(boolean isFinal,String type, String lineWithoutType, int lineNumber,
                           ArrayList<SjavacVariables> scopeVars) throws UnlegalVariableException{
        try {
            this.scopeVars = scopeVars;
            this.isFinal = isFinal;
            this.type = type;
            this.line = lineWithoutType;
            this.lineNumber = lineNumber;
            this.breakLineToVariables();
        }catch(Exception e){
            throw new UnlegalVariableException();
        }
    }

    /**
     * Break lineWithoutType into SjavacVariables instances, and add them to variables array list
     */
    public void breakLineToVariables() throws UnlegalVariableException{
        String value;
        String name;
        String part;
        try {
            while (this.line.length() != 0) {
                if (!this.line.contains(",")) {
                    part = this.line;
                    this.line = "";
                } else {
                    part = this.line.substring(this.line.lastIndexOf(",") + 1);
                    this.line = this.line.substring(0, this.line.lastIndexOf(","));
                }

                if (part.contains("=")) {
                    name = part.substring(0, part.indexOf("=")).replaceAll("\\s", "");
                    value = part.substring(part.lastIndexOf("=") + 1).replaceAll("\\s", "");
                    if (this.isAnExistingVar(value) != null) {
                        SjavacVariables newVar =
                                new SjavacVariables(this.isFinal,name,this.type,this.isAnExistingVar(value));
                    }else{
                        SjavacVariables newVar =
                                new SjavacVariables(this.isFinal, this.type, name, value, this.lineNumber);
                        this.variables.add(newVar);
                    }
                }else{
                    name = part.replaceAll("\\s", "");
                    SjavacVariables newVar = new SjavacVariables(this.isFinal, this.type, name, null, this.lineNumber);
                    this.variables.add(newVar);
                }
            }
        }catch(Exception E){
            throw new UnlegalVariableException();
        }
    }

    /**
     * return the array list of variables that were created by this class
     */
    public ArrayList<SjavacVariables> getVariables(){
        return this.variables;
    }

    /**
     * This is a helper method for cases like int a = b;
     * @param name
     * @return the variable if its found, if not return null
     */
    private SjavacVariables isAnExistingVar (String name){
        if (scopeVars == null) {
            return null;
        }
        for (SjavacVariables var: this.scopeVars){
            if(var.getName().equals(name)){
                return var;
            }
        }return null;
    }

}
