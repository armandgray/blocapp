package org.blocorganization.blocapp.campaigns;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Campaign;
import org.blocorganization.blocapp.utils.ConfirmChangesDialogFragment;
import org.blocorganization.blocapp.utils.DateTimePickerFragment;
import org.blocorganization.blocapp.utils.DateTimePresenter;
import org.blocorganization.blocapp.utils.ImageThemeAdapter;
import org.blocorganization.blocapp.utils.RecyclerItemClickListener;
import org.blocorganization.blocapp.utils.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.blocorganization.blocapp.campaigns.CreateCampaignDialogUtilities.saveCampaignToDatabaseWith;
import static org.blocorganization.blocapp.campaigns.CreateCampaignDialogUtilities.startDetailActivityWith;
import static org.blocorganization.blocapp.campaigns.UploadButtonIncluder.setupUploadButtonFrom;
import static org.blocorganization.blocapp.utils.DateTimePresenter.DATE_TIME_PICKER;
import static org.blocorganization.blocapp.utils.FieldUtilities.AMBITION;
import static org.blocorganization.blocapp.utils.FieldUtilities.BENEFITS_TO_THE_COLLEGE;
import static org.blocorganization.blocapp.utils.FieldUtilities.DESCRIPTION;
import static org.blocorganization.blocapp.utils.FieldUtilities.getTextFrom;
import static org.blocorganization.blocapp.utils.FieldUtilities.loadUrlIntoImageViewWithActivity;
import static org.blocorganization.blocapp.utils.FieldUtilities.setSelectionForSpinnerFromList;
import static org.blocorganization.blocapp.utils.FieldUtilities.setTextForEditTextAndPrepend;
import static org.blocorganization.blocapp.utils.FieldUtilities.setTextForEditTextWith;
import static org.blocorganization.blocapp.utils.FieldUtilities.verify;

public class CreateCampaignDialog extends DialogFragment
        implements DateTimePickerFragment.DateTimeSetListener,
        ConfirmChangesDialogFragment.ConfirmChangesListener {

    public static final int THEME_LAYOUT_PARAMS = 100;
    public static final String THEMES = "themes";
    public static final String IMAGEURLS = "imageurls";
    public static final String RES = "res";
    public static final String VENUES = "venues";
    public static final String TYPES = "types";
    public static final String DIALOG = "DIALOG";
    public static final String DATE = "Date";

    private Campaign campaign;

    private ImageThemeAdapter adapter;
    private List<String> themes = new ArrayList<>();
    private List<String> venues = new ArrayList<>();
    private List<String> types = new ArrayList<>();

    // upload media fields
    private ImageView ivUpload;

    // fields references
    private Integer themePosition;
    private EditText etTitle;
    private EditText etAbbreviation;
    private EditText etAdmin;
    private EditText etDescription;
    private EditText etAmbition;
    private EditText etBenefits;
    private Spinner spType;
    private Spinner spVenue;
    private RecyclerView rvThemes;

    LinearLayout previousSelectedTheme;
    private boolean isNewCampaign = true;
    private DateTimePresenter dateTimePresenter;

    public static CreateCampaignDialog withCampaign(Campaign passedCampaign) {
        CreateCampaignDialog fragment = new CreateCampaignDialog();
        fragment.setArguments(passedCampaign.toBundle());
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        final View rootView = inflater.inflate(R.layout.create_campaign_dialog, container, false);

        getPassedCampaign();

        DatabaseReference mDatabaseResources = FirebaseDatabase.getInstance().getReference().child(RES);
        DatabaseReference dbThemes = mDatabaseResources.child(IMAGEURLS).child(THEMES);

        // Load Recycler with jpg from Firebase
        rvThemes = (RecyclerView) rootView.findViewById(R.id.rvThemes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvThemes.setLayoutManager(layoutManager);
        rvThemes.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        highlightView((LinearLayout) view, position);
                    }
                }));

        dbThemes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                themes = (List) dataSnapshot.getValue();
                if (rvThemes.getAdapter() == null) {
                    if (campaign.getThemeImageUrl() != null && !campaign.getThemeImageUrl().equals("")) {
                        adapter = new ImageThemeAdapter(getActivity(),
                                themes, THEME_LAYOUT_PARAMS, campaign.getThemeImageUrl());
                    } else {
                        adapter = new ImageThemeAdapter(getActivity(),
                                themes, THEME_LAYOUT_PARAMS, null);
                    }
                    rvThemes.setAdapter(adapter);
                    loadCampaignData();
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        spVenue = (Spinner) rootView.findViewById(R.id.spVenue);

        // get venues from Firebase
        DatabaseReference dbVenues = mDatabaseResources.child(VENUES);
        dbVenues.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                venues = (List) dataSnapshot.getValue();
                if (spVenue.getAdapter() == null) {
                    SpinnerAdapter adVenues = new SpinnerAdapter(venues, getActivity());
                    spVenue.setAdapter(adVenues);
                    loadCampaignData();
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        spType = (Spinner) rootView.findViewById(R.id.spType);

        // get types from Firebase
        DatabaseReference dbTypes = mDatabaseResources.child(TYPES);
        dbTypes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                types = (List) dataSnapshot.getValue();
                if (spType.getAdapter() == null) {
                    SpinnerAdapter adType = new SpinnerAdapter(types, getActivity());
                    spType.setAdapter(adType);
                    loadCampaignData();
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        // handle check btn clicks
        ImageView ivSubmit = (ImageView) rootView.findViewById(R.id.ivSubmit);
        ivSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // field validation before confirming changes
                if (fieldVerification()) {
                    new ConfirmChangesDialogFragment().show(
                            getChildFragmentManager(), DIALOG);
                }
            }
        });

        ImageView ivCancel = (ImageView) rootView.findViewById(R.id.ivCancel);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments() == null) {
                    getActivity().onBackPressed();
                }
                getActivity().onBackPressed();
            }
        });

        dateTimePresenter = new DateTimePresenter(rootView, this);
        dateTimePresenter.loadDateFields(campaign);

        setupUploadButtonFrom(rootView, this);
        ivUpload = (ImageView) rootView.findViewById(R.id.ivUpload);

        assignEditTextFields(rootView);

        return rootView;
    }

    private void getPassedCampaign() {
        campaign = new Campaign(getArguments());
        if (getArguments() == null) {
            campaign.setTimestamp();
            isNewCampaign = false;
        }
    }

    private void highlightView(LinearLayout view, int position) {
        if (previousSelectedTheme != null) {
            previousSelectedTheme.setBackgroundResource(R.drawable.background_square_shadow);
            ImageView lastImg = (ImageView) previousSelectedTheme.getChildAt(0);
            lastImg.setColorFilter(Color.parseColor("#59000000"));
            lastImg.setBackgroundColor(Color.parseColor("#59000000"));
        }
        view.setBackgroundResource(R.drawable.background_square_selected_shadow);
        ImageView img = (ImageView) view.getChildAt(0);
        img.setColorFilter(Color.parseColor("#00000000"));
        img.setBackgroundColor(Color.parseColor("#00000000"));
        themePosition = position;
        previousSelectedTheme = view;
    }

    private boolean fieldVerification() {
        boolean areFieldsNonEmpty = true;
        if (themePosition != null) {
            campaign.setThemeImageUrl(themes.get(themePosition));
        } else {
            areFieldsNonEmpty = false;
            Toast.makeText(getActivity(), "Theme is required", Toast.LENGTH_SHORT).show();
        }
        if (spType.getSelectedItemPosition() != 0) {
            campaign.setRecordType(types.get(spType.getSelectedItemPosition()));
        } else {
            areFieldsNonEmpty = false;
            Toast.makeText(getActivity(), "Type is required", Toast.LENGTH_SHORT).show();
        }
        if (spVenue.getSelectedItemPosition() != 0) {
            campaign.setVenue(venues.get(spVenue.getSelectedItemPosition()));
        } else {
            areFieldsNonEmpty = false;
            Toast.makeText(getActivity(), "Venue is required", Toast.LENGTH_SHORT).show();
        }
        if (dateTimePresenter.getFromDate().equals("") || dateTimePresenter.getFromDate() == null || dateTimePresenter.getFromDate().equals(DATE)) {
            areFieldsNonEmpty = false;
            Toast.makeText(getActivity(), "Date is required", Toast.LENGTH_SHORT).show();
        }
        if (!etTitle.getText().toString().equals("") && etTitle.getText() != null) {
            campaign.setTitle(etTitle.getText().toString());
        } else {
            areFieldsNonEmpty = false;
            Toast.makeText(getActivity(), "Title is required", Toast.LENGTH_SHORT).show();
        }
        if (verify(etAbbreviation)) {
            campaign.setAbbreviation(getTextFrom(etAbbreviation));
        }
        if (!etAdmin.getText().toString().equals("") && etAdmin.getText() != null) {
            campaign.setAdmin(etAdmin.getText().toString());
        } else {
            areFieldsNonEmpty = false;
            Toast.makeText(getActivity(), "Admin is required", Toast.LENGTH_SHORT).show();
        }
        if (!etDescription.getText().toString().equals("") && etDescription.getText() != null) {
            StringBuffer buffer = new StringBuffer(etDescription.getText().toString());
            campaign.setDescription(buffer.replace(0, DESCRIPTION.length(), "").toString());
        } else {
            areFieldsNonEmpty = false;
            Toast.makeText(getActivity(), "Description is required", Toast.LENGTH_SHORT).show();
        }
        if (!etAmbition.getText().toString().equals("") && etAmbition.getText() != null) {
            StringBuffer buffer = new StringBuffer(etAmbition.getText().toString());
            campaign.setAmbition(buffer.replace(0, AMBITION.length(), "").toString());
        }
        if (!etBenefits.getText().toString().equals("") && etBenefits.getText() != null) {
            StringBuffer buffer = new StringBuffer(etBenefits.getText().toString());
            campaign.setBenefits(buffer.replace(0, BENEFITS_TO_THE_COLLEGE.length(), "").toString());
        }
        return areFieldsNonEmpty;
    }

    @Override
    public void onDatePickerCancel() {
        DialogFragment dialog = (DialogFragment) getChildFragmentManager()
                .findFragmentByTag(DATE_TIME_PICKER);
        dialog.dismiss();
    }

    @Override
    public void onEventDateTimeSet(int year, int month, int day, int hourOfDay, String minute) {
        dateTimePresenter.updateDateFields(campaign, year, month, day, hourOfDay, minute);

    }

    private void loadCampaignData() {
        if (getArguments() != null) {
            setSelectionForSpinnerFromList(types, campaign.getRecordType(), spType);
            setSelectionForSpinnerFromList(venues, campaign.getVenue(), spVenue);
            setTextForEditTextWith(campaign.getTitle(), etTitle);
            setTextForEditTextWith(campaign.getAbbreviation(), etAbbreviation);
            setTextForEditTextWith(campaign.getAdmin(), etAdmin);
            setTextForEditTextAndPrepend(DESCRIPTION, campaign.getDescription(), etDescription);
            setTextForEditTextAndPrepend(AMBITION, campaign.getAmbition(), etAmbition);
            setTextForEditTextAndPrepend(BENEFITS_TO_THE_COLLEGE, campaign.getBenefits(), etBenefits);
            loadUrlIntoImageViewWithActivity(campaign.getPhotoUrl(), ivUpload, getActivity());
        }
    }

    private void assignEditTextFields(View rootView) {
        etTitle = (EditText) rootView.findViewById(R.id.etTitle);
        etAbbreviation = (EditText) rootView.findViewById(R.id.etAbbreviation);
        etAbbreviation.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(3)});
        etAdmin = (EditText) rootView.findViewById(R.id.etAdmin);
        etDescription = (EditText) rootView.findViewById(R.id.etDescription);
        etAmbition = (EditText) rootView.findViewById(R.id.etAmbition);
        etBenefits = (EditText) rootView.findViewById(R.id.etBenefits);
    }

    @Override
    public void onConfirmSave() {
        saveCampaignToDatabaseWith(getActivity(), campaign);
        startDetailActivityWith(getActivity(), campaign, isNewCampaign);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        UploadActivityListener resultListener = new UploadActivityListener(getActivity());
        resultListener.onActivityResult(requestCode, resultCode, data, campaign, ivUpload);
    }

}
