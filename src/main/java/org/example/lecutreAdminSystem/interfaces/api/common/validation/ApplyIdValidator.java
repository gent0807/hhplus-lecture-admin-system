package org.example.lecutreAdminSystem.interfaces.api.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.lecutreAdminSystem.interfaces.api.common.validation.annotation.ApplyId;

public class ApplyIdValidator implements ConstraintValidator<ApplyId, Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {

        if(value == null){
            return false;
        }

        if(value.longValue() <= 0){
            return false;
        }

        return true;
    }
}
