package com.uu.validator;


import com.uu.annotation.EnumValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description：
 * @Date: 2020/8/20
 * @Author: liangqing
 * @Email: clarence_liang@163.com
 */
public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {

    private String[] strValues;
    private int[] intValues;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        strValues = constraintAnnotation.strValues();
        intValues = constraintAnnotation.intValues();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context ) {
        if(value instanceof String) {
            for (String s:strValues) {
                if(s.equals(value)){
                    return true;
                }
            }
        }else if(value instanceof Integer){
            for (Integer s:intValues) {
                if(s==value){
                    return true;
                }
            }
        }
        return false;

    }
}


