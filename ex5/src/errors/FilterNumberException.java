package errors;

public class FilterNumberException extends TypeOneError{
    /**
     * Constructor setting the error message.
     * @param lineNumber - the line number in the command file.
     */
    public FilterNumberException(int lineNumber){
        super(errorMsg);
    }
}
