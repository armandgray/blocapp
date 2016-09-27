package org.blocorganization.blocapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class BullhornFragment extends Fragment {

    public BullhornFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bullhorn, container, false);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("FRAG", "Bullhorn onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("FRAG", "Bullhorn onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("FRAG", "Bullhorn onDestroy");
    }

}
