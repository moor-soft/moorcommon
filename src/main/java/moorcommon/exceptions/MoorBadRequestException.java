package moorcommon.exceptions;

public class MoorBadRequestException extends Exception {
    public MoorBadRequestException(String errorMessage) {
        super(errorMessage);
    }
}
