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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.blocorganization.blocapp.FirebaseCampaignsHelper;
import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Campaign;
import org.blocorganization.blocapp.utils.CampaignsItemAdapter;
import org.blocorganization.blocapp.utils.RecyclerItemClickListener;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignsSubFragment extends Fragment
        implements FirebaseCampaignsHelper.FirebaseCampaignsListener {

    private static final int GALLERY_INTENT = 2;
    private static final String UPLOAD_DONE = "UPLOAD_DONE";
    private static final String UPLOAD_FAILED = "UPLOAD_FAILED";
    private static final String PHOTOS = "photos";

    private StorageReference mStorage;
    private ProgressDialog mProgressDialog;

    private CampaignsItemAdapter adapter;

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

        final RecyclerView rvCampaigns = (RecyclerView) rootView.findViewById(R.id.rvCampaigns);
        final List<Campaign> campaigns = getFirebaseCampaigns();
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
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            mProgressDialog.setMessage("Uploading...");
            mProgressDialog.show();

            Uri uri = data.getData();
            StorageReference filepath = mStorage.child(PHOTOS).child(uri.getLastPathSegment());

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

    @Override
    public List<Campaign> getFirebaseCampaigns() {
        return FirebaseCampaignsHelper.getInstance().getCampaigns();
    }

    @Override
    public int getFirebaseCampaignPosition(Campaign campaign) {
        return FirebaseCampaignsHelper.getInstance().getCampaignPosition(campaign);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            adapter.swapCampaignsData(getFirebaseCampaigns());
        }
    }
}

