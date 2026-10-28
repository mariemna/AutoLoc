package tn.esprit.mariemnajahi.exception;

/**
 * Raised when a requested domain object does not exist.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
