package oop.ex6.main.line;

/**
 * Created by shiri on 6/9/16.
 */
public class VariableDecleration implements SimpleLine{

    private String variablesData;
    private String valueType;
    private int lineNumber;

    public VariableDecleration(String valueType, String variablesData, int lineNumber){
        this.valueType = valueType;
        this.variablesData = variablesData;
        this.lineNumber = lineNumber;
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
