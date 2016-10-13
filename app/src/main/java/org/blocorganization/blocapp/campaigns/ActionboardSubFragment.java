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
public class ActionboardSubFragment extends Fragment {


    public ActionboardSubFragment() {
        // Required empty public constructor
    }

    public static ActionboardSubFragment newInstance() {

        Bundle args = new Bundle();

        ActionboardSubFragment fragment = new ActionboardSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.campaigns_subfragment_actionboard, container, false);
    }

}
