package org.example.lecutreAdminSystem.interfaces.api.common.validation;

import jakarta.validation.ConstraintValidator;
import org.example.lecutreAdminSystem.interfaces.api.common.validation.annotation.LectureId;

public class LectureIdValidator implements ConstraintValidator<LectureId, Long> {
    @Override
    public boolean isValid(Long value, jakarta.validation.ConstraintValidatorContext context) {
        if(value.longValue() <= 0){
            return false;
        }
        return true;
    }
}
