package by.school.diary.exception;

import java.util.Map;
import java.util.stream.Collectors;

public class ValidationCustomException extends RuntimeException {
    public ValidationCustomException(Map<String, String> errors) {
        super(errors.keySet().stream()
                .map(key -> key + "=" + errors.get(key))
                .collect(Collectors.joining(", ", "{", "}")));
    }
}
