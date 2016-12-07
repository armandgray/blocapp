package org.blocorganization.blocapp.bloc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.CreateDialog;
import org.blocorganization.blocapp.models.Resource;
import org.blocorganization.blocapp.utils.RecyclerItemClickListener;
import org.blocorganization.blocapp.utils.ResourceItemAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.blocorganization.blocapp.models.RecordType.ACADEMIC;
import static org.blocorganization.blocapp.models.RecordType.TIPSANDTRICKS;


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
        assignTvReferencesFrom(rootView);
        collapseAllEmptyViews();

        Resource tip = new Resource.Builder("Use JSTOR")
                .description("When writing essays, always start by writing out your ideas and then find sources on JSTOR to cite. Not the other way around!")
                .admin("Armand Gray")
                .type(TIPSANDTRICKS.toString())
                .subType(ACADEMIC.toString())
                .isPublic(true).build();
        lstTopResources.add(tip);
        lstTopResources.add(tip);

        ResourceItemAdapter adptTopResources = new ResourceItemAdapter(lstTopResources);
        RecyclerView rvTopResources = (RecyclerView) rootView.findViewById(R.id.rvTopResources);
        rvTopResources.setAdapter(adptTopResources);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvTopResources.setLayoutManager(layoutManager);
        rvTopResources.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                    }
                })
        );

        ImageView btnAdd = (ImageView) rootView.findViewById(R.id.ivAddRes);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateDialog createDialog = new CreateDialog();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .add(R.id.main_layout, createDialog)
                        .addToBackStack(null)
                        .commit();
            }
        });


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

    private void assignTvReferencesFrom(View rootView) {
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
