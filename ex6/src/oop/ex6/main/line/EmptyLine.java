package oop.ex6.main.line;


public class EmptyLine implements  SjavacLine{
    final String type = "EMPTY";
    /**
     * returns the relevant type.
     * @return
     */
    @Override
    public String getType() {
        return this.type;
    }

    /**
     * returns the relevant data (which is none aka null).
     * @return - null.
     */
    @Override
    public String getRawData() {
        return null;
    }


}
