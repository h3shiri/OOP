package oop.ex6.main.method;
import java.util.*;
/**
 * This class creates instances of method variables given a single line (e.g type1 name1, final type2 name2....)
 */
public class MethodVariableFactory {
    ArrayList<MethodVariable> variables = new ArrayList<>();
    String types = "int double char String boolean";
    /**
     * Constructor. gets the line and create MethodVariables
     * @param lineToParse
     */
    public MethodVariableFactory(String lineToParse) throws IllegalMethodDeclerationException{
        try{
            String part;
            while(lineToParse.length() > 0){
                if(!lineToParse.contains(",")){
                    part = lineToParse;
                    lineToParse = "";
                }else{
                    part = lineToParse.substring(lineToParse.lastIndexOf(",") + 1);
                    lineToParse = lineToParse.substring(0,lineToParse.lastIndexOf(","));
                    this.parsePart(part);
                }
            }
            Collections.reverse(this.variables);
        }catch(Exception e){
            throw new IllegalMethodDeclerationException();
        }
    }

    /**
     * This method gets a part (e.g final int a or boolean flag), parses it and add it to the array of MethodVars
     * @param part
     */
    private void parsePart(String part){
        String[] wordsInPart = part.split("\\s+");

        if(wordsInPart.length == 3){
            if(wordsInPart[0] != "final"){
                //Replace this return with an exception
                return;
            }else if (!this.types.contains(wordsInPart[1])){
                //Replace this return with a exception throw
                return;
            }
            MethodVariable methodVar = new MethodVariable(true,wordsInPart[1]);
            this.variables.add(methodVar);
        }else if(wordsInPart.length == 2){
            if(this.types.contains(wordsInPart[1])){
                //throw error
            }else{
                MethodVariable methodVar = new MethodVariable(false,wordsInPart[0]);
                this.variables.add(methodVar);
            }
        }
    }

    /**
     * This is an array of the method. So a method will hold this and  when we are comparing a methodCall
     * We will be able to iterate the MethodVariables and compare them.
     * @return
     */
    public ArrayList<MethodVariable> getVariables(){
        return this.variables;
    }
}
