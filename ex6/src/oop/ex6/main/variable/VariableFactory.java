package oop.ex6.main.variable;
import oop.ex6.main.Sjavac;
import oop.ex6.main.method.MethodVariable;
import oop.ex6.main.sJavacUtil.LinkComplexNode;
import oop.ex6.main.sJavacUtil.NonExistingVariableException;

import java.util.*;
/**
 * This class gets a type of variable, and creates instances of SjavacVariable
 */
public class VariableFactory {
    ArrayList<MethodVariable> methodVars;
    public ArrayList<SjavacVariable> extraGlobalVariables = new ArrayList<>();
    public String line;
    public String type;
    public boolean isFinal;
    public LinkComplexNode currentNode;
    public ArrayList<SjavacVariable> variables = new ArrayList<>();
    public int lineNumber;

    /**
     * primary Constructor for variables using this factory.
     * @param type - the specific type of this variable.
     * @param lineWithoutType - the actual arguments raw data.
     */
    public VariableFactory(boolean isFinal, String type, String lineWithoutType, int lineNumber,
                           LinkComplexNode currentNode,ArrayList<MethodVariable> methodVars)
            throws UnlegalVariableException{
        try {
            this.methodVars = methodVars;
            this.currentNode = currentNode;
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
     * Secondary Constructor for variables using this factory.
     * @param type - the specific type of this variable.
     * @param lineWithoutType - the actual arguments raw data.
     * @param currentNode - A reference to the current scope.
     * @param extraGlobalVariables - variables ot be fed into the globals field.
     * @param lineNumber - the current line number.
     */
    public VariableFactory(boolean isFinal, String type, String lineWithoutType, int lineNumber,
                           LinkComplexNode currentNode,
                           ArrayList<SjavacVariable> extraGlobalVariables,
                           ArrayList<MethodVariable> methodVars) throws UnlegalVariableException{
        try {
            this.methodVars = methodVars;
            this.currentNode = currentNode;
            this.isFinal = isFinal;
            this.type = type;
            this.line = lineWithoutType;
            this.lineNumber = lineNumber;
            feedGlobals(extraGlobalVariables);
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
                    part = part.replaceAll("=", " = ");
                    String[] partInArray = part.split("\\s+");
                    if(partInArray.length == 1){
                        part = partInArray[0];
                    } else if(partInArray.length == 3){
                        if(!partInArray[1].equals("=")) {
                            throw new UnlegalVariableException();
                        }
                    }else{throw new UnlegalVariableException();}
                    this.line = "";
                } else {
                    part = this.line.substring(this.line.lastIndexOf(",") + 1);
                    this.line = this.line.substring(0, this.line.lastIndexOf(","));
                }

                if (part.contains("=")) {
                    name = part.substring(0, part.indexOf("=")).replaceAll("\\s", "");
                    value = part.substring(part.lastIndexOf("=") + 1).replaceAll("\\s", "");
                    SjavacVariable temp = this.isAnExistingVar(value);
                    if (temp != null) {
                        if (!temp.initialized){
                            throw new UnlegalVariableException();
                        }
                        SjavacVariable newVar =
                                new SjavacVariable
                                        (this.isFinal,name,this.type,this.isAnExistingVar(value),this.methodVars);
                    }else{
                        SjavacVariable newVar =
                                new SjavacVariable
                                        (this.isFinal, this.type, name, value, this.lineNumber, this.methodVars);
                        this.variables.add(newVar);
                    }
                }else{
                    name = part.replaceAll("\\s", "");
                    SjavacVariable newVar =
                            new SjavacVariable
                                    (this.isFinal, this.type, name, null, this.lineNumber,this.methodVars);
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
     * @param name - target of variable name.
     * @return the variable if its found, if not return null
     */
    private SjavacVariable isAnExistingVar (String name){
        try {
            return currentNode.getVariable(name);
        }
        catch (NonExistingVariableException e){
            for (SjavacVariable x: extraGlobalVariables){
                if (x.getName().equals(name)){
                    return x;
                }
            }

            return null;
        }
    }

    /**
     * A messy patch for the globals assignments.
     * @param variables - various variables that should be added.
     */
    public void feedGlobals(ArrayList<SjavacVariable> variables){
        for (SjavacVariable var : variables){
            extraGlobalVariables.add(var);
        }
    }

}
