package oop.ex6.main.line;

public class VariableDeclerationLine implements SimpleLine{

    private String variablesData;
    private String valueType;
    private int lineNumber;
    private boolean isFinal;

    /**
     * A primary constructor for a variable deceleration.
     * @param valueType - the spesific type.
     * @param variablesData - the data to parse the variables from.
     * @param lineNumber - the relevant line number.
     */
    public VariableDeclerationLine(String valueType, String variablesData, int lineNumber){
        this.valueType = valueType;
        this.variablesData = variablesData;
        this.lineNumber = lineNumber;
        this.isFinal = false;
    }

    /**
     * A primary constructor for a variable deceleration.
     * @param valueType - the spesific type.
     * @param variablesData - the data to parse the variables from.
     * @param lineNumber - the relevant line number.
     * @param isFinal - A flag indicating whether the potential values should be set as final.
     */
    public VariableDeclerationLine(String valueType, String variablesData, int lineNumber, boolean isFinal){
        this.valueType = valueType;
        this.variablesData = variablesData;
        this.lineNumber = lineNumber;
        this.isFinal = isFinal;
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
     * A getter function for the value type.
     * @return - the specific type of variable.
     */
    public String getValueType() {
        return valueType;
    }

    /**
     * A getter function for the raw variables data.
     * @return - this relevant data.
     */
    public String getVariablesData() {
        return variablesData;
    }

    /**
     * A getter function for the specific type (variable deceleration).
     * A variable deceleration
     * @return - VarDeclare
     */
    @Override
    public String getType() {
        String TYPE = "VarDeclare";
        return TYPE;
    }

    /**
     * In this case the raw data has been processed so it shall be null.
     */
    @Override
    public String getRawData() {
        return null;
    }
}
