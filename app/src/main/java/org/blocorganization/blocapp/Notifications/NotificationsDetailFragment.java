package org.blocorganization.blocapp.notifications;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Record;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsDetailFragment extends Fragment {

    public static final String BUNDLE_KEY = "BUNDLE_KEY";
    Record record;

    public NotificationsDetailFragment() {
        // Required empty public constructor
    }

    public static NotificationsDetailFragment newInstance(Record record) {

        Bundle args = record.toBundle();
        NotificationsDetailFragment fragment = new NotificationsDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) { record = new Record(getArguments()); }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_notifications_detail, container, false);

        if (record != null) {

            //Display values and image
            TextView tvName = (TextView) rootView.findViewById(R.id.tvRecordDescription);
            tvName.setText(record.getRecordType());

            TextView tvInstructions = (TextView) rootView.findViewById(R.id.tvInstructions);
            tvInstructions.setText(record.getAdmin());

            TextView tvPrice = (TextView) rootView.findViewById(R.id.tvPrice);
            tvPrice.setText(record.getTimestamp());

            ImageView ivPicture = (ImageView) rootView.findViewById(R.id.ivRecordImage);
            ivPicture.setImageResource(record.getImageResource());

        }

        return rootView;
    }

}
