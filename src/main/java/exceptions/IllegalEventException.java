/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package exceptions;

public class IllegalEventException extends Exception {
    public IllegalEventException() {
    }

    public IllegalEventException(String message) {
        super(message);
    }

    public IllegalEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalEventException(Throwable cause) {
        super(cause);
    }

    public IllegalEventException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
