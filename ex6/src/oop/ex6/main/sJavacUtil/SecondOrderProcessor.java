package oop.ex6.main.sJavacUtil;
import oop.ex6.main.line.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;


public class SecondOrderProcessor {
    private Pattern simple;
    private Pattern complex;

    public static ArrayList<SjavacLine> process(ArrayList<String> rawData){
        ArrayList<SjavacLine> res = new ArrayList<>();
        for (int i=0;i<rawData.size();i++){
            String line = rawData.get(i);
            int lineNumber = i;
            if (checkForSimpleLine(line)){
                System.out.println("1");
                SimpleLine temp = new SimpLine(lineNumber, line);
                res.add(temp);
            }
            else if (checkForComplexLine(line)){
                System.out.println("2");
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
        final String simpleLineFormat = "^([.]*)(;)([\\s]*)$";
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
        final String complexLineFormat = "^(\\s)*[.]*[\\{\\}](\\s)*$";
        Pattern tempVar = Pattern.compile(complexLineFormat);
        Matcher tempMat = tempVar.matcher(lineInput);
        return tempMat.matches();
    }

    // TODO: remove main.
    public static void main(String[] args){
        ArrayList<String> temp = new ArrayList<>(Arrays.asList("int a =5;", "func(){", "double cookie", "\t", "//fsfsd"));
        ArrayList<String> temp2 = FirstOrderProcessor.process(temp);
        ArrayList<SjavacLine> res = process(temp2);
        for (int i=0;i<res.size();i++){
            System.out.println(res.get(i)+":"+res.get(i).getType());
        }
        System.out.println("out");
    }
}
