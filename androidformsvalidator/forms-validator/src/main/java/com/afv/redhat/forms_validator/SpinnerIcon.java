package com.afv.redhat.forms_validator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.redhat.androidformsvalidator.Interfaces.ValidatableComponent;
import com.example.redhat.androidformsvalidator.Interfaces.ValidatableComponentListener;
import com.weiwangcn.betterspinner.library.BetterSpinner;

/**
 * Created by kxiang on 19/09/16.
 *
 * SpinnerIcon class performs operations on spinner.
 */
public class SpinnerIcon extends BetterSpinner implements ValidatableComponent {

    private String iconName;                        //Icon name
    @Nullable
    private ValidatableComponentListener mListener; //Listener

    /**
     * Constructor for SpinnerIcon class with specified context.
     *
     * @param context Context
     */
    public SpinnerIcon(Context context) {
        super(context);
    }

    /**
     * Constructor for SpinnerIcon class with specified context and set of attributes.
     *
     * @param context Context
     * @param attrs Set of attributes
     */
    public SpinnerIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.EditTextIcon,0,0);
        try{
            iconName=a.getString(R.styleable.EditTextIcon_iconName);
        }finally{
            a.recycle();

            if (!isInEditMode()){
                init();
            }
        }
    }

    /**
     * Method sets the string value of the TextView to null on onRestoreInstanceState.
     *
     * @param state State
     */
    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        setText(null);
    }

    /**
     *
     * Method sets the size of the padding between the compound drawables and
     * the text, sets an initial icon to inactive, sets OnFocusChangeListener,
     * and the string value of the TextView.
     */
    private void init() {

        this.setCompoundDrawablePadding((int)getResources().getDimension(R.dimen.drawable_padding));
        this.setCompoundDrawablePadding((int)getResources().getDimension(R.dimen.drawable_padding));
        this.setIcon(ValidatableComponent.Status.inactive);
        this.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                renderStatus();

            }
        });
        this.setText("");
    }

    /**
     * Method sets an icon based on its status.
     *
     * @param status Status can be inactive, active, error or complete
     */
    private void setIcon(Status status) {
        Drawable img=getIconByStatus(status);
//        Drawable img= ResourcesCompat.getDrawable(getResources(),resId,null);
        this.setCompoundDrawablesWithIntrinsicBounds(img,null,null,null);
    }

    /**
     * Methods finds icon by its status.
     *
     * @param status Status can be inactive, active, error or complete
     * @return Icon located in mipmap directory based on status appended to its name
     */
    private Drawable getIconByStatus(Status status) {
        String res_name=iconName+"_"+status.name();
        Context ctx=getContext();
        int resId=getResources().getIdentifier(res_name,"mipmap",ctx.getPackageName());
        if (resId ==0){
            Log.v("resId ==0", "inactive");
            return getIconByStatus(Status.inactive);
        }else{
            return ResourcesCompat.getDrawable(getResources(),resId,ctx.getTheme());
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
     * Method checks the status of the form fields and sets a corresponding icon.
     */
    @Override
    public void renderStatus() {

        if (getError() != "") {
            setIcon(Status.error);
            if (hasFocus()) {
                setIcon(Status.active);
            } else if (getText().length() > 0) {
                setIcon(Status.complete);
            }
        } else if (getText().length() > 0) {
            setIcon(Status.complete);
        } else {
            setIcon(Status.inactive);
        }
    }

    /**
     * Method is called when focus is changed.
     *
     * @param focused               Focused field
     * @param direction             Direction
     * @param previouslyFocusedRect Previously focused field
     */
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        mListener.onFocusChange(focused,this);
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
        if (mListener!=null){
            mListener.onTextChange(text,start,lengthBefore,lengthAfter,this);
        }
    }

    /**
     * Listener setter method.
     *
     * @param listener  ValidatableComponentListener
     */
    public void setValidatableComponentListener(ValidatableComponentListener listener) {
        mListener=listener;
    }


}
