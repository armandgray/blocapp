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
public class FamilySubFragment extends Fragment {

    public FamilySubFragment() {
        // Required empty public constructor
    }

    public static FamilySubFragment newInstance() {

        Bundle args = new Bundle();

        FamilySubFragment fragment = new FamilySubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bloc_subfragment_family, container, false);
    }

}
