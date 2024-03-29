/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package exceptions;

public class WrongInputException extends Exception {
    public WrongInputException() {
    }

    public WrongInputException(String message) {
        super(message);
    }

    public WrongInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongInputException(Throwable cause) {
        super(cause);
    }

    public WrongInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
