package oop.ex6.main.line;


public class CommentLine implements  SjavacLine{
    final String type = "COMMENT";
    /**
     * returns the relevant type.
     * @return
     */
    @Override
    public String getType() {
        return "COMMENT";
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
