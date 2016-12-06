package org.blocorganization.blocapp.bloc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Resource;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResourcesSubFragment extends Fragment {

    private List<Resource> lstTopResources;
    private List<Resource> lstTipsNTricks;
    private List<Resource> lstWorkflows;
    private List<Resource> lstShoutouts;
    private List<Resource> lstMiscellaneous;
    private List<Resource> lstInquiryProtocols;
    private TextView tvTipsNTricks;
    private TextView tvWorkflows;
    private TextView tvShoutouts;
    private TextView tvInquiryProtocols;
    private TextView tvMiscellaneous;

    public ResourcesSubFragment() {
        // Required empty public constructor
    }

    public static ResourcesSubFragment newInstance() {

        Bundle args = new Bundle();
        ResourcesSubFragment fragment = new ResourcesSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bloc_subfragment_resources, container, false);

        createListObjects();
        initializeTvReferencesFrom(rootView);
        collapseAllEmptyViews();



        return rootView;
    }

    private void createListObjects() {
        lstTopResources = new ArrayList<>();
        lstTipsNTricks = new ArrayList<>();
        lstWorkflows = new ArrayList<>();
        lstShoutouts = new ArrayList<>();
        lstInquiryProtocols = new ArrayList<>();
        lstMiscellaneous = new ArrayList<>();
    }

    private void initializeTvReferencesFrom(View rootView) {
        tvTipsNTricks = (TextView) rootView.findViewById(R.id.tvTipsNTricks);
        tvWorkflows = (TextView) rootView.findViewById(R.id.tvWorkflows);
        tvShoutouts = (TextView) rootView.findViewById(R.id.tvShoutouts);
        tvInquiryProtocols = (TextView) rootView.findViewById(R.id.tvInquiryProtocols);
        tvMiscellaneous = (TextView) rootView.findViewById(R.id.tvMiscellaneous);
    }

    private void collapseAllEmptyViews() {
        collapseEmptyListView(lstTipsNTricks, tvTipsNTricks);
        collapseEmptyListView(lstWorkflows, tvWorkflows);
        collapseEmptyListView(lstShoutouts, tvShoutouts);
        collapseEmptyListView(lstInquiryProtocols, tvInquiryProtocols);
        collapseEmptyListView(lstMiscellaneous, tvMiscellaneous);
    }

    public void collapseEmptyListView(List list, View view) {
        if (list.size() == 0) {
            view.setVisibility(View.GONE);
        }
    }

}
