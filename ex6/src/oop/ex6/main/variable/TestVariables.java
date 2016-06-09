package oop.ex6.main.variable;

import oop.ex6.main.Sjavac;

import java.util.ArrayList;

/**
 * Created by Dell on 07/06/2016.
 */
public class TestVariables {


    public static void main(String[] args){
        String line = "a=3, b=4, hey";
        String type = "int";
        VariableFactory fac = new VariableFactory(false,type,line,3);
        ArrayList<SjavacVariables> vars = fac.getVariables();
        for(SjavacVariables var: vars){
            System.out.println(var.type);
            System.out.println(var.intValue);
            try {
                var.setValue("5");
            }catch(Exception e){};
            System.out.println(var.intValue + " After set");

        }
        System.out.println(vars.size());
    }
}
