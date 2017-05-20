package org.blocorganization.blocapp.campaigns;

import android.content.Intent;
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Campaign;
import org.blocorganization.blocapp.utils.ConfirmChangesDialogFragment;
import org.blocorganization.blocapp.utils.CreateUtilities;
import org.blocorganization.blocapp.utils.DateTimePickerFragment;
import org.blocorganization.blocapp.utils.DateTimePresenter;
import org.blocorganization.blocapp.utils.DialogSubmitUtilities;
import org.blocorganization.blocapp.utils.ImageThemeAdapter;
import org.blocorganization.blocapp.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import static org.blocorganization.blocapp.campaigns.UploadButtonIncluder.setupUploadButtonFrom;
import static org.blocorganization.blocapp.utils.CreateUtilities.RES;
import static org.blocorganization.blocapp.utils.CreateUtilities.TYPE;
import static org.blocorganization.blocapp.utils.CreateUtilities.TYPES;
import static org.blocorganization.blocapp.utils.CreateUtilities.VENUE;
import static org.blocorganization.blocapp.utils.CreateUtilities.VENUES;
import static org.blocorganization.blocapp.utils.DateTimePresenter.DATE_TIME_PICKER;
import static org.blocorganization.blocapp.utils.FieldUtilities.ADMIN_REQUIRED;
import static org.blocorganization.blocapp.utils.FieldUtilities.DESCRIPTION_REQUIRED;
import static org.blocorganization.blocapp.utils.FieldUtilities.TITLE_REQUIRED;
import static org.blocorganization.blocapp.utils.FieldUtilities.TYPE_REQUIRED;
import static org.blocorganization.blocapp.utils.FieldUtilities.VENUE_REQUIRED;
import static org.blocorganization.blocapp.utils.FieldUtilities.alertVerify;
import static org.blocorganization.blocapp.utils.FieldUtilities.getTextFrom;
import static org.blocorganization.blocapp.utils.FieldUtilities.loadUrlIntoImageViewWithActivity;
import static org.blocorganization.blocapp.utils.FieldUtilities.setTextForEditTextWith;
import static org.blocorganization.blocapp.utils.FieldUtilities.verify;
import static org.blocorganization.blocapp.utils.GetDpMeasurement.getDPI;

public class CreateCampaignDialog extends DialogFragment
        implements DateTimePickerFragment.DateTimeSetListener,
        ConfirmChangesDialogFragment.ConfirmChangesListener,
        DialogSubmitUtilities.DialogSubmitListener {

    public static final int THEME_LAYOUT_PARAMS = 100;
    private static final String THEMES = "themes";
    private static final String IMAGEURLS = "imageurls";

    private Campaign campaign;
    private boolean isNewCampaign = true;

    private DateTimePresenter dateTimePresenter;
    private CreateUtilities themeUtilities;
    private DatabaseReference databaseResources;

    private EditText etTitle;
    private EditText etAbbreviation;
    private EditText etAdmin;
    private EditText etDescription;
    private EditText etAmbition;
    private EditText etBenefits;
    private Switch swIsPublic;
    private Switch swUpdateNetwork;
    private ImageView ivUpload;

    private Spinner spType;
    private Spinner spVenue;

    private Integer themePosition;
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

        getPassedCampaign(rootView);
        assignFields(rootView);
        loadCampaignData();
        setupUtilities(rootView);
        setupRvThemes(rootView);
        setupSpinnersFrom(databaseResources, rootView);
        setupDateTimePresenter(rootView);
        setupUploadButtonFrom(rootView, this);

        return rootView;
    }

    private void getPassedCampaign(View rootView) {
        if (getArguments() != null) {
            campaign = new Campaign(getArguments());
        } else {
            campaign = new Campaign();
            campaign.setTimestamp();
            isNewCampaign = true;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, getDPI(getActivity(), 22), 0, 0);
            rootView.findViewById(R.id.dialogSubmitHeader).setLayoutParams(layoutParams);
            ((TextView) rootView.findViewById(R.id.tvSubmitDialog))
                    .setText(R.string.create_a_new_campaign);
        }
    }

    private void assignFields(View rootView) {
        etTitle = (EditText) rootView.findViewById(R.id.etTitle);
        etAbbreviation = (EditText) rootView.findViewById(R.id.etAbbreviation);
        etAbbreviation.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(3)});
        etAdmin = (EditText) rootView.findViewById(R.id.etAdmin);
        etDescription = (EditText) rootView.findViewById(R.id.etDescription);
        etAmbition = (EditText) rootView.findViewById(R.id.etAmbition);
        etBenefits = (EditText) rootView.findViewById(R.id.etBenefits);
        swUpdateNetwork = (Switch) rootView.findViewById(R.id.swUpdateNetwork);
        swIsPublic = (Switch) rootView.findViewById(R.id.swIsPublic);
        ivUpload = (ImageView) rootView.findViewById(R.id.ivUpload);
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
            swIsPublic.setChecked(campaign.isPublic());
            loadUrlIntoImageViewWithActivity(campaign.getPhotoUrl(), ivUpload, getActivity());
        }
    }

    private void setupRvThemes(View rootView) {
        RecyclerView rvThemes = (RecyclerView) rootView.findViewById(R.id.rvThemes);
        DatabaseReference dbThemes = databaseResources.child(IMAGEURLS).child(THEMES);
        if (dbThemes == null) {
            Toast.makeText(getActivity(), "Database Access Failure (Required)", Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
            return;
        }

        final ImageThemeAdapter adapter = themeUtilities.getRvAdapterFromImageUrlList(dbThemes, rvThemes);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvThemes.setLayoutManager(layoutManager);
        rvThemes.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        themePosition = position;
                        adapter.highlightView(position);
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

    @Override
    public void onDatePickerCancel() {
        DialogFragment dialog = (DialogFragment) getChildFragmentManager()
                .findFragmentByTag(DATE_TIME_PICKER);
        dialog.dismiss();
    }

    @Override
    public void onEventDateTimeSet(int year, int month, int day, int hourOfDay, int minute) {
        ArrayList<Integer> date = new ArrayList<>();
        date.add(year);
        date.add(month);
        date.add(day);
        date.add(hourOfDay);
        date.add(minute);

        dateTimePresenter.setTextForEditedDateField(campaign, date);
    }

    @Override
    public boolean verifyFields() {
        List<String> themes = themeUtilities.getListItems();
        List<String> venues = venueUtilities.getListItems();
        List<String> types = typeUtilities.getListItems();

        if (alertFieldsAreValid()) {
            if (themes != null) {
                campaign.setThemeImageUrl(themes.get(themePosition));
            }
            if (types != null) {
                campaign.setRecordType(getTextFrom(types, spType));
            }
            if (venues != null) {
                campaign.setVenue(getTextFrom(venues, spVenue));
            }
            campaign.setTitle(getTextFrom(etTitle));
            campaign.setAdmin(getTextFrom(etAdmin));
            campaign.setDescription(getTextFrom(etDescription));
            campaign.setPublic(swIsPublic.isChecked());
        } else {
            return false;
        }

        if (verify(etAmbition)) { campaign.setAmbition(getTextFrom(etAmbition)); }
        if (verify(etAbbreviation)) { campaign.setAbbreviation(getTextFrom(etAbbreviation)); }
        if (verify(etBenefits)) { campaign.setBenefits(getTextFrom(etBenefits)); }

        return true;
    }

    private boolean alertFieldsAreValid() {
        return alertVerify(themePosition, themeUtilities)
                && alertVerify(spType, TYPE_REQUIRED, typeUtilities)
                && alertVerify(spVenue, VENUE_REQUIRED, venueUtilities)
                && alertVerify(etTitle, TITLE_REQUIRED)
                && alertVerify(etAdmin, ADMIN_REQUIRED)
                && alertVerify(etDescription, DESCRIPTION_REQUIRED);
    }

    @Override
    public void onConfirmSave() {
        if (swUpdateNetwork.isChecked()) {
            themeUtilities.updateNetwork();
        }
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
