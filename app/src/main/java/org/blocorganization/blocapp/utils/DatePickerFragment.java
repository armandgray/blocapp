package org.blocorganization.blocapp.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import org.joda.time.DateTime;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private DateSetListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (DateSetListener) getParentFragment();
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement DateSetListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current fromDate as the default fromDate in the picker
        DateTime dt = new DateTime();
        int year = dt.getYear();
        int month = dt.getMonthOfYear() - 1;
        int day = dt.getDayOfMonth();

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        mListener.onUserCancel();
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        mListener.onEventDateSet(view, year, month + 1, day);
    }

    public interface DateSetListener {
        void onEventDateSet(DatePicker view, int year, int month, int day);
        void onUserCancel();
    }
}
