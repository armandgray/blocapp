package org.blocorganization.blocapp.campaigns;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.blocorganization.blocapp.R;

import static org.blocorganization.blocapp.campaigns.CreateGeneralSubFragment.CREATE_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignsFragment extends Fragment {

    FragmentPagerAdapter adapterViewPager;

    private static FragmentManager mFragmentManager;
    private static Fragment createSubFrag;

    public CampaignsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_campaigns, container, false);

        /**
         *  View Pager - Follow Link for more dynamic options
         *  https://guides.codepath.com/android/ViewPager-with-FragmentPagerAdapter
         */
        ViewPager vpPager = (ViewPager) rootView.findViewById(R.id.campaigns_vpager);
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(1);

        return rootView;
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static final int NUM_PAGES = 4;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            mFragmentManager = fragmentManager;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ActionboardSubFragment.newInstance();
                case 1:
                    return HomeSubFragment.newInstance();
                case 2:
                    return CampaignsSubFragment.newInstance();
                case 3:
                    return CreateSubFragment.newInstance();
                default:
                    return null;
            }
        }
    }

}
