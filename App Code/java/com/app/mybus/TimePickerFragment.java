package com.app.mybus;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

import com.app.mybus.R;

import java.util.Calendar;

/**
 * Created by Shriom on 23-04-2016.
 */
public class TimePickerFragment extends DialogFragment
         {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), (AdminActivity)getActivity(), hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }


}
