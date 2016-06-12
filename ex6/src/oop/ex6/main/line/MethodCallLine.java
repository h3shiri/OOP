package oop.ex6.main.line;

import oop.ex6.main.method.SjavacMethod;
import oop.ex6.main.variable.SjavacVariable;

import java.util.ArrayList;

/**
 * This class gets a line. It will break it into the relevant info for method calls.
 * It will be initialized during the iteration of each line in the Sjava file.
 * In in the end of the iteration of each line we will have an array of SjavacMethod,
 * And we will be able to call the isLegal method.
 */
public class MethodCallLine implements SimpleLine{
    /*data members fro the function call */
    /** An array holding all the parameters */
    ArrayList<SjavacVariable> variablesForMethod = new ArrayList<>();
    /** A string for the function's name */
    private String functionName;
    /** An int tracing the line number */
    private int lineNumber;
    /** A special string indicating it's a method call. */
    final String type = "METHODCALL";
    /** The raw data for the various parameters */
    private String parametersRawData;

    /**
     * Constructor - gets a line and extracts the relevant info (name and parameters as variable array)
     * @param functionName - the relevant function name.
     * @param parametersRawData - the relevant parameters raw data.
     * @param lineNumber - the appropriate line number.
     */
    public MethodCallLine(String functionName, String parametersRawData, int lineNumber){
        this.functionName = functionName;
        this.lineNumber = lineNumber;
        this.parametersRawData = parametersRawData;
    }

    /**
     * we have changed to the specific type to identify method call.
     * @return - A string containing "METHODCALL" a token to recognize such calls.
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * it has been thoroughly processed and thus no longer tracks
     * the raw data.
     * @return - null.
     */
    @Override
    public String getRawData() {
        return null;
    }

    /**
     * A getter function for the appropriate line number in the file.
     * @return - the corresponding line number.
     */
    @Override
    public int getLineNumber() {
        return lineNumber;
    }
}
