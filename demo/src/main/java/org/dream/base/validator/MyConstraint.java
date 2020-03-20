package org.dream.base.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义校验器
 */
@Target({ElementType.METHOD, ElementType.FIELD}) // 标注哪里
@Retention(RetentionPolicy.RUNTIME) // 运行时校验
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {

    String message() default "测试";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
