package oop.ex6.main.ifAndWhileBlock;

import oop.ex6.main.variable.SjavacVariable;

import java.util.ArrayList;

//TODO: test and consider scoping affect from the inside on the outside.
/**
 * This class handles multiple boolean terms (a || b &&...)
 */
public class BooleanFactory {
    /*Class members*/
    String line;
    ArrayList<SjavacVariable> scopeVars;
    /**
     * Primal constructor.
     * @param line - the target string for the boolean expressions.
     * @param scopeVars - the relevant scope variables.
     */
    public BooleanFactory(String line, ArrayList<SjavacVariable> scopeVars) throws UnlegalBooleanExpression{
        this.line = line;
        this.scopeVars = scopeVars;
    }

    /**
     * This method parses the line to Boolean Value instances and create boolean values for each one
     * If an exception of boolean is found on the way we throw it further.
     */
    //TODO: has been tested ??
    public void parseBooleanLine() throws UnlegalBooleanExpression{
        try{
            String[] booleans = this.line.split("\\s*(&&|\\|\\|)\\s*");
            for(String bool: booleans){
                bool = bool.replaceAll("\\s*","");
                /* now check if this bool is a legit boolean value, by calling the BooleanValue class
                Which will throw a UnlegalBooleanExpression if its not legit
                 */
                new BooleanValue(scopeVars,bool);
            }
        }catch(UnlegalBooleanExpression e){
            throw new UnlegalBooleanExpression();
        }
    }
    /**
     * This method will determine whether a boolean complex line is legal
     */
    //TODO: what is the motivation for this trip?
    public void isLegal() throws UnlegalBooleanExpression{
        this.parseBooleanLine();
    }
}
