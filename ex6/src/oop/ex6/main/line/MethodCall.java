package oop.ex6.main.line;

import oop.ex6.main.method.SjavacMethod;
import oop.ex6.main.sJavacUtil.UtilityRegex;
import oop.ex6.main.variable.SjavacVariables;

import java.util.ArrayList;

/**
 * This class gets a line. It will break it into the relevant info for method calls.
 * It will be initialized during the iteration of each line in the Sjava file.
 * In in the end of the iteration of each line we will have an array of SjavacMethod,
 * And we will be able to call the isLegal method.
 */
public class MethodCall implements SjavacLine{
    ArrayList<String> variablesForMethod = new ArrayList<>();
    String name;
    final String type = "METHOD";
    boolean isLegal = false;

    /**
     * Constructor - gets a line and extracts the relevant info (name and parameters as variable array)
     * @param line - the relevant line data.
     */
    public MethodCall(String line){
        ArrayList<String> rawData = UtilityRegex.parseMethodCall(line);
        this.name = rawData.get(0);
        for(int i=1; i < rawData.size(); i++){
            variablesForMethod.add(rawData.get(i));
        }

    }

    /**
     * This method gets all the Methods that were defined during the Sjava file reading, and return true if
     * This method call is a legal one.
     * @param definedMethods
     * @return
     */

    public boolean isLegal(ArrayList<SjavacMethod> definedMethods){
        //Check with all the methods if this line is a legal method call
        return this.isLegal;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getRawData() {
        return null;
    }
}
