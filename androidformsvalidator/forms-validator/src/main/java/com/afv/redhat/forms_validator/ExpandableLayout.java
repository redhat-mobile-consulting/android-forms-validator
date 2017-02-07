package com.afv.redhat.forms_validator;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by kxiang on 16/09/16.
 *
 * ExpandableLayout provides a customised layout for its children.
 */
public class ExpandableLayout extends LinearLayout implements View.OnClickListener {

    //Display attributes
    protected TextView mHead;
    protected ViewGroup mContent;
    protected boolean expanded=true;
    protected int defaultHeadColor;
    private Animation curAnim;
    private int mContentHeight;

    /**
     * Constructor for ExpandableLayout with a specified context.
     *
     * @param context Context
     */
    public ExpandableLayout(Context context) {
        super(context);
    }

    /**
     * Constructor for ExpandableLayout with a specified context
     * and set of attributes. Sets layout orientation to vertical.
     *
     * @param context   Context
     * @param attrs     Set of attributes
     */
    public ExpandableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    /**
     * Method finalizes inflating a view from XML.
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHead=(TextView)findViewWithTag("head");
        mContent= (ViewGroup) findViewWithTag("content");
        mContentHeight=mContent.getMeasuredHeight();
        mHead.setOnClickListener(this);
        defaultHeadColor=mHead.getCurrentTextColor();
    }

    /**
     * Method triggers a render of unexpanded accordion on click event.
     *
     * @param v View
     */
    @Override
    public void onClick(View v) {
        if (v == mHead){
            expanded=!expanded;
            renderAccordion();
        }
    }

    /**
     * Method triggers a render of expanded accordion.
     */
    public void expand(){
        expanded=true;
        renderAccordion();
    }

    /**
     * Method triggers a render of collapsed/unexpanded accordion.
     */
    public void collapse(){
        expanded=false;
        renderAccordion();
    }

    /**
     * Method triggers a render of unexpanded accordion.
     */
    public void toggle(){
        expanded=!expanded;
        renderAccordion();
    }

    /**
     * Method renders accordion.
     */
    private void renderAccordion() {
        if (curAnim!=null){
            mContent.clearAnimation();
        }
        if (expanded){
//            addView(mContent);
            mContent.setVisibility(VISIBLE);
            mContent.getLayoutParams().height=0;
            curAnim=new HeightAnimation(mContent,mContentHeight);
            curAnim.setDuration(500);
            curAnim.setAnimationListener(new Animation.AnimationListener() {

                /**
                 * Method notifies the start of the animation associated with this view.
                 *
                 * @param animation Animation
                 */
                @Override
                public void onAnimationStart(Animation animation) {

                }

                /**
                 * Method notifies the end of the animation associated with this view.
                 *
                 * @param animation Animation
                 */
                @Override
                public void onAnimationEnd(Animation animation) {
                    curAnim=null;
//                   if  (mContent.getLayoutParams().height= ViewGroup.LayoutParams.WRAP_CONTENT;
                }

                /**
                 * Method notifies the repeat of the animation associated with this view.
                 *
                 * @param animation Animation
                 */
                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mContent.startAnimation(curAnim);
//            mHead.setTextColor(defaultHeadColor);
            mHead.setCompoundDrawablesWithIntrinsicBounds(0,0, R.mipmap.expand_folded,0);
        }else{
//            removeView(mContent);
//            mContent.setVisibility(GONE);
            if (mContentHeight==0){
                mContentHeight=mContent.getMeasuredHeight();
            }

            curAnim=new HeightAnimation(mContent,0);
            curAnim.setAnimationListener(new Animation.AnimationListener() {

                /**
                 * Method notifies the start of the animation associated with this view.
                 *
                 * @param animation Animation
                 */
                @Override
                public void onAnimationStart(Animation animation) {

                }

                /**
                 * Method notifies the end of the animation associated with this view.
                 *
                 * @param animation Animation
                 */
                @Override
                public void onAnimationEnd(Animation animation) {
//                    removeView(mContent);
                    if (mContent.getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT){
                        mContent.setVisibility(GONE);
                        curAnim=null;
                    }

                }

                /**
                 * Method notifies the repeat of the animation associated with this view.
                 *
                 * @param animation Animation
                 */
                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            curAnim.setDuration(500);
            mContent.startAnimation(curAnim);
//            mHead.setTextColor(ResourcesCompat.getColor(getResources(),R.color.colorPrimary,getContext().getTheme()));
            mHead.setCompoundDrawablesWithIntrinsicBounds(0,0,R.mipmap.expand_collapsed,0);
            View v= ((Activity)getContext()).getCurrentFocus();
            if (v!=null) {
                v.clearFocus();
            }
            InputMethodManager inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * HeightAnimation class.
     */
    public static class HeightAnimation extends Animation {

        //Display attributes
        View mView;
        int mTargetHeight;
        int mCurHeight;

        /**
         * Constructor for HeightAnimation class.
         *
         * @param v             View
         * @param targetHeight  Target Height
         */
        public HeightAnimation(View v, int targetHeight){
            mView=v;
            mTargetHeight=targetHeight;
            mCurHeight=v.getMeasuredHeight();
        }

        /**
         * Helper for getTransformation.
         *
         * @param interpolatedTime  The value of the normalized time (0.0 to 1.0) after it has been run through the interpolation function.
         * @param t                 The Transformation object to fill in with the current transforms.
         */
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            ViewGroup.LayoutParams p= mView.getLayoutParams();
            int diff=mTargetHeight-mCurHeight;
            p.height=interpolatedTime<1?(int)(diff*interpolatedTime+mCurHeight): ViewGroup.LayoutParams.WRAP_CONTENT;
            mView.setLayoutParams(p);
            mView.requestLayout();
        }

        /**
         * Indicates whether or not this animation will affect the bounds of the animated view.
         *
         * @return true
         */
        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }
}
