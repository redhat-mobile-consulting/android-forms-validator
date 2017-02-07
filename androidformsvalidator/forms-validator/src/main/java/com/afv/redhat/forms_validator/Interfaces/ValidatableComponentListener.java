package com.afv.redhat.forms_validator.Interfaces;

import android.view.View;

/**
 * Created by kxiang on 19/09/16.
 *
 * ValidatableComponentListener class.
 */
public interface ValidatableComponentListener {
    void onTextChange(CharSequence text, int start, int lengthBefore, int lengthAfter, View v);
    void onFocusChange(boolean focused, View v);
    void onValiddate(boolean isValid, View v);

}