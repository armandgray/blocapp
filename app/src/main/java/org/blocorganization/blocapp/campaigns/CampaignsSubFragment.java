package org.blocorganization.blocapp.campaigns;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Campaign;
import org.blocorganization.blocapp.models.CommunityEngagementCampaign;
import org.blocorganization.blocapp.models.RecordType;
import org.blocorganization.blocapp.utils.CampaignsItemAdapter;
import org.blocorganization.blocapp.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignsSubFragment extends Fragment {

    public static final String CAMPAIGNS_CHILD = "campaigns";
    private static final String CAMPAIGN_CREATED_EVENT = "campaign_created";
    public static final int GALLERY_INTENT = 2;
    public static final String UPLOAD_DONE = "UPLOAD_DONE";
    public static final String UPLOAD_FAILED = "UPLOAD_FAILED";
    public static final int CAMERA_REQUEST_CODE = 1;
    public static final String PHOTOS = "photos";
    public static final String DOWNLOAD_FAILED = "DOWNLOAD_FAILED";
    public static final String NEW_CAMPAIGN_TAG = "NEW_CAMPAIGN";
    Boolean initLoad = true;

    private StorageReference mStorage;
    private StorageReference filepath;
    private DatabaseReference mCampaignsDatabaseReference;
    private ProgressDialog mProgressDialog;
    private ImageView ivTemporary;

    String link;

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

        mStorage = FirebaseStorage.getInstance().getReference();
        mProgressDialog = new ProgressDialog(getActivity());
        ivTemporary = (ImageView) rootView.findViewById(R.id.ivTemporary);

        final CommunityEngagementCampaign toysForTots = new CommunityEngagementCampaign();
        toysForTots.setTitle("Toys For Tots");
        toysForTots.setAdmin("Jauresse Gaines");
        toysForTots.setDescription("The Toys for Tots program is a winter toy drive hosted by BLOC that gives the students of the Claremont Colleges the opportunity to donate toys and cash to organizations in the community.");
        toysForTots.setBenefits("The members of BLOC are very aware of the economic distinction between the students of the Claremont Colleges and the surrounding community. While most of the students are fairly well-to-do, families in the surrounding community struggle to makes ends meet. With this in mind, BLOC created its Toys for Tots program.");
        toysForTots.setAmbition("With this initiative it was BLOCs aim to brighten the holiday season for families in Pomona, Covina, Upland, and Montclair.");
        toysForTots.setCampaignPhoto("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/photos%2F65?alt=media&token=38e367dd-9ae1-44e0-b5ba-cecad10278ad");
        toysForTots.setCampaignTheme("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_donate.jpg?alt=media&token=84597e3f-5a37-4527-bb78-996094a95819");
        toysForTots.setPlanOfExecution("During the month of November, members of BLOC sit in dining halls around campus and ask for donations. To fromDate, BLOC has collected upwards of $2500 in toys and funds. All donations have gone to the Citrus Valley Health Partners pediatric unit and in the future, BLOC will be expanding its donations to various women and children’s shelters in the area.");
        toysForTots.setVenue("5C Dining Halls");
        toysForTots.setMonth("Apr");
        toysForTots.setFromDate("27");
        toysForTots.setExtras("R.drawable.toy_drive_donations_sheet");

        final CommunityEngagementCampaign askCampaign = new CommunityEngagementCampaign();
        askCampaign.setTitle("ASK Campaign");
        askCampaign.setAdmin("Jauresse Gaines");
        askCampaign.setDescription("The Toys for Tots program is a winter toy drive hosted by BLOC that gives the students of the Claremont Colleges the opportunity to donate toys and cash to organizations in the community.");
        askCampaign.setBenefits("The members of BLOC are very aware of the economic distinction between the students of the Claremont Colleges and the surrounding community. While most of the students are fairly well-to-do, families in the surrounding community struggle to makes ends meet. With this in mind, BLOC created its Toys for Tots program. ");
        askCampaign.setAmbition("With this initiative it was BLOCs aim to brighten the holiday season for families in Pomona, Covina, Upland, and Montclair.");
        askCampaign.setCampaignPhoto("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/photos%2FAsk%20Flyer.jpg?alt=media&token=a9e14f66-7999-46d0-b0ec-aa10dd4f1e6c");
        askCampaign.setCampaignTheme("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_consent.jpg?alt=media&token=b56d1ec4-ebfc-420a-8bb1-adf75d47b004");
        askCampaign.setPlanOfExecution("During the month of November, members of BLOC sit in dining halls around campus and ask for donations. To fromDate, BLOC has collected upwards of $2500 in toys and funds. All donations have gone to the Citrus Valley Health Partners pediatric unit and in the future, BLOC will be expanding its donations to various women and children’s shelters in the area.");
        askCampaign.setVenue("5C Dining Halls");
        askCampaign.setMonth("Dec");
        askCampaign.setFromDate("23");
        askCampaign.setExtras("R.drawable.toy_drive_donations_sheet");

        final CommunityEngagementCampaign ymc = new CommunityEngagementCampaign();
        ymc.setTitle("Young Men's Circle");
        ymc.setAdmin("Martin Barrera");
        ymc.setBenefits("The members of BLOC are very aware of the economic distinction between the students of the Claremont Colleges and the surrounding community. While most of the students are fairly well-to-do, families in the surrounding community struggle to makes ends meet. With this in mind, BLOC created its Toys for Tots program. ");
        ymc.setAmbition("With this initiative it was BLOCs aim to brighten the holiday season for families in Pomona, Covina, Upland, and Montclair.");
        ymc.setCampaignPhoto("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/photos%2Fyoung_mens_circle%202.png?alt=media&token=fdfbd39a-e86b-48a8-a290-b868db72b04b");
        ymc.setCampaignTheme("https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/themes%2Ftheme_mentoring2.png?alt=media&token=b5139116-5567-4fd2-a579-8592ef06ba9c");
        ymc.setPlanOfExecution("During the month of November, members of BLOC sit in dining halls around campus and ask for donations. To fromDate, BLOC has collected upwards of $2500 in toys and funds. All donations have gone to the Citrus Valley Health Partners pediatric unit and in the future, BLOC will be expanding its donations to various women and children’s shelters in the area.");
        ymc.setVenue("5C Dining Halls");
        ymc.setMonth("Dec");
        ymc.setFromDate("1");
        ymc.setRecordType(String.valueOf(RecordType.COMMUNITY_ENGAGEMENT));
        ymc.setExtras("R.drawable.toy_drive_donations_sheet");

        // create campaigns list here
        campaigns = new ArrayList<>();
        if (initLoad) {
            campaigns.add(toysForTots);
            campaigns.add(askCampaign);
            campaigns.add(ymc);
        }

        // Add campaigns to FirebaseDatabase child("campaigns")
        mCampaignsDatabaseReference = FirebaseDatabase.getInstance().getReference(CAMPAIGNS_CHILD);
        mCampaignsDatabaseReference.setValue(campaigns);
        mCampaignsDatabaseReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, String> campaignsSnapshot = (Map) dataSnapshot.getValue();
                final CommunityEngagementCampaign campaign = new CommunityEngagementCampaign();

                campaign.setAbbreviation(campaignsSnapshot.get("abbreviation"));
                campaign.setTitle(campaignsSnapshot.get("title"));
                campaign.setAdmin(campaignsSnapshot.get("admin"));
                campaign.setDescription(campaignsSnapshot.get("description"));
                campaign.setBenefits(campaignsSnapshot.get("benefits"));
                campaign.setAmbition(campaignsSnapshot.get("admin"));
                campaign.setPlanOfExecution(campaignsSnapshot.get("planOfExecution"));
                campaign.setItemizedBudget(campaignsSnapshot.get("itemizedBudget"));
                campaign.setVenue(campaignsSnapshot.get("venue"));
                campaign.setFromDate(campaignsSnapshot.get("fromDate"));
                campaign.setTime(campaignsSnapshot.get("time"));
                campaign.setRecordType(campaignsSnapshot.get("recordType"));
                campaign.setExtras(campaignsSnapshot.get("extras"));
                campaign.setMonth(campaignsSnapshot.get("month"));
                campaign.setCampaignPhoto(campaignsSnapshot.get("campaignPhoto"));
                campaign.setCampaignTheme(campaignsSnapshot.get("campaignTheme"));

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

        final RecyclerView rvCampaigns = (RecyclerView) rootView.findViewById(R.id.rvCampaigns);
        adapter = new CampaignsItemAdapter(getActivity(), true, campaigns);
        rvCampaigns.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvCampaigns.setLayoutManager(gridLayoutManager);

        rvCampaigns.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), CampaignDetailActivity.class);
                        intent.putExtras(campaigns.get(position).toBundle());
                        startActivity(intent);
                    }
                })
        );

        LinearLayout createBtn = (LinearLayout) rootView.findViewById(R.id.btn_container_red);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isNewCampaign = true;
                Intent intent = new Intent(getContext(), CampaignDetailActivity.class);
                intent.putExtra(NEW_CAMPAIGN_TAG, isNewCampaign);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        https://www.youtube.com/watch?v=Zy2DKo0v-OY&index=14&list=PLGCjwl1RrtcTXrWuRTa59RyRmQ4OedWrt
//        if (position % 2 == 0) {
//
//                        } else {
//                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                            startActivityForResult(intent, CAMERA_REQUEST_CODE);
//                        }
        if (requestCode == GALLERY_INTENT && resultCode == getActivity().RESULT_OK) {
            mProgressDialog.setMessage("Uploading...");
            mProgressDialog.show();

            Uri uri = data.getData();
            // Create dir photos and subfile
            filepath = mStorage.child(PHOTOS).child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getActivity(), UPLOAD_DONE, Toast.LENGTH_LONG).show();
                    mProgressDialog.dismiss();
                    Uri downloadUri = taskSnapshot.getDownloadUrl();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), UPLOAD_FAILED, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}

