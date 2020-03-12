package moorcommon.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public abstract class Result {

    @Getter
    private boolean successful;
    @Getter
    @JsonIgnore
    private HttpStatus httpStatus;
    private List<String> messages = new ArrayList<>();

    private Result(boolean successful, HttpStatus httpStatus) {
        this.successful = successful;
        this.httpStatus = httpStatus;
    }

    public static Result success() {
        return new Success();
    }

    public static Result success(List<String> messages) {
        return success().with(messages);
    }

    public static Result failure() {
        return new Failure();
    }

    public static Result failure(HttpStatus httpStatus) {
        return new Failure(httpStatus);
    }

    public static Result failure(List<String> messages, HttpStatus httpStatus) {
        return failure(httpStatus).with(messages);
    }

    public static Result failure(String message, HttpStatus httpStatus) {
        return failure(httpStatus).with(message);
    }


    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public Result with(List<String> strings) {
        int index = 0;
        for (String string : strings) {
            messages.add(index++, string);
        }
        return this;
    }

    public Result with(String string) {
        messages.add(string);
        return this;
    }

    public Result combine(Result other) {
        return other.match(
                success -> successful ? success(messages).with(other.messages) : failure(messages, httpStatus),
                failure -> !successful ? failure(messages, httpStatus).with(other.messages) : failure(other.messages, other.httpStatus));
    }

    public Result combine(Result... others) {
        Result result = this;
        for (Result other : others) {
            result = result.combine(other);
        }
        return result;
    }

    // region Pattern Matching Boilerplate
    protected abstract Result match(Function<Success, Result> success, Function<Failure, Result> failure);

    private static final class Success extends Result {

        private Success() {
            super(true, HttpStatus.OK);
        }

        @Override
        protected Result match(Function<Success, Result> success, Function<Failure, Result> failure) {
            return success.apply(this);
        }
    }

    private static final class Failure extends Result {

        private Failure() {
            super(false, HttpStatus.NOT_FOUND);
        }

        private Failure(HttpStatus httpStatus) {
            super(false, httpStatus);
        }

        @Override
        protected Result match(Function<Success, Result> success, Function<Failure, Result> failure) {
            return failure.apply(this);
        }
    }
    // endregion
}