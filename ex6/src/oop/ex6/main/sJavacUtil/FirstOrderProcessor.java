package oop.ex6.main.sJavacUtil;

import oop.ex6.main.line.SjavacLine;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstOrderProcessor {
    public static ArrayList<String> process(ArrayList<String> rawData){
        ArrayList<String> res = new ArrayList<>();
        for(int i=0; i<rawData.size(); i++){
            if (UtilityRegex.checkLineIsComment(rawData.get(i))){
                continue;
            }
            else if (UtilityRegex.checkLineIsEmpty(rawData.get(i))){
                continue;
            }
            else{
                res.add(rawData.get(i));
            }
        }
        return res;
    }
    // TODO: remove main.
    public static void main(String[] args){
        ArrayList<String> temp = new ArrayList<>(Arrays.asList("int a =5;", "func(){", "double cookie", "\t", "//fsfsd"));
        ArrayList<String> res = process(temp);
        for (int i=0;i<res.size();i++){
            System.out.println(res.get(i));
        }
        System.out.println("5");
    }
}
