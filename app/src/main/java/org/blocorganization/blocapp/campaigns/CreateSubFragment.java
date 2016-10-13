package org.blocorganization.blocapp.campaigns;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import org.blocorganization.blocapp.R;

import static org.blocorganization.blocapp.campaigns.CreateGeneralSubFragment.CREATE_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateSubFragment extends Fragment implements CreateHomeSubFragment.CreateTypeSelectedListener {

    public CreateSubFragment() {
        // Required empty public constructor
    }

    public static CreateSubFragment newInstance() {

        Bundle args = new Bundle();

        CreateSubFragment fragment = new CreateSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.campaigns_subfragment_create, container, false);

        CreateHomeSubFragment frag = CreateHomeSubFragment.newInstance();
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.create_container, frag)
                .commit();

        return rootView;
    }

    @Override
    public void onCreateTypeSelected(int num) {
        CreateGeneralSubFragment frag = CreateGeneralSubFragment.newInstance(num);
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.create_container, frag)
                .commit();
    }

}
