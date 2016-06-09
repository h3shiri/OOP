package oop.ex6.main.method;

import oop.ex6.main.line.SjavacLine;
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
public class MethodCall implements SimpleLine{
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

    public void isLegal(ArrayList<SjavacMethod> definedMethods) throws IllegalMethodCallException{
        try{
            SjavacMethod method = checkName(definedMethods); //this checks if there is a method with the same name
            if(method == null){
                throw new IllegalMethodCallException();
            }else{
                method.checkVars(this.variablesForMethod);
            }
        }catch (Exception e){
            throw new IllegalMethodCallException();
        }
    }

    /**
     * get the list of methods that are defined and look for a method with the same name
     * @param definedMethods
     * @return
     */
    private SjavacMethod checkName(ArrayList<SjavacMethod> definedMethods){
        for (SjavacMethod method : definedMethods){
            if(method.getMethodName() == this.name){
                return method;
            }
        }return null;
    }


    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getRawData() {
        return null;
    }

    /**
     * get the variables that were called with the method call
     * @return
     */
    public ArrayList<String> getVars(){ return this.variablesForMethod;}
}
