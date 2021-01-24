package be.pxl.ja.opgave1.error;

import java.nio.file.Path;
import java.time.LocalDateTime;

public class CustomerNotFoundException extends RuntimeException {
    private static final String CUSTOMER_NOT_FOUND = "UNKOWN CUSTOMER";
    public CustomerNotFoundException(String line) {
        super(String.format("%s: %s", CUSTOMER_NOT_FOUND, line));
    }
}
