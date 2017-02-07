package com.afv.redhat.forms_validator.validators;

import android.content.Context;

import java.util.regex.Pattern;

/**
 * Created by kxiang on 07/09/16.
 *
 * RegexpValidator class.
 */
public class RegexpValidator extends EditTextValidator {

    protected Pattern pattern;                          //Regex Validation Pattern

    /**
     * Constructor for RegexpValidator class with specified context and pattern.
     *
     * @param c Context
     * @param p Regex Validation Pattern
     */
    public RegexpValidator(Context c, Pattern p) {
        super(c);
        pattern=p;
    }

    /**
     * Method validates and returns valid text.
     *
     * @param txt Text
     * @return valid text
     */
    @Override
    public boolean validate(CharSequence txt) {
        return pattern.matcher(txt.toString()).matches();
    }

    /**
     * Method returns an error message.
     *
     * @param name Error Name
     * @return Error message
     */
    @Override
    public String getErrorMessage(String name) {
        return String.format("%s is not valid",name);
    }
}
