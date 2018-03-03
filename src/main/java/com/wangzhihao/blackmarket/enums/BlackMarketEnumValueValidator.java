package com.wangzhihao.blackmarket.enums;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Description
 * <p>
 * </p>
 * DATE 24/01/2018.
 *
 * @author Wang Zhihao.
 */

public class BlackMarketEnumValueValidator implements ConstraintValidator<BlackMarketEnum, Integer> {

    private BlackMarketEnum annotation;

    @Override
    public void initialize(BlackMarketEnum annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(Integer valueForValidation, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = false;

        BlackMarketBaseEnum[] enumValues = annotation.enumClass().getEnumConstants();

        if (enumValues != null) {
            for (BlackMarketBaseEnum enumValue : enumValues) {
                if (valueForValidation == null || valueForValidation.equals(enumValue.getValue())) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
