package org.blocorganization.blocapp.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.blocorganization.blocapp.R;

public class NavBarFragment extends Fragment {

    private NavBarFragmentListener mListener;

    public NavBarFragment(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (NavBarFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement NavBarFragmentListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navbar, container, false);
        // onClick calls to mListener for each theme
        ImageView home = (ImageView) rootView.findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onHomeClick();
            }
        });

        ImageView calendar = (ImageView) rootView.findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onCampaignsClick();
            }
        });

        ImageView messages = (ImageView) rootView.findViewById(R.id.messages);
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onMessagesClick();
            }
        });

        ImageView bloc = (ImageView) rootView.findViewById(R.id.ic_bloc);
        bloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onBlocClick();
            }
        });

        return rootView;
    }

    public interface NavBarFragmentListener {
        void onHomeClick();
        void onCampaignsClick();
        void onMessagesClick();
        void onBlocClick();
    }

}
