package filters;

public class FilterNumberException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor setting the error message.
     * @param errorMsg - the appropriate error message past from inheriting class.
     */
    public FilterNumberException(String errorMsg){
        super(errorMsg);
    }
}
