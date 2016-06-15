package oop.ex6.main.line;

import java.util.ArrayList;

public class LiteralOpeningLine implements ComplexLine{
    /* data members */
    /* the literals raw data */
    private String literalsRawData;
    /* The relevant line number */
    private int lineNumber;
    /* The specific type */
    private final String TYPE = "literalOpening";

    /* The arguments for the data extraction */
    private ArrayList<String> arguments = new ArrayList<>();

    /**
     * A primal constructor for the if/while block
     * @param literalsRawData - the raw data for the while/if processing.
     * @param lineNumber - the relevant line number.
     */
    public LiteralOpeningLine(String literalsRawData, int lineNumber) {
        this.literalsRawData = literalsRawData;
        this.lineNumber = lineNumber;
        arguments.add(literalsRawData);
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
     * A getter function for the appropriate specific type.
     * @return - the corresponding type.
     */
    @Override
    public String getType() {
        return TYPE;
    }

    /**
     * A non-applicable field
     * @return - null.
     */
    @Override
    public String getRawData() {
        return null;
    }

    /**
     * A getter for the actual arguments in the literal.
     * @return - the raw data.
     */
    @Override
    public ArrayList<String> getArguments() {
        return arguments;
    }
}
