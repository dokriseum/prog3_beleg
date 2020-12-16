/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package exceptions;

public class NotImplementsException extends Exception {
    public NotImplementsException() {
    }

    public NotImplementsException(String message) {
        super(message);
    }

    public NotImplementsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotImplementsException(Throwable cause) {
        super(cause);
    }

    public NotImplementsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
