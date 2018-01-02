package com.app.mybus;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Shriom on 12-05-2016.
 */
public class DOBPickerFragment extends DialogFragment
{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date+1 as the default date in the picker
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        DatePickerDialog d = new DatePickerDialog(getActivity(), (SignupActivity) getActivity(), year, month, day);
        c.set(Calendar.YEAR,1940);
        DatePicker dp=d.getDatePicker();
        dp.setMinDate(c.getTimeInMillis());
        c.set(Calendar.YEAR,year-18);
        dp.setMaxDate(c.getTimeInMillis());
        return d;
    }

}
