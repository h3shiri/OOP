package errors;

public class FilterNumberException extends TypeOneError {
    /**
     * Constructor setting the error message.
     * @param lineNumber - the appropriate error message past from inheriting class.
     */
    public FilterNumberException(int lineNumber){
        super(lineNumber);
    }
}
