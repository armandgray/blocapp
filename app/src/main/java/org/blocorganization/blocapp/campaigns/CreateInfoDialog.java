package org.blocorganization.blocapp.campaigns;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.util.Log;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.blocorganization.blocapp.BlocApp;
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

import static org.blocorganization.blocapp.campaigns.UploadButtonUtilities.setupUploadButtonFrom;

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

    public static final int GALLERY_INTENT = 2;
    public static final String UPLOAD_DONE = "UPLOAD_DONE";
    public static final String UPLOAD_FAILED = "UPLOAD_FAILED";
    public static final int CAMERA_REQUEST_CODE = 1;
    public static final String PHOTOS = "photos";
    public static final String DOWNLOAD_FAILED = "DOWNLOAD_FAILED";
    public static final String DESCRIPTION = "Description\n\n\t\t";
    public static final String AMBITION = "Ambition\n\n\t\t";
    public static final String BENEFITS_TO_THE_COLLEGE = "Benefits to the College\n\n\t\t";
    public static final String VENUE = "Venue";
    public static final String TYPE = "Type";
    public static final String DATE = "Date";
    public static final String END = "End";
    public static final String CAMPAIGNS = "campaigns";


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
    private StorageReference mStorage;
    private StorageReference filepath;
    private DatabaseReference mCampaignsDatabaseReference;
    private ProgressDialog mProgressDialog;
    private ImageView ivUpload;

    // fields references
    private Integer themePosition;
    public static RecyclerView.ViewHolder mViewHolder;
    private EditText etTitle;
    private EditText etAbbreviation;
    private EditText etAdmin;
    private EditText etDescription;
    private EditText etAmbition;
    private EditText etBenefits;
    private Spinner spType;
    private Spinner spVenue;
    private RecyclerView rvThemes;

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
                    if (campaign.getCampaignTheme() != null && !campaign.getCampaignTheme().equals("")) {
                        adapter = new ImageThemeAdapter(getActivity(),
                                themes, THEME_LAYOUT_PARAMS, campaign.getCampaignTheme());
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
                    campaign.setCampaignTheme(themes.get(themePosition));
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

        // upload references
        mStorage = FirebaseStorage.getInstance().getReference();
        mProgressDialog = new ProgressDialog(getActivity());

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
        // load current campaign data into fields.
        if (getArguments() != null) {
            if (campaign.getCampaignTheme() != null && !campaign.getCampaignTheme().equals("")) {
                for (int i = 0; i < themes.size() - 1; i++) {
                    if (campaign.getCampaignTheme().equals(themes.get(i))) {
                        // get Viewholder for row and change background
                        themePosition = i;
                        if (i > 3) {
                            rvThemes.scrollToPosition(i - 1);
                        }
                    }
                }
            }
            if (campaign.getRecordType() != null && !campaign.getTitle().equals("")) {
                for (int i = 0; i < types.size() - 1; i++) {
                    if (campaign.getRecordType().equals(types.get(i))) {
                        spType.setSelection(i);
                    }
                }
            }
            if (campaign.getVenue() != null && !campaign.getVenue().equals("")) {
                for (int i = 0; i < venues.size() - 1; i++) {
                    if (campaign.getVenue().equals(venues.get(i))) {
                        spVenue.setSelection(i);
                    }
                }
            }
            if (campaign.getFromDate() != null && !campaign.getFromDate().equals("")) {
                DateTime dt = new DateTime();
                // java.lang.ClassCastException: java.lang.Long cannot be cast to java.lang.Integer
                // Possible that firebase saves the getFromDate as an ArrayList<Long> and the cast is unckecked in dbCallbacks and is really Long not Integer.
//                dt.withDate(campaign.getToDate().get(0), campaign.getToDate().get(1), campaign.getToDate().get(2));
//                dt.withTime(campaign.getFromDate().get(3), campaign.getFromDate().get(4), 0, 0);
                String ampmDesignator = "am";
                if (dt.getHourOfDay() >= 12) {
                    ampmDesignator = "pm";
                }
                int hourOfDay = dt.getHourOfDay();
                if (hourOfDay != 12) {
                    hourOfDay = hourOfDay % 12;
                }
                tvFromDate.setText("On: " + dt.monthOfYear().getAsText() + " " + dt.getDayOfMonth() + ", " + dt.year() + ", " + hourOfDay + ":" + dt.getMinuteOfHour() + " " + ampmDesignator);
            }
            if (campaign.getToDate() != null && !campaign.getToDate().equals("")) {
                DateTime dt = new DateTime();
//                dt.withDate(campaign.getToDate().get(0).intValue(), 0, 0);
//                , campaign.getToDate().get(1), campaign.getToDate().get(2));
//                dt.withTime(campaign.getToDate().get(3), campaign.getToDate().get(4), 0, 0);
                String ampmDesignator = "am";
                if (dt.getHourOfDay() >= 12) {
                    ampmDesignator = "pm";
                }
                int hourOfDay = dt.getHourOfDay();
                if (hourOfDay != 12) {
                    hourOfDay = hourOfDay % 12;
                }
                tvToDate.setText("On: " + dt.monthOfYear().getAsText() + " " + dt.getDayOfMonth() + ", " + dt.year() + ", " + hourOfDay + ":" + dt.getMinuteOfHour() + " " + ampmDesignator);
                isRange = true;
                showEndDateView();
            }
            if (campaign.getTitle() != null && !campaign.getTitle().equals("")) {
                etTitle.setText(campaign.getTitle());
            }
            if (campaign.getAbbreviation() != null && !campaign.getAbbreviation().equals("")) {
                etAbbreviation.setText(campaign.getAbbreviation());
            }
            if (campaign.getAdmin() != null && !campaign.getAdmin().equals("")) {
                etAdmin.setText(campaign.getAdmin());
            }
            if (campaign.getDescription() != null && !campaign.getTitle().equals("")) {
                etDescription.setText(DESCRIPTION + campaign.getBenefits());
            }
            if (campaign.getAmbition() != null && !campaign.getTitle().equals("")) {
                etAmbition.setText(AMBITION + campaign.getBenefits());
            }
            if (campaign.getBenefits() != null && !campaign.getTitle().equals("")) {
                etBenefits.setText(BENEFITS_TO_THE_COLLEGE + campaign.getBenefits());
            }
            if (campaign.getCampaignPhoto() != null && !campaign.getCampaignPhoto().equals("")) {
                Picasso.with(getActivity()).load(campaign.getCampaignPhoto()).into(ivUpload);
                ivUpload.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onUserCancel() {
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
        /**
         *  Add/ Update Campaign in Firebase using position in List
         */
        Log.i("CAMP_DATE", String.valueOf(campaign.getFromDate().get(0)) + campaign.getFromDate().get(1) + campaign.getFromDate().get(2));
        Log.i("CAMP_POS", String.valueOf(BlocApp.getInstance().getCampaignPosition(new Campaign(getArguments()))));
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(CAMPAIGNS);
        mDatabase.child(String.valueOf(
                BlocApp.getInstance().getCampaignPosition(new Campaign(getArguments()))))
                .setValue(campaign);

        getActivity().onBackPressed();
        getActivity().onBackPressed();
        Intent intent = new Intent(getActivity(), CampaignDetailActivity.class);
        intent.putExtras(campaign.toBundle());
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        UploadActivityListener.onActivityResult(getActivity(), requestCode, resultCode, data);

//        if (requestCode == GALLERY_INTENT && resultCode == getActivity().RESULT_OK) {
//            mProgressDialog.setMessage("Uploading...");
//            mProgressDialog.show();
//
//            Uri uri = data.getData();
//            // Create dir photos and subfile
//            filepath = mStorage.child(PHOTOS).child(uri.getLastPathSegment());
//            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    Toast.makeText(getActivity(), UPLOAD_DONE, Toast.LENGTH_LONG).show();
//                    mProgressDialog.dismiss();
//                    Uri downloadUri = taskSnapshot.getDownloadUrl();
//                    campaign.setCampaignPhoto(downloadUri.toString());
//                    ivUpload.setVisibility(View.VISIBLE);
//                    Picasso.with(getActivity()).load(campaign.getCampaignPhoto()).into(ivUpload);
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getActivity(), UPLOAD_FAILED, Toast.LENGTH_LONG).show();
//                }
//            });
//        }
    }

}
