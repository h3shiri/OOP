package oop.ex6.main.line;

import java.util.ArrayList;

public class VariableSubstitutionLine implements SimpleLine {

    /* data members */
    /* The potential target to copy into */
    private String target;
    /* the source material */
    private String source;
    /* Appropriate line number */
    private int lineNumber;

    private ArrayList<String> arguments = new ArrayList<>();

    private final String type = "Substitution";

    public VariableSubstitutionLine(String target, String source, int lineNumber) {
        this.target = target;
        this.source = source;
        this.lineNumber = lineNumber;
        arguments.add(target);
        arguments.add(source);
    }

    /**
     * A getter function for the target String.
     * @return - the target String.
     */
    public String getTarget() {
        return target;
    }

    /**
     * A getter function for the source String.
     * @return - the source String.
     */
    public String getSource() {
        return source;
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
     * A getter function for the specific type of this simpleLine.
     * @return - the magic String corresponding to this type (Substitution).
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Non applicable the line has undergone processing.
     * @return - null.
     */
    @Override
    public String getRawData() {
        return null;
    }

    /**
     * A getter function for actual arguments data.
     * @return - the relevant data.
     */
    @Override
    public ArrayList<String> getArguments() {
        return arguments;
    }
}
