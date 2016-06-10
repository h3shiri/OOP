package oop.ex6.main;

/**
 * This class will handle the sJava file, going line by line and creating instances
 * of classes (lines, methods, loops, ifs)
 */
public class SjavacReader {

//    ArrayList<String> file; //The original file
//    ArrayList<SjavacMethod> methods = new ArrayList<>(); //The methods that we will find in the file
//    //Notice that methods can be called before they are defined but variables cannot..
//    //So we need to  keep all the method calls in a list and in the end after all methods are declared
//    //we can check it out.. So we may need a class MethodCall" that keeps instances of method calls
//    // and then we can iterate it in the end and check that each one is valid.
//    ArrayList<MethodCall> methodCalls = new ArrayList<>();
//    ArrayList<SjavacVariable> variables = new ArrayList<>(); //The variables that we found
//    /**
//     * Constructor, it gets a line and goes one by one and first finds the simple lines (checking if it ends
//     * with ; no need for regex for that). It will keep all the variables and the method calls (not method declare)
//     * e.g ***void help(String hi);*** is a method call, all stored in array lists which we can iterate after to
//     * check after w e create all the method declarations( ends with "{").
//     * @param file
//     */
//
//    public SjavacReader(ArrayList<String> file)throws IllegalLineFormatException{
//        this.file = file; // An array of lines that we need to break into simple lines, methods and more.
//        try {
//            this.readSimpleLines(file);
//        }catch (IllegalLineFormatException e){
//            throw new IllegalLineFormatException();
//        }
//        //this.readComplexLines(file); //need to think about this one...
//
//
//    }
//    //Notice that there should be a lot of try catch and stuff here, which aren't here yet.
//    public void readSimpleLines(ArrayList<String> file) throws IllegalLineFormatException{
//        for(String line: file){ //This loops first go through all the simple lines.
//            // TODO: clear up, simple lines are only valid within certain scope aka location is crucial.
//            if(line.endsWith(";")) {
//                try {
//                    LineReader readLine = new LineReader(line, 5);
//                    if (readLine.isMethodCall()) {
//                        MethodCall methodCall = new MethodCall(line);
//                        this.methodCalls.add(methodCall);
//                    } else if (readLine.isCommentLine()) {
//                        continue;
//                    } else if (readLine.isSpaceLine()) {
//                        continue;
//                    } else if (readLine.isVariableDeclaration()) {
//                        SjavacVariable variable = new SjavacVariable(line);
//                        this.variables.add(variable);
//                    } else if (readLine.isVariableCopy()) {
//                        String variable; //Needs to break this from the line somehow...
////                        if (!checkVariableCopy(variable)) {
//                            //ERROR! YOU CANNOT COPY THE VARIABLE BECAUSE SOMETHING WENT WRONG
//                        } else {
//                            //somehow need to copy the new one and add it to the array list of variables.
//                        }
//                    }
//                }catch(IllegalLineFormatException e){
//                    throw new IllegalLineFormatException();
//                }
//            }
//        }
//    }
}
