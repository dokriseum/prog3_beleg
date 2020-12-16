/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package exceptions;

public class SizeReachedException extends Exception {
    public SizeReachedException() {
    }

    public SizeReachedException(String message) {
        super(message);
    }

    public SizeReachedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SizeReachedException(Throwable cause) {
        super(cause);
    }

    public SizeReachedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
