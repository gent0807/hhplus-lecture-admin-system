package org.example.lecutreAdminSystem.interfaces.api.common.validation.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.lecutreAdminSystem.interfaces.api.common.validation.LectureIdValidator;
import org.example.lecutreAdminSystem.interfaces.api.common.validation.UserIdValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {LectureIdValidator.class})
@Documented
public @interface LectureId {
    String message() default "강의 번호가 올바르지 않습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
