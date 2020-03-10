package moorcommon.exceptions;

public class MoorUnauthorizedException extends Exception {
    public MoorUnauthorizedException(String errorMessage) {
        super(errorMessage);
    }
}
