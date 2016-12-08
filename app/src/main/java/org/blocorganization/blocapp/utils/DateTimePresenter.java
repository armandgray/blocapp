package org.blocorganization.blocapp.utils;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DateTimePresenter {

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
    }


}
