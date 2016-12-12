package org.blocorganization.blocapp.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.blocorganization.blocapp.BlocApp;
import org.blocorganization.blocapp.campaigns.CampaignDetailActivity;
import org.blocorganization.blocapp.models.Campaign;

import java.util.ArrayList;
import java.util.List;

import static org.blocorganization.blocapp.campaigns.CreateCampaignDialog.THEME_LAYOUT_PARAMS;
import static org.blocorganization.blocapp.utils.FieldUtilities.setSelectionForSpinnerFromList;

public class CreateUtilities {

    public static final String TYPE = "TYPE";
    public static final String VENUE = "VENUE";
    public static final String RES = "res";
    public static final String VENUES = "venues";
    public static final String TYPES = "types";
    public static final String SUBTYPE = "SUBTYPE";
    public static final String SUBTYPES = "subtypes";
    public static final String REPEAT_EVENT = "REPEAT_EVENT";
    public static final String REPEAT_OPTIONS = "repeat";

    private static final String CAMPAIGNS = "campaigns";

    private List<String> listItems;
    @Nullable private Campaign campaign;
    private Activity activity;

    public CreateUtilities(Activity activity) {
        this.campaign = null;
        this.activity = activity;
        this.listItems = new ArrayList<>();
    }

    public CreateUtilities(@NonNull Campaign campaign, Activity activity) {
        this.campaign = campaign;
        this.activity = activity;
        this.listItems = new ArrayList<>();
    }

    public void getRvImageUrlListFrom(DatabaseReference databaseReference, final RecyclerView recyclerView) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            private ImageThemeAdapter adapter;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listItems = (List) dataSnapshot.getValue();
                if (recyclerView.getAdapter() == null) {
                    adapter = new ImageThemeAdapter(activity,
                            listItems, THEME_LAYOUT_PARAMS, campaign.getThemeImageUrl());
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void getSpinnerListItemsFrom(DatabaseReference databaseReference, final Spinner spinner, final String methodCall) {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listItems = (List) dataSnapshot.getValue();

                SpinnerAdapter adapter = new SpinnerAdapter(listItems, activity);
                if (spinner.getAdapter() == null) {
                    spinner.setAdapter(adapter);
                } else {
                    adapter = (SpinnerAdapter) spinner.getAdapter();
                    adapter.notifyDataSetChanged();
                }
                getSelectionFor(methodCall, spinner);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getSelectionFor(String methodCall, Spinner spinner) {
        if (campaign != null) {
            switch (methodCall) {
                case TYPE:
                    setSelectionForSpinnerFromList(listItems, campaign.getRecordType(), spinner);
                    return;
                case VENUE:
                    setSelectionForSpinnerFromList(listItems, campaign.getVenue(), spinner);
            }
        }
    }

    public void saveCampaignToDatabase() {
        if (campaign != null) {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(CAMPAIGNS);
            mDatabase.child(String.valueOf(
                    BlocApp.getInstance().getCampaignPosition(campaign)))
                    .setValue(campaign);
        }
    }

    public void startDetailActivityWith(Activity activity, boolean isNewCampaign) {
        if (isNewCampaign) {
            activity.onBackPressed();
        }
        activity.onBackPressed();
        Intent intent = new Intent(activity, CampaignDetailActivity.class);
        if (campaign != null) {
            intent.putExtras(campaign.toBundle());
        }
        activity.startActivity(intent);
    }

    @Nullable
    public List<String> getListItems() {
        return listItems;
    }

    public Activity getActivity() {
        return activity;
    }
}
