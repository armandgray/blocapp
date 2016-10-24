package org.blocorganization.blocapp.campaigns;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.blocorganization.blocapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignsSubFragment extends Fragment {


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

        ImageView btnImage = (ImageView) rootView.findViewById(R.id.ivBtn);
        btnImage.setImageResource(R.drawable.ic_package_up_white_48dp);
        TextView btnText = (TextView) rootView.findViewById(R.id.tvBtn);
        btnText.setText("Create New Campaign");

        return rootView;
    }

}
