package com.parkgo.parkgorithm;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;


public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private int selectedHour = -1;
    private int selectedMinute = -1;

    OnTimeSelectedListener mCallback;

    // Container Activity must implement this interface
    public interface OnTimeSelectedListener {
        public void onTimeSelected(int h, int m);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        selectedHour = c.get(Calendar.HOUR_OF_DAY);
        selectedMinute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, selectedHour, selectedMinute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnTimeSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTimeSelectedListener");
        }
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//        listActivity = (ListActivity) getActivity();
//        listActivity.setHour(hourOfDay);
//        listActivity.setMinute(minute);
        mCallback.onTimeSelected(hourOfDay, minute);
        selectedHour = hourOfDay;
        selectedMinute = minute;
    }
}