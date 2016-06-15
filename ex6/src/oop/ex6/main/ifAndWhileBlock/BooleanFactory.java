package oop.ex6.main.ifAndWhileBlock;

import oop.ex6.main.sJavacUtil.LinkComplexNode;


//TODO: test and consider scoping affect from the inside on the outside.
/**
 * This class handles multiple boolean terms (a || b &&...)
 */
public class BooleanFactory {
    /*Class members*/
    /** The raw data to build booleans from */
    String line;

    /** A reference tot he actual node */
    LinkComplexNode currentNode;
    /**
     * Primal constructor.
     * @param line - the target string for the boolean expressions.
     * @param currentNode - the relevant scope.
     */
    public BooleanFactory(String line, LinkComplexNode currentNode) throws UnlegalBooleanExpression{
        this.line = line;
        this.currentNode = currentNode;
        isLegal();
    }

    /**
     * This method parses the line to Boolean Value instances and create boolean values for each one
     * If an exception of boolean is found on the way we throw it further.
     */
    //TODO: has been tested ??
    public void isLegal() throws UnlegalBooleanExpression {

        String[] booleans = this.line.split("\\s*(&&|\\|\\|)\\s*");
        for (String bool : booleans) {
            bool = bool.replaceAll("\\s*", "");
            /* now check if this bool is a legit boolean value, by calling the BooleanValue class
             * Which will throw a UnlegalBooleanExpression if its not legit
             */
            new BooleanValue(currentNode, bool);
        }
    }
}
