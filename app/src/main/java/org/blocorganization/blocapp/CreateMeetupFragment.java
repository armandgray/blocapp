package org.blocorganization.blocapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.blocorganization.blocapp.utils.DialogSubmitUtilities;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateMeetupFragment extends Fragment
        implements DialogSubmitUtilities.DialogSubmitListener,
        CreateDialog.ParentDialogSubmitListener {


    public CreateMeetupFragment() {
        // Required empty public constructor
    }

    public static CreateMeetupFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CreateMeetupFragment fragment = new CreateMeetupFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_fragment_meetup, container, false);

        return rootView;
    }

    @Override
    public boolean verifyFields() {
        return true;
    }

    @Override
    public void onConfirmSave() {

    }
}
