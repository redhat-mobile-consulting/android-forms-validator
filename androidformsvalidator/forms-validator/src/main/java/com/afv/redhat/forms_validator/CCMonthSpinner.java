package com.afv.redhat.forms_validator;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;

/**
 * Created by kxiang on 03/10/16.
 *
 * CCMonthSpinner class performs operations on spinner.
 */
public class CCMonthSpinner extends SpinnerIcon {
    private final int monthNumber=12;               //Number of months

    String[] monString=new String[monthNumber];     //Month conversion

    /**
     * Constructor for CCMonthSpinner class with specified context.
     *
     * @param context Context
     */
    public CCMonthSpinner(Context context) {
        super(context);
    }

    /**
     * Constructor for CCMonthSpinner with specified context and set of attributes.
     *
     * @param context Context
     * @param attrs   Set of attributes
     */
    public CCMonthSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        for (int i=0;i<monthNumber;i++){
            monString[i]=new SimpleDateFormat("MMM").format(new GregorianCalendar(2000,i,1).getTime());
        }
        ArrayAdapter<String> monthAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,monString);
        setAdapter(monthAdapter);
    }

    /**
     * Method returns the month in integer(start from 0) -1 if no  month being chosen.
     *
     * @return the month in integer(start from 0) -1 if no  month being chosen
     */
    public int getValue(){
        String txt=getText().toString();
        return txt.isEmpty()?-1: Arrays.asList(monString).indexOf(txt);
    }
}
