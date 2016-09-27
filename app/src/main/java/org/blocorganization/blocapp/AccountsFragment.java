package org.blocorganization.blocapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountsFragment extends Fragment {


    public AccountsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accounts, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("FRAG", "Accounts onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("FRAG", "Accounts onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("FRAG", "Accounts onDestroy");
    }

}
