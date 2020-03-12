package moorcommon.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor(staticName = "of")
public class ResultObject<T> {

    private Result result;
    @JsonProperty("result")
    private T data;

    @JsonCreator
    private ResultObject(@JsonProperty("httpStatus") HttpStatus httpStatus,
                         @JsonProperty("messages") List<String> messages,
                         @JsonProperty("successful") Boolean successful,
                         @JsonProperty("result") T data) {

        this.result = successful
                ? Result.success(messages)
                : Result.failure(messages, httpStatus);

        this.data = data;
    }

    public static <T> ResultObject<T> of(Result result) {
        return of(result, null);
    }

    public static <T> ResultObject<T> success() {
        return of(Result.success(), null);
    }

    public static <T> ResultObject<T> success(T data) {
        return of(Result.success(), data);
    }

    public static <T> ResultObject<T> success(List<String> messages, T data) {
        return of(Result.success(messages), data);
    }

    public static <T> ResultObject<T> failure() {
        return of(Result.failure(), null);
    }

    public static <T> ResultObject<T> failure(String message) {
        return of(Result.failure().with(message), null);
    }

    public static <T> ResultObject<T> failure(List<String> messages) {
        return of(Result.failure().with(messages), null);
    }

    public static <T> ResultObject<T> failure(List<String> messages, HttpStatus httpStatus) {
        return of(Result.failure(messages, httpStatus), null);
    }

    public static <T> ResultObject<T> failure(String message, HttpStatus httpStatus) {
        return of(Result.failure(message, httpStatus), null);
    }

    public boolean isSuccessful() {
        return result.isSuccessful();
    }

    /**
     * @return Messages in an immutable set.
     */
    public List<String> getMessages() {
        return result.getMessages();
    }

    @JsonIgnore
    public HttpStatus getHttpStatus() {
        return result.getHttpStatus();
    }

    public <R> ResultObject<R> with(R object) {
        return new ResultObject<>(result, object);
    }
}
