package org.blocorganization.blocapp.campaigns;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.blocorganization.blocapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActionboardSubFragment extends Fragment {

    private ImageView ivCertIcon;
    private ImageView ivCampaignImage1;
    private ImageView ivCampaignImage3;
    private ImageView ivCampaignImage2;
    private ImageView ivCampaignImage4;

    public ActionboardSubFragment() {
        // Required empty public constructor
    }

    public static ActionboardSubFragment newInstance() {
        Bundle args = new Bundle();
        ActionboardSubFragment fragment = new ActionboardSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.campaigns_subfragment_actionboard, container, false);

        assignFields(rootView);
        setupImageViews();


        return rootView;
    }

    private void assignFields(View rootView) {
        ivCertIcon = (ImageView) rootView.findViewById(R.id.ivCertIcon);
        ivCampaignImage1 = (ImageView) rootView.findViewById(R.id.ivCampaignImage1);
        ivCampaignImage2 = (ImageView) rootView.findViewById(R.id.ivCampaignImage2);
        ivCampaignImage3 = (ImageView) rootView.findViewById(R.id.ivCampaignImage3);
        ivCampaignImage4 = (ImageView) rootView.findViewById(R.id.ivCampaignImage4);
    }

    private void setupImageViews() {
        ivCertIcon.setColorFilter(getResources().getColor(R.color.ToolBarColor));
        Picasso.with(getActivity()).load(
                "https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/photos%2F65?alt=media&token=38e367dd-9ae1-44e0-b5ba-cecad10278ad"
        ).into(ivCampaignImage1);
        Picasso.with(getActivity()).load(
                "https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/photos%2FAsk%20Flyer.jpg?alt=media&token=a9e14f66-7999-46d0-b0ec-aa10dd4f1e6c"
        ).into(ivCampaignImage2);
        Picasso.with(getActivity()).load(
                "https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/photos%2Fbloc_brothers_dinner.jpg?alt=media&token=2eb0cf2e-c139-49d7-8734-8fc3689c6d72"
        ).into(ivCampaignImage3);
        Picasso.with(getActivity()).load(
                "https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/photos%2Fyoung_mens_circle%202.png?alt=media&token=fdfbd39a-e86b-48a8-a290-b868db72b04b"
        ).into(ivCampaignImage4);
    }

}
