package fi.liike.rest.auth;

/**
 * Custom exception for this project.
 * Thrown to indicate that the given data is for some reason invalid.
 */
public class InvalidTietokatalogiDataException extends Exception {
    public InvalidTietokatalogiDataException() {
    }

    public InvalidTietokatalogiDataException(String s) {
        super(s);
    }

    public InvalidTietokatalogiDataException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidTietokatalogiDataException(Throwable throwable) {
        super(throwable);
    }

    public InvalidTietokatalogiDataException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
