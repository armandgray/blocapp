package org.blocorganization.blocapp;

import android.app.Application;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.blocorganization.blocapp.models.Campaign;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlocApp extends Application {

    public static final String CAMPAIGNS = "campaigns";
    private List<Campaign> campaigns = new ArrayList<>();

    private static BlocApp singleton;

    public static BlocApp getInstance(){
        return singleton;
    }

    public List<Campaign> getCampaigns(){
        return campaigns;
    }

    public int getCampaignPosition(Campaign campaign) {
        for (int i = 0; i < campaigns.size(); i++) {
                if (campaign.getTitle().equals(campaigns.get(i).getTitle())) {
                return i;
            }
        }

        return campaigns.size();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(CAMPAIGNS);
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> campaignsSnapshot = (Map) dataSnapshot.getValue();
                final Campaign campaign = new Campaign();

                campaign.setAbbreviation((String) campaignsSnapshot.get("abbreviation"));
                campaign.setTitle((String) campaignsSnapshot.get("title"));
                campaign.setAdmin((String) campaignsSnapshot.get("admin"));
                campaign.setDescription((String) campaignsSnapshot.get("description"));
                campaign.setBenefits((String) campaignsSnapshot.get("benefits"));
                campaign.setAmbition((String) campaignsSnapshot.get("admin"));
                campaign.setPlanOfExecution((String) campaignsSnapshot.get("planOfExecution"));
                campaign.setItemizedBudget((String) campaignsSnapshot.get("itemizedBudget"));
                campaign.setVenue((String) campaignsSnapshot.get("venue"));
                campaign.setFromDate((ArrayList<Integer>) campaignsSnapshot.get("fromDate"));
                campaign.setRecordType((String) campaignsSnapshot.get("recordType"));
                campaign.setExtras((String) campaignsSnapshot.get("extras"));
                campaign.setToDate((ArrayList<Integer>) campaignsSnapshot.get("toDate"));
                campaign.setPhotoUrl((String) campaignsSnapshot.get("campaignPhoto"));
                campaign.setThemeImageUrl((String) campaignsSnapshot.get("campaignTheme"));

                ArrayList<Long> timestampAsListLong = (ArrayList<Long>) campaignsSnapshot.get("timestamp");
                if (timestampAsListLong != null) {
                    ArrayList<Integer> timestampList = new ArrayList<>();
                    for (Long dateElement : timestampAsListLong) {
                        Integer elementAsInteger = dateElement != null ? dateElement.intValue() : null;
                        timestampList.add(elementAsInteger);
                    }
                    campaign.setTimestamp(timestampList);
                } else {
                    campaign.setTimestamp(null);
                }

                campaigns.add(campaign);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}