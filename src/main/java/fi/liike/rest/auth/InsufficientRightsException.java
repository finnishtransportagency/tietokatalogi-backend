package fi.liike.rest.auth;

/**
 * Custom exception for this project.
 * Thrown to indicate insufficient rights for the requested action
 * based on the user's Vayla user group(s).
 */
public class InsufficientRightsException extends Exception {
    public InsufficientRightsException() {
    }

    public InsufficientRightsException(String message) {
        super(message);
    }

    public InsufficientRightsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientRightsException(Throwable cause) {
        super(cause);
    }

    public InsufficientRightsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
