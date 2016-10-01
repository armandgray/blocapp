package org.blocorganization.blocapp.bloc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.blocorganization.blocapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlocSubFragment extends Fragment {

    public static final String BLOC_FRAG = "BLOC_FRAG";

    public BlocSubFragment() {
        // Required empty public constructor
    }

    public static BlocSubFragment newInstance() {

        Bundle args = new Bundle();

        BlocSubFragment fragment = new BlocSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    ImageView chevronDown;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.subfragment_bloc, container, false);

        // cannot get height of parent container
//        RelativeLayout canvas = (RelativeLayout) view.findViewById(R.id.main_layout);
        int targetY = 1700;

//        chevronDown = (ImageView) rootView.findViewById(R.id.menu_bullet1);
//
//        ObjectAnimator animator = ObjectAnimator.ofFloat(chevronDown, "y", 1650, targetY).setDuration(700);
//        animator.setInterpolator(new BounceInterpolator());
//        animator.start();

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
