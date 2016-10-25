package org.blocorganization.blocapp.campaigns;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.utils.GestureListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignsFragment extends Fragment {

    FragmentPagerAdapter adapterViewPager;
    static LinearLayout menu;
    static View selectedBullet;

    // Swipe Gesture Fields
    private GestureDetector gestureDetector;

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
        gestureDetector = new GestureDetector(getActivity(), new GestureListener(rootView, R.id.menu_container, R.id.menu_selected_bullet));
        vpPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });

        adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        menu = (LinearLayout) rootView.findViewById(R.id.menu_container);
        selectedBullet = menu.getChildAt(4);
        menu.removeView(menu.getChildAt(3));
        menu.removeView(menu.getChildAt(3));
        menu.addView(selectedBullet, 1);

        vpPager.setCurrentItem(1);

        return rootView;
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
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
