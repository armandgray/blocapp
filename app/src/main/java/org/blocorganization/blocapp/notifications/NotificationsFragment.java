package org.blocorganization.blocapp.notifications;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Record;

import static org.blocorganization.blocapp.MainActivity.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment implements
        NotificationsListFragment.NotificationsListFragmentListener {

    public NotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_notifications, container, false);

        NotificationsListFragment listFrag = new NotificationsListFragment();
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.notifications_list_container, listFrag)
                .addToBackStack(TAG)
                .commit();

        return rootView;
    }

    @Override
    public void onItemSelected(Record record) {
        NotificationsDetailFragment detailFrag = NotificationsDetailFragment
                .newInstance(record);
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.notifications_list_container, detailFrag)
                .addToBackStack(TAG)
                .commit();
    }
}
