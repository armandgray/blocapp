package org.blocorganization.blocapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateCampaignFragment extends Fragment {


    public CreateCampaignFragment() {
        // Required empty public constructor
    }

    public static CreateCampaignFragment newInstance() {
        
        Bundle args = new Bundle();
        
        CreateCampaignFragment fragment = new CreateCampaignFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_fragment_campaign, container, false);

        return rootView;
    }

}
