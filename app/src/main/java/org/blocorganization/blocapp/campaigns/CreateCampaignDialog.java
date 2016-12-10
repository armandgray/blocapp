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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Campaign;
import org.blocorganization.blocapp.utils.ConfirmChangesDialogFragment;
import org.blocorganization.blocapp.utils.CreateUtilities;
import org.blocorganization.blocapp.utils.DateTimePickerFragment;
import org.blocorganization.blocapp.utils.DateTimePresenter;
import org.blocorganization.blocapp.utils.DialogSubmitUtilities;
import org.blocorganization.blocapp.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static org.blocorganization.blocapp.campaigns.UploadButtonIncluder.setupUploadButtonFrom;
import static org.blocorganization.blocapp.utils.CreateUtilities.TYPE;
import static org.blocorganization.blocapp.utils.CreateUtilities.VENUE;
import static org.blocorganization.blocapp.utils.DateTimePresenter.DATE_TIME_PICKER;
import static org.blocorganization.blocapp.utils.FieldUtilities.alertVerify;
import static org.blocorganization.blocapp.utils.FieldUtilities.getTextFrom;
import static org.blocorganization.blocapp.utils.FieldUtilities.loadUrlIntoImageViewWithActivity;
import static org.blocorganization.blocapp.utils.FieldUtilities.setTextForEditTextWith;
import static org.blocorganization.blocapp.utils.FieldUtilities.verify;

public class CreateCampaignDialog extends DialogFragment
        implements DateTimePickerFragment.DateTimeSetListener,
        ConfirmChangesDialogFragment.ConfirmChangesListener,
        DialogSubmitUtilities.DialogSubmitListener {

    public static final int THEME_LAYOUT_PARAMS = 100;
    public static final String THEMES = "themes";
    public static final String IMAGEURLS = "imageurls";
    public static final String RES = "res";
    public static final String VENUES = "venues";
    public static final String TYPES = "types";
    public static final String ADMIN_REQUIRED = "Admin Required";
    public static final String TITLE_REQUIRED = "Title Required";
    public static final String DESCRIPTION_REQUIRED = "Description Required";
    public static final String THEME_REQUIRED = "Theme Required";
    public static final String TYPE_REQUIRED = "Type Required";
    public static final String VENUE_REQUIRED = "Venue Required";

    private Campaign campaign;
    private boolean isNewCampaign = true;

    private DateTimePresenter dateTimePresenter;
    private CreateUtilities themeUtilities;
    private DatabaseReference databaseResources;

    private List<String> themes = new ArrayList<>();
    private List<String> venues = new ArrayList<>();
    private List<String> types = new ArrayList<>();

    private EditText etTitle;
    private EditText etAbbreviation;
    private EditText etAdmin;
    private EditText etDescription;
    private EditText etAmbition;
    private EditText etBenefits;
    private ImageView ivUpload;

    private Spinner spType;
    private Spinner spVenue;
    private RecyclerView rvThemes;

    private Integer themePosition;
    LinearLayout previousSelectedTheme;
    private CreateUtilities typeUtilities;
    private CreateUtilities venueUtilities;


    public static CreateCampaignDialog withCampaign(Campaign passedCampaign) {
        CreateCampaignDialog fragment = new CreateCampaignDialog();
        fragment.setArguments(passedCampaign.toBundle());
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.create_campaign_dialog, container, false);

        getPassedCampaign();
        assignFields(rootView);
        loadCampaignData();
        setupUtilities(rootView);
        setupRvThemes(rootView);
        setupSpinnersFrom(databaseResources, rootView);
        setupDateTimePresenter(rootView);
        setupUploadButtonFrom(rootView, this);

        return rootView;
    }

    private void getPassedCampaign() {
        if (getArguments() != null) {
            campaign = new Campaign(getArguments());
        } else {
            campaign = new Campaign();
            campaign.setTimestamp();
            isNewCampaign = true;
        }
    }

    private void setupUtilities(View rootView) {
        themeUtilities = new CreateUtilities(campaign, getActivity());
        typeUtilities = new CreateUtilities(campaign, getActivity());
        venueUtilities = new CreateUtilities(campaign, getActivity());

        databaseResources = FirebaseDatabase.getInstance().getReference().child(RES);
        DialogSubmitUtilities submitUtilities = new DialogSubmitUtilities(rootView, this);
        submitUtilities.setupClickListeners(this);
    }

    private void loadCampaignData() {
        if (getArguments() != null) {
            setTextForEditTextWith(campaign.getTitle(), etTitle);
            setTextForEditTextWith(campaign.getAbbreviation(), etAbbreviation);
            setTextForEditTextWith(campaign.getAdmin(), etAdmin);
            setTextForEditTextWith(campaign.getDescription(), etDescription);
            setTextForEditTextWith(campaign.getAmbition(), etAmbition);
            setTextForEditTextWith(campaign.getBenefits(), etBenefits);
            loadUrlIntoImageViewWithActivity(campaign.getPhotoUrl(), ivUpload, getActivity());
        }
    }

    private void setupRvThemes(View rootView) {
        rvThemes = (RecyclerView) rootView.findViewById(R.id.rvThemes);
        DatabaseReference dbThemes = databaseResources.child(IMAGEURLS).child(THEMES);

        themeUtilities.getRvImageUrlListFrom(dbThemes, rvThemes);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvThemes.setLayoutManager(layoutManager);
        rvThemes.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        highlightView((LinearLayout) view, position);
                    }
                }));
    }

    private void setupSpinnersFrom(DatabaseReference mDatabaseResources, View rootView) {
        spVenue = (Spinner) rootView.findViewById(R.id.spVenue);
        spType = (Spinner) rootView.findViewById(R.id.spType);

        DatabaseReference dbVenues = mDatabaseResources.child(VENUES);
        DatabaseReference dbTypes = mDatabaseResources.child(TYPES);

        venueUtilities.getSpinnerListItemsFrom(dbVenues, spVenue, VENUE);
        typeUtilities.getSpinnerListItemsFrom(dbTypes, spType, TYPE);
    }

    private void setupDateTimePresenter(View rootView) {
        dateTimePresenter = new DateTimePresenter(rootView, this);
        dateTimePresenter.loadDateFields(campaign);
    }

    private void assignFields(View rootView) {
        etTitle = (EditText) rootView.findViewById(R.id.etTitle);
        etAbbreviation = (EditText) rootView.findViewById(R.id.etAbbreviation);
        etAbbreviation.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(3)});
        etAdmin = (EditText) rootView.findViewById(R.id.etAdmin);
        etDescription = (EditText) rootView.findViewById(R.id.etDescription);
        etAmbition = (EditText) rootView.findViewById(R.id.etAmbition);
        etBenefits = (EditText) rootView.findViewById(R.id.etBenefits);
        ivUpload = (ImageView) rootView.findViewById(R.id.ivUpload);
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

    @Override
    public void onDatePickerCancel() {
        DialogFragment dialog = (DialogFragment) getChildFragmentManager()
                .findFragmentByTag(DATE_TIME_PICKER);
        dialog.dismiss();
    }

    @Override
    public void onEventDateTimeSet(int year, int month, int day, int hourOfDay, String minute) {
        ArrayList<Integer> date = new ArrayList<>();
        date.add(year);
        date.add(month);
        date.add(day);
        date.add(hourOfDay);
        date.add(Integer.valueOf(minute));

        dateTimePresenter.setTextForEditedDateField(campaign, date);
    }

    @Override
    public boolean verifyFields() {
        themes = themeUtilities.getListItems();
        venues = venueUtilities.getListItems();
        types = typeUtilities.getListItems();

        if (alertFieldsAreValid()) {
            campaign.setThemeImageUrl(themes.get(themePosition));
            campaign.setRecordType(getTextFrom(types, spType));
            campaign.setVenue(getTextFrom(venues, spVenue));
            campaign.setTitle(getTextFrom(etTitle));
            campaign.setAdmin(getTextFrom(etAdmin));
            campaign.setDescription(getTextFrom(etDescription));
        } else {
            return false;
        }

        if (verify(etAmbition)) { campaign.setAmbition(getTextFrom(etAmbition)); }
        if (verify(etBenefits)) { campaign.setBenefits(getTextFrom(etBenefits)); }

        return true;
    }

    private boolean alertFieldsAreValid() {
        return alertVerify(themePosition, THEME_REQUIRED, themeUtilities)
                && alertVerify(spType, TYPE_REQUIRED, typeUtilities)
                && alertVerify(spVenue, VENUE_REQUIRED, venueUtilities)
                && alertVerify(etTitle, TITLE_REQUIRED)
                && alertVerify(etAdmin, ADMIN_REQUIRED)
                && alertVerify(etDescription, DESCRIPTION_REQUIRED);
    }

    @Override
    public void onConfirmSave() {
        themeUtilities.saveCampaignToDatabase();
        themeUtilities.startDetailActivityWith(getActivity(), isNewCampaign);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        UploadActivityListener resultListener = new UploadActivityListener(getActivity());
        resultListener.onActivityResult(requestCode, resultCode, data, campaign, ivUpload);
    }
}
