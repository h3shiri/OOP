package oop.ex6.main.line;

import java.util.ArrayList;

public class MethodDefinitionLine implements ComplexLine {
    /* data members */

    /* the function name */
    private String funcName;
    /* The parameters raw data */
    private String parametersRawData;
    /* The line number */
    private int lineNumber;
    /* The specific type indicator */
    private final String TYPE = "functionDeclare";

    private ArrayList<String> arguments = new ArrayList<>();
    /**
     * The primary constructor.
     * @param funcName - the functions actual name.
     * @param parametersRawData - the potential parameters.
     * @param lineNumber - the relevant line number.
     */
    public MethodDefinitionLine(String funcName, String parametersRawData, int lineNumber) {
        this.funcName = funcName;
        this.parametersRawData = parametersRawData;
        this.lineNumber = lineNumber;
        arguments.add(funcName);
        arguments.add(parametersRawData);
    }

    /**
     * A getter function for the function's name.
     * @return - the actual name.
     */
    public String getFuncName() {
        return funcName;
    }

    /**
     * A getter function for the parameters raw data.
     * @return - the corresponding raw data.
     */
    public String getParametersRawData() {
        return parametersRawData;
    }

    /**
     * A getter function for the appropriate line number in the file
     * @return - the corresponding line number.
     */
    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * A getter function for the specific type
     * @return - A magic String indicating we have function deceleration.
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * A non-applicable method the data has been processed.
     * @return - null
     */
    @Override
    public String getRawData() {
        return null;
    }

    /**
     * A getter function for the actual important arguments.
     * @return
     */
    @Override
    public ArrayList<String> getArguments() {
        return arguments;
    }
}
