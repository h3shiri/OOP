package oop.ex6.main.line;

import java.util.ArrayList;

public interface SjavacLine{
    /**
     * get the type of the line
     * @return the corresponding type
     */
    public String getType();

    /**
     * get the raw data of the line
     */
    public String getRawData();

    /*
     * get various arguments used for fine data in later classes.
     * default for some classes don't actually have to implement this.
     */
    default ArrayList<String> getArguments(){
        return null;
    }
}