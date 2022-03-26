/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notebank.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Uche Powers
 */
public class ValidatorMessage {

    private String name;
    private String message;
    private String value;

    public ValidatorMessage(String name, String message, String value) {
        this.name = name;
        this.message = message;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static ValidatorMessage newVal(String field, String msg, String value) {
        return new ValidatorMessage(field, msg, value);
    }

    public static List<ValidatorMessage> validate(Object a) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        List<ValidatorMessage> messages = new ArrayList();
        Set<ConstraintViolation<Object>> violations = validator.validate(a);
        for (ConstraintViolation<Object> violation : violations) {
            messages.add(new ValidatorMessage(violation.getPropertyPath().toString(), violation.getMessage(), violation.getInvalidValue() + ""));
            System.out.println(violation.getMessage());
            System.out.println(violation.getPropertyPath().toString());
        }
        return messages;
    }

    public static List<ValidatorMessage> validate(List<Object> obj) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        List<ValidatorMessage> messages = new ArrayList();
        for (Object a : obj) {
            Set<ConstraintViolation<Object>> violations = validator.validate(a);
            for (ConstraintViolation<Object> violation : violations) {
                messages.add(new ValidatorMessage(violation.getPropertyPath().toString(), violation.getMessage(), violation.getInvalidValue() + ""));
                System.out.println(violation.getMessage());
                System.out.println(violation.getPropertyPath().toString());
            }
        }
        return messages;
    }

    public static boolean enumCheckIfExist(Enum[] enums, String value) {
        //T result = null;
        for (Enum en : enums) {
            if (en.name().equals(value)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

}
