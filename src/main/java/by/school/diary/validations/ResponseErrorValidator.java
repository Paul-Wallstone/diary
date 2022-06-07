package by.school.diary.validations;


import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseErrorValidator {
    public Map<String, String> mapValidationService(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(result.getAllErrors())) {
                result.getAllErrors().forEach(error -> {
                    errorMap.put(error.getCode(), error.getDefaultMessage());
                });
            }
            result.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });
            return errorMap;
        }
        return null;
    }
}
