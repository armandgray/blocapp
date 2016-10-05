package org.blocorganization.blocapp.bloc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.blocorganization.blocapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MissionSubFragment extends Fragment {


    public MissionSubFragment() {
        // Required empty public constructor
    }

    public static MissionSubFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MissionSubFragment fragment = new MissionSubFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.subfragment_mission, container, false);

        return rootView;
    }

}
