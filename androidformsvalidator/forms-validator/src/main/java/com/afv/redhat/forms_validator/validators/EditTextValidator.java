package com.afv.redhat.forms_validator.validators;

import android.content.Context;

/**
 * Created by kxiang on 07/09/16.
 *
 * EditTextValidator class.
 */
public abstract class EditTextValidator{
    private final Context ctx;
    public abstract boolean validate(CharSequence txt);
    public abstract String getErrorMessage(String name);
    public EditTextValidator(Context c){
        ctx=c;
    }
}