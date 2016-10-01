package org.blocorganization.blocapp.utils;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import org.blocorganization.blocapp.R;

public class NavBarFragment extends Fragment {

    private NavBarFragmentListener mListener;

    public NavBarFragment(){
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        assert context instanceof NavBarFragmentListener;
        mListener = (NavBarFragmentListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("FRAG", "Fragment Created");
        View rootView = inflater.inflate(R.layout.fragment_navbar, container, false);
        // onClick calls to mListener for each icon
        ImageView bullhorn = (ImageView) rootView.findViewById(R.id.bullhorn);
        bullhorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onBullhornClick();
            }
        });

        ImageView calendar = (ImageView) rootView.findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onCampaignsClick();
            }
        });

        ImageView emoticon = (ImageView) rootView.findViewById(R.id.emoticon);
        emoticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onMessagesClick();
            }
        });

        ImageView personal = (ImageView) rootView.findViewById(R.id.personal);
        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {mListener.onNotificationsClick();
            }
        });

        ImageView bloc = (ImageView) rootView.findViewById(R.id.boldB);
        bloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onBlocClick();
            }
        });

        return rootView;
    }

    public interface NavBarFragmentListener {
        void onBullhornClick();
        void onCampaignsClick();
        void onMessagesClick();
        void onBlocClick();
        void onNotificationsClick();
    }

}
