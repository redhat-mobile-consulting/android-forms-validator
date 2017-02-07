package com.afv.redhat.forms_validator.validators;

import android.content.Context;

/**
 * Created by kxiang on 07/09/16.
 *
 * MinimumLengthValidator class.
 */
public class MinimumLengthValidator extends EditTextValidator{

    private int len;    //Minimum length

    /**
     * Constructor for MinimumLengthValidator class with specified context and minimum text length
     *
     * @param c Context
     * @param l Minimum Text Length
     */
    public MinimumLengthValidator(Context c , int l) {
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
        return txt.length()>=len;
    }

    /**
     * Method returns an error message.
     *
     * @param name Error Name
     * @return Error message
     */
    @Override
    public String getErrorMessage(String name) {
        return String.format("%s should contain at least %d characters.",name,len);
    }
}
