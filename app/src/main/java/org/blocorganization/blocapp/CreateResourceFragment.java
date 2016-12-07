package org.blocorganization.blocapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateResourceFragment extends Fragment {


    public CreateResourceFragment() {
        // Required empty public constructor
    }

    public static CreateResourceFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CreateResourceFragment fragment = new CreateResourceFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.create_fragment_resource, container, false);

        return rootView;
    }

}
