package com.afv.redhat.forms_validator;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;

import java.util.Calendar;

/**
 * Created by kxiang on 03/10/16.
 *
 * CCYearSpinner class performs operations on spinner.
 */
public class CCYearSpinner extends SpinnerIcon {

    /**
     * Constructor for CCYearSpinner class with specified context.
     *
     * @param context Context
     */
    public CCYearSpinner(Context context) {
        super(context);
    }

    /**
     * Constructor for CCYearSpinner class with specified context and set of attributes.
     *
     * @param context   Context
     * @param attrs     Set of attributes
     */
    public CCYearSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        int year= Calendar.getInstance().get(Calendar.YEAR);
        String[] yearString=new String[]{
                String.valueOf(year),
                String.valueOf(year+1),
                String.valueOf(year+2),
                String.valueOf(year+3)
        };
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,yearString);
        setAdapter(adapter);
    }

    /**
     * Method returns the current chosen year. return 0 if no year being chosen.
     *
     * @return current chosen year. return 0 if no year being chosen
     */
    public int getValue(){
        String txt=getText().toString();
        return Integer.valueOf(txt.isEmpty()?"0":txt);
    }
}
