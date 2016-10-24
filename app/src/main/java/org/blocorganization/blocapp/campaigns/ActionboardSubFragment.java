package org.blocorganization.blocapp.campaigns;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.blocorganization.blocapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActionboardSubFragment extends Fragment {

    ActionboardFragListener mListener;

    public ActionboardSubFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        assert getParentFragment() instanceof ActionboardFragListener;
        mListener = (ActionboardFragListener) getParentFragment();
    }

    public static ActionboardSubFragment newInstance() {

        Bundle args = new Bundle();

        ActionboardSubFragment fragment = new ActionboardSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.campaigns_subfragment_actionboard, container, false);

        mListener.onActionboardCreated();

        return rootView;
    }

    public interface ActionboardFragListener {
        void onActionboardCreated();
    }
}
