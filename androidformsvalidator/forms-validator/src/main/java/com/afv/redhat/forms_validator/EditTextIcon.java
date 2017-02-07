package com.afv.redhat.forms_validator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.ViewParent;
import android.widget.EditText;

import com.afv.redhat.forms_validator.Interfaces.ValidatableComponent;
import com.afv.redhat.forms_validator.Interfaces.ValidatableComponentListener;

/**
 * Created by kxiang on 10/08/16.
 *
 * EditTextIcon class performs operations on edit text.
 */
public class EditTextIcon extends EditText implements ValidatableComponent {

    private String iconName = null;                                             //Icon name
    @Nullable
    private ValidatableComponentListener validatableComponentListener = null;   //Listener

    /**
     * Listener setter method.
     *
     * @param t ValidatableComponentListener
     */
    public void setValidatableComponentListener(ValidatableComponentListener t) {
        validatableComponentListener = t;
    }

    /**
     * Constructor for EditTextIcon class.
     *
     * @param context Context
     * @param attrs Attribute Set
     */
    public EditTextIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.EditTextIcon, 0, 0);
        try {
            iconName = a.getString(R.styleable.EditTextIcon_iconName);
        } finally {
            a.recycle();
            if (!isInEditMode()) {
                init();
            }
        }
    }

    /**
     * Methods finds icon by its status.
     *
     * @param status Status can be inactive, active, error or complete
     * @return Icon located in mipmap directory based on status appended to its name.
     */
    @Nullable
    private Drawable getIconByStatus(Status status) {
        if (isInEditMode()) {
            return null;
        }
        String res_name = iconName + "_" + status.name();
        Context ctx = getContext();
        int resId = getResources().getIdentifier(res_name, "mipmap", ctx.getPackageName());
        if (resId == 0) {
            return getIconByStatus(Status.inactive);
        } else {
            return ResourcesCompat.getDrawable(getResources(), resId, ctx.getTheme());
        }
    }

    /**
     * Method is called when the text is changed.
     *
     * @param text          Text Input
     * @param start         Text Input Start
     * @param lengthBefore  Text Length Before Change
     * @param lengthAfter   Text Length After Change
     */
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (validatableComponentListener != null) {
            validatableComponentListener.onTextChange(text, start, lengthBefore, lengthAfter, this);
        }

    }

    /**
     * Method checks the status of the form fields and sets a corresponding icon.
     */
    @Override
    public void renderStatus() {
        ViewParent v = getParent();
        boolean hasError = getError() != null;
        boolean hasFocus = hasFocus();
        if (v instanceof TextInputLayout) {
            hasError = ((TextInputLayout) v).getError() != null;
        }

        if (hasError) {
            setIcon(Status.error);
        } else {
            if (hasFocus) {
                setIcon(Status.active);
            } else if (getText() != null && getText().length() > 0) {
                setIcon(Status.complete);
            } else {
                setIcon(Status.inactive);
            }
        }
    }

    /**
     * Method sets an icon with a correct status.
     *
     * @param status Status can be inactive, active, error or complete
     */
    @Override
    public void renderStatus(Status status) {
        setIcon(status);
    }

    /**
     * Method sets the size of the padding between the compound drawables and
     * the text and sets an initial icon to inactive.
     */
    private void init() {
        this.setCompoundDrawablePadding((int) getResources().getDimension(R.dimen.drawable_padding));
        this.setIcon(Status.inactive);
    }

    /**
     * Method is called when the focus state of text field has changed.
     *
     * @param focused               Focused field
     * @param direction             Direction
     * @param previouslyFocusedRect Previously focused
     */
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        renderStatus();
        if (validatableComponentListener != null) {
            validatableComponentListener.onFocusChange(focused, this);
        }

    }

    /**
     * Method sets an icon based on its status.
     *
     * @param status Status can be inactive, active, error or complete
     */
    protected void setIcon(Status status) {
        Drawable img = getIconByStatus(status);
//        Drawable img= ResourcesCompat.getDrawable(getResources(),resId,null);
        this.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
    }
}
