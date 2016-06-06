package oop.ex6.main.method;

import java.util.ArrayList;

/**
 * This class represent a Sjavac method, and will carry all its relevant info
 */
public class SjavacMethod {
    //here is start to understand we will need the ? thing that they tought in the lecture. To be honest,
    //I am still not sure how it works..
    final ArrayList<?> parameters; //The parameters could be anything, we do not know what they are until init
    final String methodName;
    final int firstLine;
    final int lastLine; // These are the lines in the original Sjavac file
    ArrayList<String> sJavacFile;
    /**
     * Constructor for a new method class
     * @param methodName
     * @param parameters
     * @param firstLine
     * @param lastLine
     */
    public SjavacMethod(String methodName, ArrayList<?> parameters, int firstLine, int lastLine,
                         ArrayList<String> sJavacFile) throws IllegalMethodDeclerationException{
        this.parameters = parameters; //We need to see how we create this array of parameters to begin with
        this.methodName = methodName;
        this.firstLine = firstLine;
        this.lastLine = lastLine;
        this.sJavacFile = sJavacFile; //the original file so we have the content of the method according to the
        try {
            this.checkMethodIsLegal();
        }catch (IllegalMethodDeclerationException e){
            throw new IllegalMethodDeclerationException();
        }
    }

    /**
     * Check if the method is a legal method declaration
     */
    private void checkMethodIsLegal() throws IllegalMethodDeclerationException{
        return;
    }
}
