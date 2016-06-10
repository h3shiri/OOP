package oop.ex6.main.variable;

import java.util.ArrayList;

/**
 * Created by Dell on 07/06/2016.
 */
public class TestVariables {


    public static void main(String[] args){
        String line = "a=3, b=4, hey";
        String type = "int";
        try {
            VariableFactory fac = new VariableFactory(false, type, line, 3, null);
            ArrayList<SjavacVariable> vars = fac.getVariables();
            for(SjavacVariable var: vars){
                System.out.println(var.type);
                System.out.println(var.intValue);
                try {
                    var.setValue("5");
                }catch(Exception e){};
                System.out.println(var.intValue + " After set");

            }
            System.out.println(vars.size());
        }
        catch (Exception e){
            System.out.println("blob");
        }
    }
}
