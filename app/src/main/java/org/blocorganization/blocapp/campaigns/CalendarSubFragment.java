package org.blocorganization.blocapp.campaigns;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.blocorganization.blocapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarSubFragment extends Fragment {

    public CalendarSubFragment() {
        // Required empty public constructor
    }

    public static CalendarSubFragment newInstance() {

        Bundle args = new Bundle();

        CalendarSubFragment fragment = new CalendarSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.campaigns_subfragment_calendar, container, false);

        return rootView;
    }

}
