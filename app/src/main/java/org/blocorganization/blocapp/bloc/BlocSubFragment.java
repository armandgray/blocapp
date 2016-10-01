package org.blocorganization.blocapp.bloc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.blocorganization.blocapp.R;
import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlocSubFragment extends Fragment {

    public static final String BLOC_FRAG = "BLOC_FRAG";
    LinearLayout menuContainer;

    public BlocSubFragment() {}

    public static BlocSubFragment newInstance() {

        Bundle args = new Bundle();

        BlocSubFragment fragment = new BlocSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.subfragment_bloc, container, false);

        menuContainer = (LinearLayout) rootView.findViewById(R.id.menu_container);
        final LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.bloc_layout);
        final ImageView chevron = (ImageView) rootView.findViewById(R.id.bloc_chevron_down);
        final ViewGroup chevronParent = (ViewGroup) chevron.getParent();
        chevronParent.removeView(chevron);

        final ScrollView scrollView = (ScrollView) rootView.findViewById(R.id.bloc_scrollview);
        final TextView headerBroHood = (TextView) rootView.findViewById(R.id.header_brotherhood);
        final TextView textWhoAreWe = (TextView) rootView.findViewById(R.id.text_whoarewe);
        Button checkUsBtn = (Button) rootView.findViewById(R.id.bloc_checkus_btn);

        checkUsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.smoothScrollTo(0, headerBroHood.getTop());
            }
        });

        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (chevronParent.getChildAt(0) != chevron) {
                    chevronParent.addView(chevron, 0);
                }
                if (scrollView.getScrollY() <= 100) chevronParent.removeView(chevron);

                if (scrollView.getScrollY() == 0) {
                    menuContainer.setVisibility(View.VISIBLE);
                } else if (scrollView.getScrollY() >= textWhoAreWe.getBottom() - layout.getHeight()){
                    menuContainer.setVisibility(View.INVISIBLE);
                    chevron.setVisibility(View.INVISIBLE);
                } else {
                    menuContainer.setVisibility(View.INVISIBLE);
                    chevron.setVisibility(View.VISIBLE);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(BLOC_FRAG, "Bloc onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(BLOC_FRAG, "Bloc onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(BLOC_FRAG, "Bloc onDestroy");
    }
}
