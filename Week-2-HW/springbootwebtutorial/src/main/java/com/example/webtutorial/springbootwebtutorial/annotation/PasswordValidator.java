package com.example.webtutorial.springbootwebtutorial.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null) return false;
        if(value.length()<10) return false;
        int uc=0,lc=0,sp=0;
        for(int i=0;i<value.length();i++){
            if(value.charAt(i) <=90 && value.charAt(i)>=65){
                uc++;
            }
            else if(value.charAt(i)>=97 && value.charAt(i)<=122){
                lc++;
            }
            else if(!(value.charAt(i)>=48 && value.charAt(i)<=57)) {
                sp++;
            }
        }
        if(uc!=0 && lc!=0 && sp!=0){
            return true;
        }
        return false;
    }
}
