package com.afv.redhat.forms_validator.validators;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by kxiang on 07/09/16.
 *
 * EmailValidator class.
 */
public  class EmailValidator extends EditTextValidator {

    /**
     * Constructor for EmailValidator class with specified context.
     *
     * @param c Context
     */
    public EmailValidator(Context c) {
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
        return !TextUtils.isEmpty(txt) && android.util.Patterns.EMAIL_ADDRESS.matcher(txt).matches();
    }

    /**
     * Method returns an error message.
     *
     * @param name Error Name
     * @return Error message
     */
    @Override
    public String getErrorMessage(String name) {
        return "\t\t\t\tPlease enter a valid email address";
    }
};