package oop.ex6.main.sJavacUtil;

import java.util.ArrayList;

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
}
