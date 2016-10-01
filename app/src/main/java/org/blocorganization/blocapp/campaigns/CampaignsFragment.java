package org.blocorganization.blocapp.campaigns;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.blocorganization.blocapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignsFragment extends Fragment {

    public CampaignsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_campaigns, container, false);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("FRAG", "Campaigns onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("FRAG", "Campaigns onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("FRAG", "Campaigns onDestroy");
    }

}
