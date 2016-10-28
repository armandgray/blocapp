package org.blocorganization.blocapp.campaigns;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Campaign;
import org.blocorganization.blocapp.models.CommunityEngagementCampaign;
import org.blocorganization.blocapp.utils.CampaignsItemAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignsSubFragment extends Fragment {

    public static final String CAMPAIGNS_CHILD = "campaigns";
    private static final String CAMPAIGN_CREATED_EVENT = "campaign_created";

    private DatabaseReference mCampaignsDatabaseReference;

    List<Campaign> campaigns;
    CampaignsItemAdapter adapter;

    public CampaignsSubFragment() {
        // Required empty public constructor
    }

    public static CampaignsSubFragment newInstance() {

        Bundle args = new Bundle();

        CampaignsSubFragment fragment = new CampaignsSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.campaigns_subfragment_campaigns, container, false);

        final CommunityEngagementCampaign toysForTots = new CommunityEngagementCampaign();
        toysForTots.setTitle("Toys For Tots");
        toysForTots.setAdmin("Jauresse Gaines");
        toysForTots.setDescription("The Toys for Tots program is a winter toy drive hosted by BLOC that gives the students of the Claremont Colleges the opportunity to donate toys and cash to organizations in the community.");
        toysForTots.setBenefits("The members of BLOC are very aware of the economic distinction between the students of the Claremont Colleges and the surrounding community. While most of the students are fairly well-to-do, families in the surrounding community struggle to makes ends meet. With this in mind, BLOC created its Toys for Tots program.");
        toysForTots.setAmbition("With this initiative it was BLOCs aim to brighten the holiday season for families in Pomona, Covina, Upland, and Montclair.");
        toysForTots.setCampaignPhoto(R.drawable.toys_for_tots);
        toysForTots.setPlanOfExecution("During the month of November, members of BLOC sit in dining halls around campus and ask for donations. To date, BLOC has collected upwards of $2500 in toys and funds. All donations have gone to the Citrus Valley Health Partners pediatric unit and in the future, BLOC will be expanding its donations to various women and children’s shelters in the area.");
        toysForTots.setVenue("5C Dining Halls");
        toysForTots.setMonth("Nov");
        toysForTots.setDate("27");
        toysForTots.setExtras("R.drawable.toy_drive_donations_sheet");

        final CommunityEngagementCampaign askCampaign = new CommunityEngagementCampaign();
        askCampaign.setTitle("ASK Campaign");
        askCampaign.setAdmin("Jauresse Gaines");
        askCampaign.setDescription("The Toys for Tots program is a winter toy drive hosted by BLOC that gives the students of the Claremont Colleges the opportunity to donate toys and cash to organizations in the community.");
        askCampaign.setBenefits("The members of BLOC are very aware of the economic distinction between the students of the Claremont Colleges and the surrounding community. While most of the students are fairly well-to-do, families in the surrounding community struggle to makes ends meet. With this in mind, BLOC created its Toys for Tots program. ");
        askCampaign.setAmbition("With this initiative it was BLOCs aim to brighten the holiday season for families in Pomona, Covina, Upland, and Montclair.");
        askCampaign.setCampaignPhoto(R.drawable.toys_for_tots);
        askCampaign.setPlanOfExecution("During the month of November, members of BLOC sit in dining halls around campus and ask for donations. To date, BLOC has collected upwards of $2500 in toys and funds. All donations have gone to the Citrus Valley Health Partners pediatric unit and in the future, BLOC will be expanding its donations to various women and children’s shelters in the area.");
        askCampaign.setVenue("5C Dining Halls");
        askCampaign.setMonth("Dec");
        askCampaign.setDate("23");
        askCampaign.setExtras("R.drawable.toy_drive_donations_sheet");

        final CommunityEngagementCampaign ymc = new CommunityEngagementCampaign();
        ymc.setTitle("Young Men's Circle");
        ymc.setAdmin("Martin Barrera");
        ymc.setBenefits("The members of BLOC are very aware of the economic distinction between the students of the Claremont Colleges and the surrounding community. While most of the students are fairly well-to-do, families in the surrounding community struggle to makes ends meet. With this in mind, BLOC created its Toys for Tots program. ");
        ymc.setAmbition("With this initiative it was BLOCs aim to brighten the holiday season for families in Pomona, Covina, Upland, and Montclair.");
        ymc.setCampaignPhoto(R.drawable.toys_for_tots);
        ymc.setPlanOfExecution("During the month of November, members of BLOC sit in dining halls around campus and ask for donations. To date, BLOC has collected upwards of $2500 in toys and funds. All donations have gone to the Citrus Valley Health Partners pediatric unit and in the future, BLOC will be expanding its donations to various women and children’s shelters in the area.");
        ymc.setVenue("5C Dining Halls");
        ymc.setMonth("Dec");
        ymc.setDate("1");
        ymc.setExtras("R.drawable.toy_drive_donations_sheet");

        // create campaigns list here
        campaigns = new ArrayList<>();
        campaigns.add(toysForTots);
        campaigns.add(askCampaign);
        campaigns.add(ymc);

        // Add campaigns to FirebaseDatabase child("campaigns")
        mCampaignsDatabaseReference = FirebaseDatabase.getInstance().getReference().child(CAMPAIGNS_CHILD);
        mCampaignsDatabaseReference.setValue(campaigns);

        final RecyclerView rvCampaigns = (RecyclerView) rootView.findViewById(R.id.rvCampaigns);
        adapter = new CampaignsItemAdapter(getActivity(), campaigns);
        rvCampaigns.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvCampaigns.setLayoutManager(gridLayoutManager);

        rvCampaigns.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                Intent intent = new Intent(getContext(), CampaignDetailActivity.class);
                intent.putExtras(campaigns.get(0).toBundle());
                startActivity(intent);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        LinearLayout createBtn = (LinearLayout) rootView.findViewById(R.id.btn_container_red);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CampaignDetailActivity.class);
                startActivity(intent);
            }
        });

        ImageView btnImage = (ImageView) createBtn.getChildAt(0);
        btnImage.setImageResource(R.drawable.ic_package_up_white_48dp);
        TextView btnText = (TextView) createBtn.getChildAt(1);
        btnText.setText("Create New Campaign");

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        /**
         * "created CampaignsItemAdapter (contains code to dynamically change layout size if(title == null)); Used adapter to created recycler view in HomeFragement and CampaignSubFragement (NOT WORKING: ItemTouchListener); Added campaigns List<Campaigns> dynamically to FirebaseRealtimeDatabase; attached onChildEventListener to FirebaseDatabaseReference"
         */

        // Add child event listener to the campaigns
        mCampaignsDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, String> campaignsSnapshot = (Map) dataSnapshot.getValue();
                Campaign campaign = new Campaign();

                campaign.setAbbreviation(campaignsSnapshot.get("abbreviation"));
                campaign.setTitle(campaignsSnapshot.get("title"));
                campaign.setAdmin(campaignsSnapshot.get("admin"));
                campaign.setDescription(campaignsSnapshot.get("description"));
                campaign.setBenefits(campaignsSnapshot.get("benefits"));
                campaign.setCampaignPhoto(R.drawable.theme_slopes);
                campaign.setAmbition(campaignsSnapshot.get("admin"));
                campaign.setPlanOfExecution(campaignsSnapshot.get("planOfExecution"));
                campaign.setItemizedBudget(campaignsSnapshot.get("itemizedBudget"));
                campaign.setVenue(campaignsSnapshot.get("venue"));
                campaign.setDate(campaignsSnapshot.get("date"));
                campaign.setTime(campaignsSnapshot.get("time"));
                campaign.setRecordType(campaignsSnapshot.get("recordType"));
                campaign.setExtras(campaignsSnapshot.get("extras"));
                campaign.setMonth(campaignsSnapshot.get("month"));
                campaign.setIcon(R.drawable.theme_donate);

                campaigns.add(0, campaign);
                adapter.notifyItemInserted(0);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                HashMap<String, String> campaign = dataSnapshot.getValue(HashMap.class);
//                Log.v("E_CHILD_CHANGED", campaign.toString());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                HashMap<String, String> campaign = dataSnapshot.getValue(HashMap.class);
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



//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                Post post = dataSnapshot.getValue(Post.class);
//                // [START_EXCLUDE]
//                mAuthorView.setText(post.author);
//                mTitleView.setText(post.title);
//                mBodyView.setText(post.body);
//                // [END_EXCLUDE]
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
//                // [START_EXCLUDE]
//                Toast.makeText(PostDetailActivity.this, "Failed to load post.",
//                        Toast.LENGTH_SHORT).show();
//                // [END_EXCLUDE]
//            }
//        mPostReference.addValueEventListener(postListener);

