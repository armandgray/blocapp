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
public class CreateMessageFragment extends Fragment
        implements DialogSubmitUtilities.DialogSubmitListener,
        CreateDialog.ParentDialogSubmitListener {


    public CreateMessageFragment() {
        // Required empty public constructor
    }

    public static CreateMessageFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CreateMessageFragment fragment = new CreateMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.create_fragment_message, container, false);

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
