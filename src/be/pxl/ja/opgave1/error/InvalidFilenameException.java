package be.pxl.ja.opgave1.error;

public class InvalidFilenameException extends RuntimeException {
    private final static String INVALID_FILENAME = "INVALID FILENAME";

    public InvalidFilenameException() {
        super(INVALID_FILENAME);
    }
}
