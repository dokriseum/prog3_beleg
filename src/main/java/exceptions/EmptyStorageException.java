/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package exceptions;

public class EmptyStorageException extends Exception {
    public EmptyStorageException() {
    }

    public EmptyStorageException(String message) {
        super(message);
    }

    public EmptyStorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyStorageException(Throwable cause) {
        super(cause);
    }

    public EmptyStorageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
