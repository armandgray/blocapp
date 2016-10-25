package org.blocorganization.blocapp.notifications;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Campaign;
import org.blocorganization.blocapp.models.RecordData;
import org.blocorganization.blocapp.utils.NotificationsArrayAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsListFragment extends ListFragment {

    List<Campaign> campaigns = new RecordData().getCampaigns();
    private NotificationsListFragmentListener mParentListener;

    public NotificationsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotificationsArrayAdapter adapter = new NotificationsArrayAdapter(getActivity(), R.layout.record_listitem, campaigns);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_notifications_list, container, false);

        mParentListener = (NotificationsListFragmentListener) getParentFragment();

        return rootView;
    }

    public interface NotificationsListFragmentListener {
        void onItemSelected(Campaign campaigns);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Campaign campaign = campaigns.get(position);
        mParentListener.onItemSelected(campaign);
    }

}
