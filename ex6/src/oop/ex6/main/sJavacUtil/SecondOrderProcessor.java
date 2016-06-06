package oop.ex6.main.sJavacUtil;
import oop.ex6.main.line.*;
import java.util.ArrayList;
import java.util.regex.*;


public class SecondOrderProcessor {
    public static ArrayList<SjavacLine> process(ArrayList<String> rawData){
        ArrayList<SjavacLine> res = new ArrayList<>();
        for (int i=0;i<rawData.size();i++){
            String line = rawData.get(i);
            int lineNumber = i;
            if (checkForSimpleLine(line)){
                SimpleLine temp = new SimpLine(lineNumber, line);
                res.add(temp);
            }
            else if (checkForComplexLine(line)){
                CompLine temp = new CompLine(lineNumber, line);
                res.add(temp);
            }
        }
        return res;
    }

    /**
     * A static method for identifying simple lines.
     * @param lineInput - the raw line input
     * @return - true iff the line is simple.
     */
    private static boolean checkForSimpleLine(String lineInput){
        final String simpleLineFormat = "([.]*)(;)([\\s]*)?";
        Pattern tempVar = Pattern.compile(simpleLineFormat);
        Matcher tempMat = tempVar.matcher(lineInput);
        return tempMat.matches();
    }

    /**
     * A static method for identifying simple lines.
     * @param lineInput
     * @return
     */
    private static boolean checkForComplexLine(String lineInput){
        final String complexLineFormat = "(\\s)*[.]*[\\{\\}](\\s)*";
        Pattern tempVar = Pattern.compile(complexLineFormat);
        Matcher tempMat = tempVar.matcher(lineInput);
        return tempMat.matches();
    }
}
