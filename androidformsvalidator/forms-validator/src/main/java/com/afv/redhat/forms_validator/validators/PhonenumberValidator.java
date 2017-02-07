package com.afv.redhat.forms_validator.validators;

import android.content.Context;
import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by kxiang on 07/09/16.
 *
 * PhonenumberValidator class.
 */
public class PhonenumberValidator extends EditTextValidator {

    /**
     * Constructor for PhonenumberValidator class with specified context.
     *
     * @param c Context
     */
    public PhonenumberValidator(Context c) {
        super(c);
    }
//  protected static Pattern IrePhone=Pattern.compile("08[0-9]{8}");
    protected static Pattern IrePhone= Pattern.compile("^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$"); //Regex format matches French phone numbers: 06 01 02 03 04, +33 6 01 02 03 04, 0033 7 01 02 03 04

    /**
     * Method validates and returns valid text.
     *
     * @param txt Text
     * @return valid text
     */
    @Override
    public boolean validate(CharSequence txt) {
        return !TextUtils.isEmpty(txt) && IrePhone.matcher(txt).matches();
    }

    /**
     * Method returns an error message.
     *
     * @param name Error Name
     * @return Error message
     */
    @Override
    public String getErrorMessage(String name) {
        return "\t\t\t\tPhone number should be in 06 01 02 03 04, +33 6 01 02 03 04,\n\t\t\t\t0033 7 01 02 03 04 format.";
    }
}
