package com.afv.redhat.forms_validator.validators;

import android.content.Context;

/**
 * Created by kxiang on 07/09/16.
 *
 * RequireValidator class.
 */
public  class RequireValidator extends EditTextValidator {

    /**
     * Constructor for RequireValidator class with specified context.
     *
     * @param c Context
     */
    public RequireValidator(Context c) {
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
        return txt.length()>0;
    }

    /**
     * Method returns an error message.
     *
     * @param name Error Name
     * @return Error message
     */
    @Override
    public String getErrorMessage(String name) {
        return String.format("\t\t\t\tPlease provide %s.",name);
    }


}
