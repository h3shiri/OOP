package oop.ex6.main.line;

public interface ComplexLine extends SjavacLine{
    /**
     * A getter function for the appropriate line number in the file
     * @return - the corresponding line number.
     */
    public int getLineNumber();

    @Override
    String getType();

    @Override
    String getRawData();
}
