package org.blocorganization.blocapp.campaigns;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.blocorganization.blocapp.R;

public class HomeSubFragment extends Fragment {

    HomeFragListener mListener;

    public HomeSubFragment() {
        // Required empty public constructor
    }

    public static HomeSubFragment newInstance() {

        Bundle args = new Bundle();

        HomeSubFragment fragment = new HomeSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (HomeFragListener) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement HomeFragListener");
        }
        mListener = (HomeFragListener) getParentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.campaigns_subfragment_home, container, false);

//        ImageView chMeeting = (ImageView) rootView.findViewById(R.id.chMeeting);
//        chMeeting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // call parent to set vpager
//                // call actionboard frag to open meeting details
//            }
//        });

        ImageView chNotes = (ImageView) rootView.findViewById(R.id.chNotes);

        ImageView chSupporters = (ImageView) rootView.findViewById(R.id.chSupporters);

        ImageView ivCampaignToday = (ImageView) rootView.findViewById(R.id.ivCampaignToday);
        Picasso.with(getContext()).load(
                "https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/photos%2Fopen_book_magnifier.jpg?alt=media&token=b38faccb-42ad-4b20-8314-61af4eb0f40f"
        ).into(ivCampaignToday);
        ivCampaignToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CampaignDetailActivity.class);
                startActivity(intent);
            }
        });

        ImageView ivCalendarImage = (ImageView) rootView.findViewById(R.id.ivCalendarImage);
        Picasso.with(getContext()).load(
                "https://firebasestorage.googleapis.com/v0/b/blocapp-22b4d.appspot.com/o/photos%2Fcalendar.jpg?alt=media&token=6c355520-a345-4bb2-8a8a-3ac17ba6b64a"
        ).into(ivCalendarImage);
        ivCalendarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onCalendarImageClick();
            }
        });

        TextView tvCampaignDetails = (TextView) rootView.findViewById(R.id.tvCampaignDetails);
        tvCampaignDetails.setText("Dinner With The Deans\n5:00pm - Behind SCC");
        TextView tvDateToday = (TextView) rootView.findViewById(R.id.tvDateToday);
        tvDateToday.setText("February 5th");
        TextView tvUpcomingAction = (TextView) rootView.findViewById(R.id.tvUpcomingAction);
        tvUpcomingAction.setText("Toys for Tots (deadline - Dec. 12)");

        Log.i("HOME_FRAG", "ONCREATEVIEW");
        return rootView;
    }

    public interface HomeFragListener {
        void onCalendarImageClick();
        void onHomeCreate();
    }

}
