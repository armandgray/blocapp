package org.blocorganization.blocapp.campaigns;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.blocorganization.blocapp.R;

public class HomeSubFragment extends Fragment {

    HomeFragListener mListener;

    public HomeSubFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        assert getParentFragment() instanceof HomeFragListener;
        mListener = (HomeFragListener) getParentFragment();
    }

    public static HomeSubFragment newInstance() {

        Bundle args = new Bundle();

        HomeSubFragment fragment = new HomeSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.campaigns_subfragment_home, container, false);

        return rootView;
    }

    public interface HomeFragListener {
        void onHomeCreated();
    }

}
