package org.blocorganization.blocapp.campaigns;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import org.blocorganization.blocapp.utils.CampaignsItemAdapter;
import org.blocorganization.blocapp.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignsSubFragment extends Fragment {

    public static final String CAMPAIGNS_CHILD = "campaigns";
    public static final int GALLERY_INTENT = 2;
    public static final String UPLOAD_DONE = "UPLOAD_DONE";
    public static final String UPLOAD_FAILED = "UPLOAD_FAILED";
    public static final String PHOTOS = "photos";

    private StorageReference mStorage;
    private StorageReference filepath;
    private DatabaseReference mCampaignsDatabaseReference;
    private ProgressDialog mProgressDialog;

    List<Campaign> campaigns = new ArrayList<>();
    CampaignsItemAdapter adapter;

    public CampaignsSubFragment() {}

    public static CampaignsSubFragment newInstance() {

        Bundle args = new Bundle();

        CampaignsSubFragment fragment = new CampaignsSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.campaigns_subfragment_campaigns, container, false);

        mStorage = FirebaseStorage.getInstance().getReference();
        mProgressDialog = new ProgressDialog(getActivity());

        mCampaignsDatabaseReference = FirebaseDatabase.getInstance().getReference(CAMPAIGNS_CHILD);
        mCampaignsDatabaseReference.addChildEventListener(new ChildEventListener() {

            @Override
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
                campaign.setRecordType((String) campaignsSnapshot.get("recordType"));
                campaign.setExtras((String) campaignsSnapshot.get("extras"));
                campaign.setPhotoUrl((String) campaignsSnapshot.get("campaignPhoto"));
                campaign.setThemeImageUrl((String) campaignsSnapshot.get("campaignTheme"));
                if (campaignsSnapshot.get("isPublic") == null) {
                    campaign.setPublic(false);
                } else {
                    campaign.setPublic((Boolean) campaignsSnapshot.get("isPublic"));
                }

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

                ArrayList<Long> fromDateAsListLong = (ArrayList<Long>) campaignsSnapshot.get("fromDate");
                if (fromDateAsListLong != null) {
                    ArrayList<Integer> fromDateList = new ArrayList<>();
                    for (Long dateElement : fromDateAsListLong) {
                        Integer elementAsInteger = dateElement != null ? dateElement.intValue() : null;
                        fromDateList.add(elementAsInteger);
                    }
                    campaign.setFromDate(fromDateList);
                } else {
                    campaign.setFromDate(null);
                }

                ArrayList<Long> toDateAsListLong = (ArrayList<Long>) campaignsSnapshot.get("toDateList");
                if (toDateAsListLong != null) {
                    ArrayList<Integer> toDateList = new ArrayList<>();
                    for (Long dateElement : toDateAsListLong) {
                        Integer elementAsInteger = dateElement != null ? dateElement.intValue() : null;
                        toDateList.add(elementAsInteger);
                    }
                    campaign.setToDate(toDateList);
                } else {
                    campaign.setToDate(null);
                }

                campaigns.add(0, campaign);
                adapter.notifyItemInserted(0);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                // copy code above and use hashcodes to identify the corresponding
                // position of the campaign changed with List<Campaign> campaigns
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
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .add(android.R.id.content, new CreateCampaignDialog())
                        .addToBackStack(null)
                        .commit();
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
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            mProgressDialog.setMessage("Uploading...");
            mProgressDialog.show();

            Uri uri = data.getData();
            filepath = mStorage.child(PHOTOS).child(uri.getLastPathSegment());

            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getActivity(), UPLOAD_DONE, Toast.LENGTH_LONG).show();
                    mProgressDialog.dismiss();

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

