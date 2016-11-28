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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
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
import org.blocorganization.blocapp.utils.GetDpMeasurement;
import org.blocorganization.blocapp.utils.ImageThemeAdapter;
import org.blocorganization.blocapp.utils.RecyclerItemClickListener;
import org.blocorganization.blocapp.utils.SpinnerAdapter;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import static org.blocorganization.blocapp.campaigns.FieldUtilities.AMBITION;
import static org.blocorganization.blocapp.campaigns.FieldUtilities.BENEFITS_TO_THE_COLLEGE;
import static org.blocorganization.blocapp.campaigns.FieldUtilities.DESCRIPTION;
import static org.blocorganization.blocapp.campaigns.FieldUtilities.loadUrlIntoImageViewWithActivity;
import static org.blocorganization.blocapp.campaigns.FieldUtilities.saveCampaignToDatabaseWith;
import static org.blocorganization.blocapp.campaigns.FieldUtilities.setSelectionForSpinnerFromList;
import static org.blocorganization.blocapp.campaigns.FieldUtilities.setTextForEditTextAndPrepend;
import static org.blocorganization.blocapp.campaigns.FieldUtilities.setTextForEditTextWith;
import static org.blocorganization.blocapp.campaigns.UploadButtonIncluder.setupUploadButtonFrom;
import static org.blocorganization.blocapp.utils.DateTimeHandler.setTextForDateWith;

public class CreateInfoDialog extends DialogFragment
        implements DateTimePickerFragment.DateTimeSetListener,
        ConfirmChangesDialogFragment.ConfirmChangesListener {

    public static final int THEME_LAYOUT_PARAMS = 100;
    public static final String THEMES = "themes";
    public static final String IMAGEURLS = "imageurls";
    public static final String RES = "res";
    public static final String VENUES = "venues";
    public static final String TYPES = "types";
    public static final String DIALOG = "DIALOG";
    public static final String DATE_TIME_PICKER = "dateTimePicker";

    public static final String DATE = "Date";

    private Campaign campaign;

    private ImageThemeAdapter adapter;
    private List<String> themes = new ArrayList<>();
    private List<String> venues = new ArrayList<>();
    private List<String> types = new ArrayList<>();

    // Edit Date fields
    private Boolean isRange;
    private boolean editEndDate;
    private RelativeLayout editDateLayout;
    private RelativeLayout editDateFromLayout;
    private RelativeLayout editDateEndLayout;
    private TextView tvToDate;
    private TextView tvFromDate;
    private ImageView ivToDateMenuArrow;

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

    private UploadActivityListener resultListener;

    public static CreateInfoDialog withCampaign(Campaign passedCampaign) {
        CreateInfoDialog fragment = new CreateInfoDialog();
        fragment.setArguments(passedCampaign.toBundle());
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        final View rootView = inflater.inflate(R.layout.create_info_dialog, container, false);

        getPassedCampaign();

        DatabaseReference mDatabaseResources = FirebaseDatabase.getInstance().getReference().child(RES);
        DatabaseReference dbThemes = mDatabaseResources.child(IMAGEURLS).child(THEMES);

        // Load Recycler with jpg from Firebase
        rvThemes = (RecyclerView) rootView.findViewById(R.id.rvThemes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvThemes.setLayoutManager(layoutManager);
        rvThemes.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    LinearLayout lastClick;

                    @Override
                    public void onItemClick(View view, int position) {
                        LinearLayout imgLayout = (LinearLayout) view;
                        if (lastClick != null) {
                            lastClick.setBackgroundResource(R.drawable.background_square_shadow);
                            ImageView lastImg = (ImageView) lastClick.getChildAt(0);
                            lastImg.setColorFilter(Color.parseColor("#59000000"));
                            lastImg.setBackgroundColor(Color.parseColor("#59000000"));
                        }
                        imgLayout.setBackgroundResource(R.drawable.background_square_selected_shadow);
                        ImageView img = (ImageView) imgLayout.getChildAt(0);
                        img.setColorFilter(Color.parseColor("#00000000"));
                        img.setBackgroundColor(Color.parseColor("#00000000"));
                        themePosition = position;
                        lastClick = (LinearLayout) view;
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

            private boolean fieldVerification() {
                boolean allClear = true;
                if (themePosition != null) {
                    campaign.setThemeImageUrl(themes.get(themePosition));
                } else {
                    allClear = false;
                    Toast.makeText(getActivity(), "Theme is required", Toast.LENGTH_SHORT).show();
                }
                if (spType.getSelectedItemPosition() != 0) {
                    campaign.setRecordType(types.get(spType.getSelectedItemPosition()));
                } else {
                    allClear = false;
                    Toast.makeText(getActivity(), "Type is required", Toast.LENGTH_SHORT).show();
                }
                if (spVenue.getSelectedItemPosition() != 0) {
                    campaign.setVenue(venues.get(spVenue.getSelectedItemPosition()));
                } else {
                    allClear = false;
                    Toast.makeText(getActivity(), "Venue is required", Toast.LENGTH_SHORT).show();
                }
                if (tvFromDate.getText().toString().equals("") || tvFromDate.getText() == null || tvFromDate.getText().toString().equals(DATE)) {
                    allClear = false;
                    Toast.makeText(getActivity(), "Date is required", Toast.LENGTH_SHORT).show();
                }
                if (!etTitle.getText().toString().equals("") && etTitle.getText() != null) {
                    campaign.setTitle(etTitle.getText().toString());
                } else {
                    allClear = false;
                    Toast.makeText(getActivity(), "Title is required", Toast.LENGTH_SHORT).show();
                }
                if (!etAbbreviation.getText().toString().equals("") && etAbbreviation.getText() != null) {
                    campaign.setAbbreviation(etAbbreviation.getText().toString());
                }
                if (!etAdmin.getText().toString().equals("") && etAdmin.getText() != null) {
                    campaign.setAdmin(etAdmin.getText().toString());
                } else {
                    allClear = false;
                    Toast.makeText(getActivity(), "Admin is required", Toast.LENGTH_SHORT).show();
                }
                if (!etDescription.getText().toString().equals("") && etDescription.getText() != null) {
                    StringBuffer buffer = new StringBuffer(etDescription.getText().toString());
                    campaign.setDescription(buffer.replace(0, DESCRIPTION.length(), "").toString());
                } else {
                    allClear = false;
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
                // if all fields are "allClear", move to confirm changes
                return allClear;
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

        // select fromDate with DatePickerFrag
        editDateLayout = (RelativeLayout) rootView.findViewById(R.id.editDateLayout);
        editDateFromLayout = (RelativeLayout) rootView.findViewById(R.id.editDateFromLayout);
        editDateEndLayout = (RelativeLayout) rootView.findViewById(R.id.editDateEndLayout);
        tvFromDate = (TextView) rootView.findViewById(R.id.tvFromDate);
        tvToDate = (TextView) rootView.findViewById(R.id.tvToDate);
        ivToDateMenuArrow = (ImageView) rootView.findViewById(R.id.ivMenuArrow);

        editDateEndLayout.setVisibility(View.GONE);
        editDateFromLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DateTimePickerFragment()
                        .show(getChildFragmentManager(), DATE_TIME_PICKER);
            }
        });

        // handle fromDate range options
        ivToDateMenuArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRange == null) {
                    hideEndDateView();
                }
            }
        });

        editDateEndLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DateTimePickerFragment()
                        .show(getChildFragmentManager(), DATE_TIME_PICKER);
                tvToDate.setTextColor(Color.parseColor("#FFFFFF"));
                ivToDateMenuArrow.setColorFilter(Color.parseColor("#FFFFFF"));
                ivToDateMenuArrow.setImageResource(R.drawable.ic_menu_down_white_48dp);
                isRange = true;
                editEndDate = true;
            }
        });
        editDateFromLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEndDateView();
                editDateFromLayout.setBackgroundResource(R.drawable.date_divider_background);
                editDateFromLayout.setPadding(GetDpMeasurement.getDPI(getActivity(), 10), 0, 0, GetDpMeasurement.getDPI(getActivity(), 5));

                return isRange = true;
            }
        });
        editDateEndLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return hideEndDateView();
            }
        });

        setupUploadButtonFrom(rootView, this);

        ivUpload = (ImageView) rootView.findViewById(R.id.ivUpload);

        etTitle = (EditText) rootView.findViewById(R.id.etTitle);
        etAbbreviation = (EditText) rootView.findViewById(R.id.etAbbreviation);
        etAbbreviation.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(3)});
        etAdmin = (EditText) rootView.findViewById(R.id.etAdmin);
        etDescription = (EditText) rootView.findViewById(R.id.etDescription);
        etAmbition = (EditText) rootView.findViewById(R.id.etAmbition);
        etBenefits = (EditText) rootView.findViewById(R.id.etBenefits);

        return rootView;
    }

    private void getPassedCampaign() {
        campaign = new Campaign(getArguments());
        if (getArguments() == null) {
            campaign.setTimestamp();
        }
    }

    private void loadCampaignData() {
        if (getArguments() != null) {
            setSelectionForSpinnerFromList(types, campaign.getRecordType(), spType);
            setSelectionForSpinnerFromList(venues, campaign.getVenue(), spVenue);
            loadDateFields();
            setTextForEditTextWith(campaign.getTitle(), etTitle);
            setTextForEditTextWith(campaign.getAbbreviation(), etAbbreviation);
            setTextForEditTextWith(campaign.getAdmin(), etAdmin);
            setTextForEditTextAndPrepend(DESCRIPTION, campaign.getDescription(), etDescription);
            setTextForEditTextAndPrepend(AMBITION, campaign.getAmbition(), etAmbition);
            setTextForEditTextAndPrepend(BENEFITS_TO_THE_COLLEGE, campaign.getBenefits(), etBenefits);
            loadUrlIntoImageViewWithActivity(campaign.getPhotoUrl(), ivUpload, getActivity());
        }
    }

    private void loadDateFields() {
        setTextForDateWith(campaign.getFromDate(), tvFromDate, true);
        if (campaign.getToDate() != null) {
            setTextForDateWith(campaign.getToDate(), tvToDate, false);
            showEndDateView();
        }
    }

    @Override
    public void onDatePickerCancel() {
        DialogFragment dialog = (DialogFragment) getChildFragmentManager()
                .findFragmentByTag(DATE_TIME_PICKER);
        dialog.dismiss();
    }

    @Override
    public void onEventDateTimeSet(int year, int month, int day, int hourOfDay, String minute) {
        // Date formatting fields
        ArrayList<Integer> date = new ArrayList<>();
        date.add(year);
        date.add(month);
        date.add(day);
        date.add(hourOfDay);
        date.add(Integer.valueOf(minute));

        DateTime dt = new DateTime();
        String ampmDesignator = "am";
        if (hourOfDay >= 12) {
            ampmDesignator = "pm";
        }
        if (hourOfDay != 12) {
            hourOfDay = hourOfDay % 12;
        }
        dt.withDate(year, month, day);

        // initial state is null; afterwards editEndDate is true if user selects 2nd fromDate field
        if (isRange == null) {
            editDateFromLayout.setBackgroundResource(R.drawable.date_divider_background);
            editDateFromLayout.setPadding(GetDpMeasurement.getDPI(getActivity(), 10), 0, 0, GetDpMeasurement.getDPI(getActivity(), 5));
            tvFromDate.setText("On: " + dt.monthOfYear().getAsText() + " " + day + ", " + year + ", " + hourOfDay + ":" + minute + " " + ampmDesignator);
            campaign.setFromDate(date);
            showEndDateView();
            ivToDateMenuArrow.setImageResource(R.drawable.ic_close_white_48dp);
        } else if (editEndDate && isRange) {
            tvToDate.setText("End: " + dt.monthOfYear().getAsText() + " " + day + ", " + year + ", " + hourOfDay + ":" + minute + " " + ampmDesignator);
            campaign.setToDate(date);
            editEndDate = false;
        } else {
            tvFromDate.setText("On: " + dt.monthOfYear().getAsText() + " " + day + ", " + year + ", " + hourOfDay + ":" + minute + " " + ampmDesignator);
        }
    }

    private void showEndDateView() {
        editDateEndLayout.setPadding(GetDpMeasurement.getDPI(getActivity(), 10), GetDpMeasurement.getDPI(getActivity(), 5), 0, 0);
        editDateEndLayout.setVisibility(View.VISIBLE);

        tvToDate.setTextColor(Color.parseColor("#333333"));
        ivToDateMenuArrow.setImageResource(R.drawable.ic_menu_down_white_48dp);
        ivToDateMenuArrow.setColorFilter(Color.parseColor("#333333"));
    }

    private boolean hideEndDateView() {
        editDateLayout.getChildAt(2).setVisibility(View.GONE);
        editDateLayout.getChildAt(1).setBackgroundResource(R.color.colorPrimaryDark);
        return isRange = false;
    }

    @Override
    public void onConfirmSave() {
        saveCampaignToDatabaseWith(getActivity(), campaign);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        resultListener = new UploadActivityListener(getActivity());
        resultListener.onActivityResult(requestCode, resultCode, data, campaign, ivUpload);
    }

}
