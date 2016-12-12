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

import static org.blocorganization.blocapp.utils.CreateUtilities.REPEAT_EVENT;
import static org.blocorganization.blocapp.utils.CreateUtilities.REPEAT_OPTIONS;
import static org.blocorganization.blocapp.utils.CreateUtilities.RES;
import static org.blocorganization.blocapp.utils.CreateUtilities.TYPE;
import static org.blocorganization.blocapp.utils.CreateUtilities.TYPES;
import static org.blocorganization.blocapp.utils.CreateUtilities.VENUE;
import static org.blocorganization.blocapp.utils.CreateUtilities.VENUES;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateMeetupFragment extends Fragment
        implements DialogSubmitUtilities.DialogSubmitListener,
        CreateDialog.ParentDialogSubmitListener {


    private EditText etTitle;
    private EditText etAdmin;
    private EditText etDescription;

    private FrameLayout venueLayout;
    private FrameLayout typeLayout;
    private FrameLayout repeatEventLayout;
    private Spinner spVenue;
    private Spinner spType;
    private Spinner spRepeatEvent;
    private CreateUtilities typeUtilities;
    private CreateUtilities venueUtilities;
    private CreateUtilities repeatEventUtilities;

    private DatabaseReference databaseResources;

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

        assignFields(rootView);
        setupUtilities(rootView);
        setupSpinnersFrom(databaseResources);

        return rootView;
    }

    private void assignFields(View rootView) {
        etTitle = (EditText) rootView.findViewById(R.id.etTitle);
        etAdmin = (EditText) rootView.findViewById(R.id.etAdmin);
        etDescription = (EditText) rootView.findViewById(R.id.etDescription);

        venueLayout = (FrameLayout) rootView.findViewById(R.id.containerVenue);
        typeLayout = (FrameLayout) rootView.findViewById(R.id.containerType);
        repeatEventLayout = (FrameLayout) rootView.findViewById(R.id.containerEventRepeat);
        spVenue = (Spinner) venueLayout.findViewById(R.id.spinner);
        spType = (Spinner) typeLayout.findViewById(R.id.spinner);
        spRepeatEvent = (Spinner) repeatEventLayout.findViewById(R.id.spinner);

        databaseResources = FirebaseDatabase.getInstance().getReference().child(RES);
    }

    private void setupUtilities(View rootView) {
        typeUtilities = new CreateUtilities(getActivity());
        venueUtilities = new CreateUtilities(getActivity());
        repeatEventUtilities = new CreateUtilities(getActivity());
    }

    private void setupSpinnersFrom(DatabaseReference mDatabaseResources) {
        DatabaseReference dbVenues = mDatabaseResources.child(VENUES);
        DatabaseReference dbTypes = mDatabaseResources.child(TYPES);
        DatabaseReference dbRepeatOptions = mDatabaseResources.child(REPEAT_OPTIONS);

        venueUtilities.getSpinnerListItemsFrom(dbVenues, spVenue, VENUE);
        typeUtilities.getSpinnerListItemsFrom(dbTypes, spType, TYPE);
        repeatEventUtilities.getSpinnerListItemsFrom(dbRepeatOptions, spRepeatEvent, REPEAT_EVENT);
    }

    @Override
    public boolean verifyFields() {
        return true;
    }

    @Override
    public void onConfirmSave() {

    }
}
