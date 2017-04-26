package org.blocorganization.blocapp.campaigns;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.blocorganization.blocapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignsFragment extends Fragment
            implements HomeSubFragment.HomeFragListener {

    private FragmentPagerAdapter adapterViewPager;
    private ViewPager vpPager;
    private LinearLayout menu;
    private ImageView menuBullet0;

    // Swipe Gesture Fields
    private GestureDetector gestureDetector;

    private FragmentManager mFragmentManager;

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
        vpPager = (ViewPager) rootView.findViewById(R.id.campaigns_vpager);
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(0);

        menu = (LinearLayout) rootView.findViewById(R.id.menu_container);
        menuBullet0 = (ImageView) menu.getChildAt(0);
        menuBullet0.setImageResource(R.drawable.menu_selected_point);

        return rootView;
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private static final int NUM_PAGES = 1;

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
                    return CampaignsSubFragment.newInstance();
                default:
                    return null;
            }
        }
    }

    @Override
    public void onCalendarImageClick() {
        vpPager.setCurrentItem(0);
    }

    @Override
    public void onHomeCreate() {

    }

}
