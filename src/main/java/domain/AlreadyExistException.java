package domain;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException (String s) {
        super (s);
    }
}
