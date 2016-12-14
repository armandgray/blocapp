package org.blocorganization.blocapp.campaigns;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.ToDoItem;
import org.blocorganization.blocapp.utils.RecyclerItemClickListener;
import org.blocorganization.blocapp.utils.ToDoItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActionboardSubFragment extends Fragment {

    public static final String IMAGE_URL1 = "https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/photos%2F65?alt=media&token=38e367dd-9ae1-44e0-b5ba-cecad10278ad";
    public static final String IMAGE_URL2 = "https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/photos%2FAsk%20Flyer.jpg?alt=media&token=a9e14f66-7999-46d0-b0ec-aa10dd4f1e6c";
    public static final String IMAGE_URL3 = "https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/photos%2Fbloc_brothers_dinner.jpg?alt=media&token=2eb0cf2e-c139-49d7-8734-8fc3689c6d72";
    public static final String IMAGE_URL4 = "https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/photos%2Fyoung_mens_circle%202.png?alt=media&token=fdfbd39a-e86b-48a8-a290-b868db72b04b";
    private ImageView ivCertIcon;
    private ImageView ivCampaignImage1;
    private ImageView ivCampaignImage3;
    private ImageView ivCampaignImage2;
    private ImageView ivCampaignImage4;

    private TextView tvHighPriority;
    private TextView tvMedPriority;
    private TextView tvLowPriority;
    private List<ToDoItem> lstHighPriority;
    private List<ToDoItem> lstMedPriority;
    private List<ToDoItem> lstLowPriority;

    public ActionboardSubFragment() {
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
        View rootView = inflater.inflate(R.layout.campaigns_subfragment_actionboard, container, false);

        assignFields(rootView);
        setupImageViews();
        createListObjects();
        createMockResources();
        collapseAllEmptyViews();
        setupRvHighPriority(rootView);

        return rootView;
    }

    private void assignFields(View rootView) {
        ivCertIcon = (ImageView) rootView.findViewById(R.id.ivCertIcon);
        ivCampaignImage1 = (ImageView) rootView.findViewById(R.id.ivCampaignImage1);
        ivCampaignImage2 = (ImageView) rootView.findViewById(R.id.ivCampaignImage2);
        ivCampaignImage3 = (ImageView) rootView.findViewById(R.id.ivCampaignImage3);
        ivCampaignImage4 = (ImageView) rootView.findViewById(R.id.ivCampaignImage4);

        tvHighPriority = (TextView) rootView.findViewById(R.id.tvHighPriority);
        tvMedPriority = (TextView) rootView.findViewById(R.id.tvMediumPriority);
        tvLowPriority = (TextView) rootView.findViewById(R.id.tvLowPriority);

    }

    private void setupImageViews() {
        ivCertIcon.setColorFilter(Color.parseColor("#FF2A00"));
//        Picasso.with(getActivity()).load(IMAGE_URL1).into(ivCampaignImage1);
//        Picasso.with(getActivity()).load(IMAGE_URL2).into(ivCampaignImage2);
//        Picasso.with(getActivity()).load(IMAGE_URL3).into(ivCampaignImage3);
//        Picasso.with(getActivity()).load(IMAGE_URL4).into(ivCampaignImage4);
    }

    private void createListObjects() {
        lstHighPriority = new ArrayList<>();
        lstMedPriority = new ArrayList<>();
        lstLowPriority= new ArrayList<>();
    }

    private void createMockResources() {
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setTitle("Buy toys from store");
        toDoItem.setSourceRecord("Toys for Tots");
        lstHighPriority.add(toDoItem);

        ToDoItem toDoItem2 = new ToDoItem();
        toDoItem2.setTitle("Get paddies from Hilltop");
        toDoItem2.setSourceRecord("Bloc Party");
        // TODO fix multiple item padding issue
//        lstHighPriority.add(toDoItem2);

    }

    private void collapseAllEmptyViews() {
        collapseEmptyListView(lstHighPriority, tvHighPriority);
        collapseEmptyListView(lstMedPriority, tvMedPriority);
        collapseEmptyListView(lstLowPriority, tvLowPriority);
    }

    private void collapseEmptyListView(List list, View view) {
        if (list.size() == 0) {
            view.setVisibility(View.GONE);
        }
    }

    private void setupRvHighPriority(View rootView) {
        ToDoItemAdapter adptHighPriority = new ToDoItemAdapter(lstHighPriority);
        RecyclerView rvHighPriority = (RecyclerView) rootView.findViewById(R.id.rvHighPriority);
        rvHighPriority.setAdapter(adptHighPriority);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvHighPriority.setLayoutManager(layoutManager);

        rvHighPriority.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                    }
                })
        );
    }

}
