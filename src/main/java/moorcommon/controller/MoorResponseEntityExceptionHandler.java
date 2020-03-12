package moorcommon.controller;

import moorcommon.utils.ResultObject;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class MoorResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String name = ex.getParameterName();
        Locale locale = request.getLocale();
        ResultObject resultObject = ResultObject.failure(messageSource.getMessage("parameter.missing", null, "Parameter is missing: " ,locale) + name, HttpStatus.BAD_REQUEST);
        return new ResponseEntity(resultObject, resultObject.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String methods = "";
        for (String method : ex.getSupportedMethods()) {
            methods += method + " ,";
        }
        methods = methods.substring(0, methods.length() - 1);
        Locale locale = request.getLocale();
        ResultObject resultObject = ResultObject.failure(messageSource.getMessage("wrong.http.request.method", null,"HTTP method is wrong.Correct HTTP Methods: " ,locale) + methods, HttpStatus.METHOD_NOT_ALLOWED);

        return new ResponseEntity(resultObject, resultObject.getHttpStatus());
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Locale locale = request.getLocale();
        ResultObject resultObject = ResultObject.failure(messageSource.getMessage("value.missing", null,"Please enter values" ,locale), HttpStatus.BAD_REQUEST);
        return new ResponseEntity(resultObject, resultObject.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Locale locale = request.getLocale();
        ResultObject resultObject = ResultObject.failure(messageSource.getMessage("an.error.occurred", null, "An error occurred",locale) + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity(resultObject, resultObject.getHttpStatus());
    }


    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Locale locale = request.getLocale();
        ResultObject resultObject = ResultObject.failure(messageSource.getMessage("variable.wrong.type", null,"One variable type is wrong.Correct is: " ,locale) + ex.getRequiredType().getSimpleName(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity(resultObject, resultObject.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Locale locale = request.getLocale();
        if (status == HttpStatus.NOT_FOUND) {
            ResultObject resultObject = ResultObject.failure(messageSource.getMessage("invalid.url", null,"Invalid URL:" ,locale) + ex.getRequestURL(), HttpStatus.NOT_FOUND);
            return new ResponseEntity(resultObject, resultObject.getHttpStatus());
        } else {
            ResultObject resultObject = ResultObject.failure(ex.getMessage(), status);
            return new ResponseEntity(resultObject, resultObject.getHttpStatus());
        }
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Locale locale = request.getLocale();
        ResultObject resultObject = ResultObject.failure(messageSource.getMessage("missing.id", null,"Please enter ID" ,locale), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(resultObject, resultObject.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Locale locale = request.getLocale();
        ResultObject resultObject = ResultObject.failure(messageSource.getMessage("an.error.occurred",null,"An error occurred" ,locale), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(resultObject, resultObject.getHttpStatus());
    }
}
