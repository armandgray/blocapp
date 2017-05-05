package org.blocorganization.blocapp.utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement DateSetListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DateTime dt = new DateTime();
        int year = dt.getYear();
        int month = dt.getMonthOfYear() - 1;
        int day = dt.getDayOfMonth();

        final DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        datePickerDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                        .setTextColor(Color.parseColor("#000000"));
                datePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                        .setTextColor(Color.parseColor("#000000"));
                datePickerDialog.getButton(DialogInterface.BUTTON_NEUTRAL)
                        .setTextColor(Color.parseColor("#000000"));
            }
        });
        return datePickerDialog;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        mListener.onDatePickerCancel();
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        mListener.onEventDateSet(year, month + 1, day);
    }

    interface DateSetListener {
        void onEventDateSet(int year, int month, int day);
        void onDatePickerCancel();
    }
}
