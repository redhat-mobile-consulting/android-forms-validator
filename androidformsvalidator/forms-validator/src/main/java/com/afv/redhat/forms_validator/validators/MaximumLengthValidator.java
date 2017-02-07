package com.afv.redhat.forms_validator.validators;

import android.content.Context;

/**
 * Created by kxiang on 07/09/16.
 *
 * MaximumLengthValidator class.
 */
public class MaximumLengthValidator extends EditTextValidator {

    private int len;    //Maximum length

    /**
     * Constructor for MaximumLengthValidator class with specified context and maximum text length.
     *
     * @param c Context
     * @param l Maximum Text Length
     */
    public MaximumLengthValidator(Context c, int l) {
        super(c);
        len=l;
    }

    /**
     * Method validates and returns valid text.
     *
     * @param txt Text
     * @return valid text
     */
    @Override
    public boolean validate(CharSequence txt) {
        return txt.length()<=len;
    }

    /**
     * Method returns an error message.
     *
     * @param name Error Name
     * @return Error message
     */
    @Override
    public String getErrorMessage(String name) {
        return String.format("%s should contain at most %d characters.",name,len);
    }
}
