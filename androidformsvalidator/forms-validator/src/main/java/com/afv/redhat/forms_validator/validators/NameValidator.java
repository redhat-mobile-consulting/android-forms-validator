package com.afv.redhat.forms_validator.validators;

import android.content.Context;

import java.util.regex.Pattern;

/**
 * Created by kxiang on 07/09/16.
 *
 * NameValidator class.
 */
public class NameValidator extends RegexpValidator {

    /**
     * Constructor for NameValidator with specified context.
     *
     * @param c Context
     */
    public NameValidator(Context c) {
        super(c, null);
        Pattern p= Pattern.compile("^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$");
        pattern = p;
    }

    /**
     * Method returns an error message.
     *
     * @param name Error Name
     * @return Error message
     */
    @Override
    public String getErrorMessage(String name) {
        return String.format("Please enter valid %s",name.toLowerCase());
    }
}
