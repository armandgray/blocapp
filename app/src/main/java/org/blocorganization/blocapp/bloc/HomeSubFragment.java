package org.blocorganization.blocapp.bloc;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.blocorganization.blocapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeSubFragment extends Fragment {

    private HomeScrollListener mListener;

    public HomeSubFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (HomeScrollListener) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(getParentFragment().toString()
                    + " must implement HomeScrollListener");
        }
        mListener = (HomeScrollListener) getParentFragment();
    }

    public static HomeSubFragment newInstance() {

        Bundle args = new Bundle();

        HomeSubFragment fragment = new HomeSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bloc_subfragment_home, container, false);

        final RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.bloc_layout);
        final ImageView chevron = (ImageView) rootView.findViewById(R.id.bloc_chevron_down);
        chevron.setVisibility(View.GONE);

        final ScrollView scrollView = (ScrollView) rootView.findViewById(R.id.bloc_scrollview);
        final TextView headerBroHood = (TextView) rootView.findViewById(R.id.header_brotherhood);
        final TextView textWhoAreWe = (TextView) rootView.findViewById(R.id.text_whoarewe);

        LinearLayout checkUsBtn = (LinearLayout) rootView.findViewById(R.id.btn_container_red);
        checkUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.smoothScrollTo(0, headerBroHood.getTop());
            }
        });

        ImageView btnImage = (ImageView) checkUsBtn.getChildAt(0);
        btnImage.setImageResource(R.drawable.ic_binoculars_white_48dp);
        TextView btnText = (TextView) checkUsBtn.getChildAt(1);
        btnText.setText("Check Us Out");

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (scrollView.getScrollY() <= 100) {
                    chevron.setVisibility(View.GONE);
                    mListener.onScrollTop();
                }

                if (scrollView.getScrollY() == 0) {
                    mListener.onScrollTop();
                } else if (scrollView.getScrollY()
                        >= textWhoAreWe.getBottom() - layout.getHeight()) {
                    mListener.onScrollDown();
                    chevron.setVisibility(View.INVISIBLE);
                } else {
                    mListener.onScrollDown();
                    chevron.setVisibility(View.VISIBLE);
                }
            }
        });

        return rootView;
    }

    interface HomeScrollListener {
        void onScrollTop();
        void onScrollDown();
    }
}