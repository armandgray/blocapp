package org.blocorganization.blocapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.blocorganization.blocapp.utils.CreateUtilities;
import org.blocorganization.blocapp.utils.DialogSubmitUtilities;

import static org.blocorganization.blocapp.utils.CreateUtilities.RES;
import static org.blocorganization.blocapp.utils.CreateUtilities.TYPE;
import static org.blocorganization.blocapp.utils.CreateUtilities.TYPES;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateMessageFragment extends Fragment
        implements DialogSubmitUtilities.DialogSubmitListener,
        CreateDialog.ParentDialogSubmitListener {


    private EditText etTitle;
    private EditText etAdmin;
    private EditText etDescription;

    private FrameLayout typeLayout;
    private Spinner spType;
    private CreateUtilities typeUtilities;

    private DatabaseReference databaseResources;

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

        assignFields(rootView);
        typeUtilities = new CreateUtilities(getActivity());
        setupSpinnersFrom(databaseResources);

        return rootView;
    }

    private void assignFields(View rootView) {
        etTitle = (EditText) rootView.findViewById(R.id.etTitle);
        etAdmin = (EditText) rootView.findViewById(R.id.etAdmin);
        etDescription = (EditText) rootView.findViewById(R.id.etDescription);

        typeLayout = (FrameLayout) rootView.findViewById(R.id.containerType);
        spType = (Spinner) typeLayout.findViewById(R.id.spinner);

        databaseResources = FirebaseDatabase.getInstance().getReference().child(RES);
    }

    private void setupSpinnersFrom(DatabaseReference mDatabaseResources) {
        DatabaseReference dbTypes = mDatabaseResources.child(TYPES);
        typeUtilities.getSpinnerListItemsFrom(dbTypes, spType, TYPE);
    }

    @Override
    public boolean verifyFields() {
        return true;
    }

    @Override
    public void onConfirmSave() {

    }
}
