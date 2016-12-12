package org.blocorganization.blocapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.blocorganization.blocapp.utils.CreateUtilities;
import org.blocorganization.blocapp.utils.DialogSubmitUtilities;

import static org.blocorganization.blocapp.campaigns.CreateCampaignDialog.RES;
import static org.blocorganization.blocapp.campaigns.CreateCampaignDialog.TYPES;
import static org.blocorganization.blocapp.utils.CreateUtilities.TYPE;
import static org.blocorganization.blocapp.utils.FieldUtilities.TYPE_REQUIRED;
import static org.blocorganization.blocapp.utils.FieldUtilities.alertVerify;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateResourceFragment extends Fragment
        implements DialogSubmitUtilities.DialogSubmitListener,
        CreateDialog.ParentDialogSubmitListener {

    public static final String SUBTYPE = "SUBTYPE";
    public static final String SUBTYPES = "subtypes";
    private EditText etTitle;
    private EditText etAdmin;
    private EditText etDescription;

    private FrameLayout subtypeLayout;
    private FrameLayout typeLayout;
    private Spinner spType;
    private Spinner spSubtype;

    private CreateUtilities typeUtilities;
    private CreateUtilities subtypeUtilities;
    private DatabaseReference databaseResources;

    public CreateResourceFragment() {
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

        assignFields(rootView);
        setupUtilities(rootView);
        setupSpinnersFrom(databaseResources);

        return rootView;
    }

    private void assignFields(View rootView) {
        etTitle = (EditText) rootView.findViewById(R.id.etTitle);
        etAdmin = (EditText) rootView.findViewById(R.id.etAdmin);
        etDescription = (EditText) rootView.findViewById(R.id.etDescription);

        subtypeLayout = (FrameLayout) rootView.findViewById(R.id.containerSubtype);
        typeLayout = (FrameLayout) rootView.findViewById(R.id.containerType);
        spSubtype = (Spinner) subtypeLayout.findViewById(R.id.spinner);
        spType = (Spinner) typeLayout.findViewById(R.id.spinner);

        databaseResources = FirebaseDatabase.getInstance().getReference().child(RES);
    }

    private void setupUtilities(View rootView) {
        typeUtilities = new CreateUtilities(getActivity());
        subtypeUtilities = new CreateUtilities(getActivity());
    }

    private void setupSpinnersFrom(DatabaseReference mDatabaseResources) {
        DatabaseReference dbSubtype = mDatabaseResources.child(SUBTYPES);
        DatabaseReference dbTypes = mDatabaseResources.child(TYPES);

        subtypeUtilities.getSpinnerListItemsFrom(dbSubtype, spSubtype, SUBTYPE);
        typeUtilities.getSpinnerListItemsFrom(dbTypes, spType, TYPE);
    }

    @Override
    public boolean verifyFields() {
        if (spType == null || TYPE_REQUIRED == null || typeUtilities == null) {
            Toast.makeText(getActivity(), "Scary", Toast.LENGTH_SHORT).show();
        } else {
            if (alertVerify(spType, TYPE_REQUIRED, typeUtilities)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onConfirmSave() {
    }
}
