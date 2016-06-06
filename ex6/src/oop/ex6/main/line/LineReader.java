package oop.ex6.main.line;

import oop.ex6.main.sJavacUtil.UtilityRegex;

/**
 * This class is reading a single line, 
 */
public class LineReader{
    private String line;
    private int lineNumber;
    /**
     * Constructor, also checks that the line is valid
     * @param line - the line's raw data.
     * @param lineNumber - the current line number.
     */
    public LineReader(String line, int lineNumber) throws IllegalLineFormatException{
        this.line = line;
        this.lineNumber = lineNumber;
        this.checkSimpleValidatyOfLine(line);
    }

    /**
     * A getter function for the appropriate line number.
     * @return - the corresponding line number.
     */
    public int getLineNumber() {
        return lineNumber;
    }

    private void checkSimpleValidatyOfLine(String lineInput){

    }

    /**
     * This method checks that the line is legal, will throw an exception if not
     */
    //TODO: check breakdown may be too early.
    private void checkAdvancedValidatyOfLine(String line) throws IllegalLineFormatException {
        if(!isMethodCall() && !isSpaceLine() && !isCommentLine() && !isVariableCopy()
                && !isVariableDeclaration()){
            throw new IllegalLineFormatException() ;
        }
    }
    /**
     * tests the line for a method call format.
     * @return - True if the line was recognized as a method call.
     */
    public boolean isMethodCall(){
        return UtilityRegex.checkLineIsMethodCall(this.line);
    }

    /**
     * checks if the line is empty of code.
     * @return - true if a line is just spaces
     */
    public boolean isSpaceLine(){
        return UtilityRegex.checkLineIsEmpty(this.line);
    }

    /**
     * tests for a comment format.
     * @return - true if the line is a comment line
     */
    public boolean isCommentLine(){
        return UtilityRegex.checkLineIsComment(this.line);
    }

    /**
     * return true if the line is not valid Sjava line
     */
    public boolean isVariableDeclaration() {

        return UtilityRegex.checkLineIsVariableDeclaration(this.line);
    }

    /**
     * Return true if the line is a variable copy (int b = a)
     * @return
     */
    public boolean isVariableCopy(){
        //REGEX
        return true;
    }
}
