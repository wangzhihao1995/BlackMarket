package com.wangzhihao.blackmarket.enums;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Description
 * <p>
 * </p>
 * DATE 05/02/2018.
 *
 * @author Wang Zhihao.
 */
@Documented
@Constraint(validatedBy = {BlackMarketEnumValueValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface BlackMarketEnum {
    public abstract String message() default "Invalid Enum Value!";

    public abstract Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};

    public abstract Class<? extends BlackMarketBaseEnum> enumClass();

    public abstract boolean ignoreCase() default false;
}
