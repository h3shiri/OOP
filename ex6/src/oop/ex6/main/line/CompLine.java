package oop.ex6.main.line;

/**
 * Created by shiri on 6/6/16.
 */
public class CompLine implements ComplexLine{
    /* data members */
    private final String COMPLEXLINE = "Complex";
    /** The relevant line number */
    private int lineNumber;

    /** The line's raw data */
    private String rawData;

    /** The line spesific type */
    private String type;

    /**
     * The primary constructor for this simple class.
     * @param lineNumber - the relevant line number.
     * @param rawData - the line's raw data.
     */
    public CompLine(int lineNumber, String rawData) {
        this.lineNumber = lineNumber;
        this.rawData = rawData;
        this.type = COMPLEXLINE;
    }

    /**
     * A getter function for the line number.
     * @return - the appropriate line number.
     */
    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * A getter function for the specific type.
     * @return - thge relevant type.
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * A getter function for the row data.
     * @return - the relevant raw data.
     */
    @Override
    public String getRawData() {
        return rawData;
    }

    /**
     * A setter function for a specific type.
     * @param type - the modified type.
     */
    public void setType(String type) {
        this.type = type;
    }
}
