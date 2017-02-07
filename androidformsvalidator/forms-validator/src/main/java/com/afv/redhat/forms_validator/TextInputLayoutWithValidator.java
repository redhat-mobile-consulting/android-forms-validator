package com.afv.redhat.forms_validator;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.afv.redhat.forms_validator.Interfaces.ValidatableComponent;
import com.afv.redhat.forms_validator.Interfaces.ValidatableComponentListener;
import com.afv.redhat.forms_validator.validators.EditTextValidator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by kxiang on 11/08/16.
 *
 * TextInputLayoutWithValidator class performs validation on text input.
 */
public class TextInputLayoutWithValidator extends TextInputLayout implements ValidatableComponentListener {

    ValidatableComponentListener listener;                          //Listener
    private List<EditTextValidator> validator = new ArrayList<>();  //Validators list
    private ValidatableComponent mEditText;                         //Edit text

    /**
     * Method notifies that, within text, the count characters
     * beginning at start have just replaced old text that had length.
     *
     * @param text          Text Input
     * @param start         Text Input Start
     * @param lengthBefore  Text Length Before Change
     * @param lengthAfter   Text Length After Change
     * @param v             View
     */
    @Override
    public void onTextChange(CharSequence text, int start, int lengthBefore, int lengthAfter, View v) {
        validate();
        if (listener != null) {
            listener.onTextChange(text, start, lengthBefore, lengthAfter, this);
        }
    }

    /**
     * Method is called when the focus state of a view has changed.
     *
     * @param focused   Focus state
     * @param v         View
     */
    @Override
    public void onFocusChange(boolean focused, View v) {
        if (listener != null) {
            listener.onFocusChange(focused, this);
        }
        if (!focused) {
            if (mEditText instanceof EditText) {
                EditText mt = (EditText) mEditText;
                Log.v("mEditText", mt.toString());
                mt.setText(mt.getText().toString().trim());
            }
        }
    }

    @Override
    public void onValiddate(boolean isValid, View v) {
    }

    /**
     * Method checks if input is valid and changes UI accordingly.
     *
     * @return is valid or not
     */
    public boolean validate() {
        setError(null);
        setErrorEnabled(false);
        if (validator.size() > 0) {
            Iterator<EditTextValidator> iterator = validator.listIterator();
            while (iterator.hasNext()) {
                EditTextValidator v = iterator.next();
                String text = mEditText.getText().toString();
                if (!v.validate(text)) {
                    setError(v.getErrorMessage(getHint().toString()));
                    break;
                }
            }
            mEditText.renderStatus();
        }
        boolean isValid = getError() == null;
        if (listener != null) {
            listener.onValiddate(isValid, this);

        }
        return isValid;
    }

    /**
     * Method resets validation.
     */
    public void resetValidation() {
        setError(null);
        setErrorEnabled(false);
    }

    /**
     * Method checks if input is valid without changing UI.
     *
     * @return is valid or not
     */
    public boolean isValid() {
        if (validator.size() > 0) {
            Iterator<EditTextValidator> iterator = validator.listIterator();
            while (iterator.hasNext()) {
                EditTextValidator v = iterator.next();
                String text = mEditText.getText().toString();
                if (!v.validate(text)) {
                    return false;

                }
            }
        }
        return true;
    }

    /**
     * Method checks if spinner selection is valid and changes UI accordingly.
     *
     * @return is valid or not
     */
    public boolean validateSpinner() {
        setError(null);
        setErrorEnabled(false);
        if(mEditText.getText().length() > 0) {
            renderIcon();
            return true;
        } else {
            renderIcon();
            setError("\t\t\t\tPlease provide Civilité *");
            return false;
        }
    }

    /**
     * Constructor with specified context and set of attributes.
     *
     * @param context   Context
     * @param attrs     Set of attributes
     */
    public TextInputLayoutWithValidator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Method triggers a render of an icon to correct state.
     */
    public void renderIcon() {
        mEditText.renderStatus();
    }

    /**
     * Method triggers a render of an icon to correct state.
     *
     * @param status Status (inactive, active, complete or error)
     */
    public void renderIcon(ValidatableComponent.Status status) {
        mEditText.renderStatus(status);
    }

    /**
     * Method finalizes inflating a view from XML and sets a listener on click.
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mEditText = (ValidatableComponent) getEditText();
        mEditText.setValidatableComponentListener(this);
        ((View) mEditText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performClick();
            }
        });
    }

    /**
     * Method adds validator to the edit text field.
     *
     * @param v View
     */
    public void addValidator(EditTextValidator v) {
        validator.add(v);
    }

    /**
     * Method sets validation component listener.
     *
     * @param l ValidatableComponentListener
     */
    public void setValidatableComponentListener(ValidatableComponentListener l) {

        listener = l;

    }

    /**
     * Method adds validators.
     *
     * @param list List of edit text validators
     */
    public void addValidators(List<EditTextValidator> list) {
        Iterator<EditTextValidator> iterator = list.iterator();
        while (iterator.hasNext()) {
            addValidator(iterator.next());
        }
    }


}
