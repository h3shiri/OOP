package oop.ex6.main.variable;
import java.util.*;
/**
 * This class gets a type of variable, and creates instances of SjavacVariable
 */
public class VariableFactory {
    String line;
    String type;
    boolean isFinal;
    ArrayList<SjavacVariable> scopeVars;
    ArrayList<SjavacVariable> variables = new ArrayList<>();

    int lineNumber;
    /**
     * Constructor
     * @param type - the specific type of this variable.
     * @param lineWithoutType
     */
    public VariableFactory(boolean isFinal,String type, String lineWithoutType, int lineNumber,
                           ArrayList<SjavacVariable> scopeVars) throws UnlegalVariableException{
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
     * Break lineWithoutType into SjavacVariable instances, and add them to variables array list
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
                        SjavacVariable newVar =
                                new SjavacVariable(this.isFinal,name,this.type,this.isAnExistingVar(value));
                    }else{
                        SjavacVariable newVar =
                                new SjavacVariable(this.isFinal, this.type, name, value, this.lineNumber);
                        this.variables.add(newVar);
                    }
                }else{
                    name = part.replaceAll("\\s", "");
                    SjavacVariable newVar = new SjavacVariable(this.isFinal, this.type, name, null, this.lineNumber);
                    this.variables.add(newVar);
                }
            }
        }catch(Exception E){
            throw new UnlegalVariableException();
        }
    }

    /**
     * A getter function for the list of the new variables.
     * @return the array list of variables that were created by this class
     */
    public ArrayList<SjavacVariable> getVariables(){
        return this.variables;
    }

    /**
     * This is a helper method for cases like int a = b;
     * @param name
     * @return the variable if its found, if not return null
     */
    private SjavacVariable isAnExistingVar (String name){
        if (scopeVars == null) {
            return null;
        }
        for (SjavacVariable var: this.scopeVars){
            if(var.getName().equals(name)){
                return var;
            }
        }return null;
    }

}
