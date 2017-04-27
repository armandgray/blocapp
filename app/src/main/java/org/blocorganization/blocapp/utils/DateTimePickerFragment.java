package org.blocorganization.blocapp.utils;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import org.joda.time.DateTime;

public class DateTimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener,
        DatePickerFragment.DateSetListener {

    private DateTimeSetListener mListener;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (DateTimeSetListener) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement DateTimeSetListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        new DatePickerFragment()
                .show(getChildFragmentManager(), "datePicker");

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DateTime dt = new DateTime();
        int hour = dt.getHourOfDay();
        int minute = dt.getMinuteOfHour();

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mListener.onEventDateTimeSet(mYear, mMonth, mDay, hourOfDay, minute);
    }

    @Override
    public void onEventDateSet(DatePicker view, int year, int month, int day) {
        mYear = year;
        mMonth = month;
        mDay = day;
    }

    @Override
    public void onDatePickerCancel() {
        mListener.onDatePickerCancel();
    }

    public interface DateTimeSetListener {
        void onEventDateTimeSet(int year, int month, int day, int hourOfDay, int minute);
        void onDatePickerCancel();
    }
}

