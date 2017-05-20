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

import org.blocorganization.blocapp.FirebaseCampaignsHelper;
import org.blocorganization.blocapp.campaigns.CampaignDetailActivity;
import org.blocorganization.blocapp.models.Campaign;

import java.util.ArrayList;
import java.util.List;

import static org.blocorganization.blocapp.utils.FieldUtilities.setSelectionForSpinnerFromList;

public class CreateUtilities {

    public static final String TYPE = "TYPE";
    public static final String VENUE = "VENUE";
    public static final String RES = "res";
    public static final String VENUES = "venues";
    public static final String TYPES = "types";
    private static final String NEWS_FEED_SOURCES = "news feed sources";

    private static final String CAMPAIGNS = "campaigns";

    private List<String> listItems;
    private final Campaign passedCampaign;
    private final Activity activity;
    public ImageThemeAdapter adapter;

    public CreateUtilities(@NonNull Campaign campaign, Activity activity) {
        this.passedCampaign = campaign;
        this.activity = activity;
        this.listItems = new ArrayList<>();
    }

    public void setRvAdapterFromImageUrlList(DatabaseReference databaseReference, final RecyclerView recyclerView) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressWarnings("unchecked")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() instanceof List) {
                    listItems = (List) dataSnapshot.getValue();
                }
                if (recyclerView.getAdapter() == null) {
                    //noinspection ConstantConditions
                    if (passedCampaign != null) {
                        adapter = new ImageThemeAdapter(activity,
                                listItems, passedCampaign.getThemeImageUrl());
                    }
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
            @SuppressWarnings("unchecked")
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
        //noinspection ConstantConditions
        if (passedCampaign != null) {
            switch (methodCall) {
                case TYPE:
                    setSelectionForSpinnerFromList(listItems, passedCampaign.getRecordType(), spinner);
                    return;
                case VENUE:
                    setSelectionForSpinnerFromList(listItems, passedCampaign.getVenue(), spinner);
            }
        }
    }

    public void updateNetwork() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(RES).child(NEWS_FEED_SOURCES);
        mDatabase.push().setValue(passedCampaign);
    }

    public void saveCampaignToDatabase() {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(CAMPAIGNS);
        mDatabase.child(String.valueOf(
                FirebaseCampaignsHelper.getInstance().getCampaignCount()))
                .setValue(passedCampaign);
    }

    public void startDetailActivityWith(Activity activity) {
        activity.onBackPressed();
        Intent intent = new Intent(activity, CampaignDetailActivity.class);
        //noinspection ConstantConditions
        if (passedCampaign != null) {
            intent.putExtras(passedCampaign.toBundle());
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
