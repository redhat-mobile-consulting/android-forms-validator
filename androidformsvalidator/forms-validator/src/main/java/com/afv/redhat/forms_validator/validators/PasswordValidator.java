package com.afv.redhat.forms_validator.validators;

import android.content.Context;

import java.util.regex.Pattern;

/**
 * Created by kxiang on 07/09/16.
 *
 * PasswordValidator class.
 */
public class PasswordValidator extends EditTextValidator {

    private Pattern[] patterns=new Pattern[]{               //Patterns
            Pattern.compile("[a-z]+"),
            Pattern.compile("[A-Z]+"),
            Pattern.compile("[0-9]+"),
            Pattern.compile("[#?!@$%^&*-.()/'\"]+")
    };

    /**
     * Constructor for PasswordValidator class with specified context.
     *
     * @param c Context
     */
    public PasswordValidator(Context c) {
        super(c);
    }

    /**
     * Method validates and returns valid text.
     *
     * @param txt Text
     * @return valid text
     */
    @Override
    public boolean validate(CharSequence txt) {
        int count=0;
        for (int i=0;i<patterns.length;i++){
            Pattern p=patterns[i];
            if (p.matcher(txt).find(0)){
                count++;
            }
        }
        return count>=3;
    }

    /**
     * Method returns an error message.
     *
     * @param name Error Name
     * @return Error message
     */
    @Override
    public String getErrorMessage(String name) {
        return "Password should contain at least three of: a number, a lower case letter, an uppercase letter, a special character.";
    }
}
