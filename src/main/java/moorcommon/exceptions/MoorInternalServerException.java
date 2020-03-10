package moorcommon.exceptions;

public class MoorInternalServerException extends Exception {
    public MoorInternalServerException(String errorMessage) {
        super(errorMessage);
    }
}
