package com.afv.redhat.forms_validator;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by kxiang on 16/09/16.
 *
 * EditDatePicker class performs operations on edit text.
 */
public class EditDatePicker extends EditTextIcon implements View.OnTouchListener, DatePickerDialog.OnDateSetListener {
    DatePickerDialog mDate;                                                             //Date picker dialog
    public static SimpleDateFormat datePickerSDF=new SimpleDateFormat("dd/MM/yyyy");    //Date formatter

    /**
     * Constructor for EditDatePicker with specified context and set of attributes.
     *
     * @param context Context
     * @param attrs Set of attributes
     */
    public EditDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);

//        setEnabled(false);
        setFocusable(false);

    }

    /**
     * Method finalizes inflating a view from XML and sets date picker dialog.
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        setOnFocusChangeListener(this);
        setOnTouchListener(this);
        if (!isInEditMode()){
            mDate=new DatePickerDialog(getContext(),this,1980,1,1);
        }

//        setShowSoftInputOnFocus(false);
    }

    /**
     * Method sets a beginning date in a specified format.
     *
     * @param view          The picker associated with the dialog
     * @param year          The selected year
     * @param monthOfYear   the selected month (0-11 for compatibility with MONTH)
     * @param dayOfMonth    The selected day of the month (1-31, depending on month)
     */
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        setText(datePickerSDF.format(new Date(year-1900,monthOfYear,dayOfMonth)));
    }

    /**
     * Method shows date based on touch event.
     *
     * @param v         The view the touch event has been dispatched to
     * @param event     he MotionEvent object containing full information about the event
     * @return date
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP){
            String curChose=getText().toString();
            if (curChose!=null && !curChose.isEmpty()){
                try {
                    Date curDate=datePickerSDF.parse(curChose);
                    setDate(curDate);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            mDate.show();
            return true;
        }

        return false;
    }

    /**
     * Method sets a date.
     *
     * @param d date
     */
    public void setDate(Date d){
        if (mDate!=null){
            Calendar c= Calendar.getInstance();
            c.setTime(d);
            mDate.updateDate(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
        }
    }

//    @Override
//    public void onFocusChange(View v, boolean hasFocus) {
//        if (hasFocus){
//            mDate.show();
//        }
//    }


}
