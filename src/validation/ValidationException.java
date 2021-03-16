package validation;

public class ValidationException extends RuntimeException {
    /**
     * Excpetion Class
     * @param exception - Exception to be thrown
     */
    public ValidationException(String exception){
        super(exception);
    }
}
