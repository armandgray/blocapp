package org.blocorganization.blocapp.utils;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.blocorganization.blocapp.R;

public class DateTimePresenter {

    public static final String DATE_TIME_PICKER = "dateTimePicker";

    private View rootView;
    private Fragment fragment;

    private Boolean isRange;
    private boolean editEndDate;
    private RelativeLayout editDateLayout;
    private RelativeLayout editDateFromLayout;
    private RelativeLayout editDateEndLayout;
    private TextView tvToDate;
    private TextView tvFromDate;
    private ImageView ivToDateMenuArrow;

    public DateTimePresenter(View rootView, Fragment fragment) {
        this.rootView = rootView;
        this.fragment = fragment;

        assignDateFields();
        setupDateClickListeners();
    }

    private void assignDateFields() {
        editDateLayout = (RelativeLayout) rootView.findViewById(R.id.editDateLayout);
        editDateFromLayout = (RelativeLayout) rootView.findViewById(R.id.editDateFromLayout);
        editDateEndLayout = (RelativeLayout) rootView.findViewById(R.id.editDateEndLayout);
        tvFromDate = (TextView) rootView.findViewById(R.id.tvFromDate);
        tvToDate = (TextView) rootView.findViewById(R.id.tvToDate);
        ivToDateMenuArrow = (ImageView) rootView.findViewById(R.id.ivMenuArrow);
    }

    private void setupDateClickListeners() {
        editDateEndLayout.setVisibility(View.GONE);
        editDateFromLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DateTimePickerFragment()
                        .show(fragment.getChildFragmentManager(), DATE_TIME_PICKER);
            }
        });

        editDateEndLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DateTimePickerFragment()
                        .show(fragment.getChildFragmentManager(), DATE_TIME_PICKER);
                setupInitialState();
            }
        });
        editDateFromLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEndDateView();
                return isRange = true;
            }
        });
        editDateEndLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return hideEndDateView();
            }
        });
    }

    private void setupInitialState() {
        tvToDate.setTextColor(Color.parseColor("#FFFFFF"));
        ivToDateMenuArrow.setColorFilter(Color.parseColor("#FFFFFF"));
        ivToDateMenuArrow.setImageResource(R.drawable.ic_menu_down_white_48dp);
        isRange = true;
        editEndDate = true;
    }

    private void showEndDateView() {
        editDateEndLayout.setPadding(GetDpMeasurement.getDPI(fragment.getActivity(), 10), GetDpMeasurement.getDPI(fragment.getActivity(), 5), 0, 0);
        editDateEndLayout.setVisibility(View.VISIBLE);

        tvToDate.setTextColor(Color.parseColor("#333333"));
        ivToDateMenuArrow.setImageResource(R.drawable.ic_menu_down_white_48dp);
        ivToDateMenuArrow.setColorFilter(Color.parseColor("#333333"));

        editDateFromLayout.setBackgroundResource(R.drawable.date_divider_background);
        editDateFromLayout.setPadding(GetDpMeasurement.getDPI(fragment.getActivity(), 10), 0, 0, GetDpMeasurement.getDPI(fragment.getActivity(), 5));
    }

    private boolean hideEndDateView() {
        editDateLayout.getChildAt(2).setVisibility(View.GONE);
        editDateLayout.getChildAt(1).setBackgroundResource(R.color.colorPrimaryDark);
        return isRange = false;
    }
}
