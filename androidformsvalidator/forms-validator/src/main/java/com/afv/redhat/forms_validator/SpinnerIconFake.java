package com.afv.redhat.forms_validator;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by kxiang on 19/09/16.
 *
 * SpinnerIconFake class performs operations on spinner.
 */
public class SpinnerIconFake extends EditTextIcon {

    /**
     * Constructor for SpinnerIconFake with specified context and set of attributes.
     *
     * @param context   Context
     * @param attrs     Set of attributes
     */
    public SpinnerIconFake(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init();
    }

    /**
     *  Method is called when focus is changed.
     *
     * @param focused               Focused field
     * @param direction             Direction
     * @param previouslyFocusedRect Previously focused field
     */
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindowToken(), 0);
            setKeyListener(null);
        }
    }

    /**
     * Method sets the Drawables to appear to the left of, above, and below the text.
     * Sets transparency for dropdown icon.
     *
     * @param left      Left of the text
     * @param top       Top of the text
     * @param right     Right of the text
     * @param bottom    Bottom of the text
     */
    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        Drawable dropdownIcon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_expand_more_black_18dp,getContext().getTheme());
        dropdownIcon.mutate().setAlpha(128);
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, dropdownIcon, bottom);
    }

}
