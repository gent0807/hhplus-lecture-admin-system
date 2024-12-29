package org.example.lecutreAdminSystem.interfaces.api.common.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.lecutreAdminSystem.interfaces.api.common.validation.ApplyIdValidator;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ApplyIdValidator.class})
@Documented
public @interface ApplyId {

    String message() default "수강 신청 번호가 올바르지 않습니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
