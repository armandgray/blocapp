package org.blocorganization.blocapp.bloc;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.blocorganization.blocapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlocFragment extends Fragment implements HomeSubFragment.HomeScrollListener {

    private FragmentManager mFragmentManager;
    FragmentPagerAdapter adapterViewPager;
    private LinearLayout menu;
    private ImageView menuBullet0;
    private ImageView menuBullet1;
    private ImageView menuBullet2;
    private ImageView menuBullet3;
    private ImageView menuBullet4;

    public BlocFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bloc, container, false);

        /**
         *  View Pager - Follow Link for more dynamic options
         *  https://guides.codepath.com/android/ViewPager-with-FragmentPagerAdapter
         */
        ViewPager vpPager = (ViewPager) rootView.findViewById(R.id.bloc_vpager);
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(2);

        menu = (LinearLayout) rootView.findViewById(R.id.menu_container);
        menuBullet0 = (ImageView) menu.getChildAt(0);
        menuBullet1 = (ImageView) menu.getChildAt(1);
        menuBullet2 = (ImageView) menu.getChildAt(2);
        menuBullet3 = (ImageView) menu.getChildAt(3);
        menuBullet4 = (ImageView) menu.getChildAt(4);
        menuBullet2.setImageResource(R.drawable.menu_selected_point);

        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        menuBullet1.setImageResource(R.drawable.menu_point);
                        menuBullet0.setImageResource(R.drawable.menu_selected_point);
                        return;
                    case 1:
                        menuBullet0.setImageResource(R.drawable.menu_point);
                        menuBullet2.setImageResource(R.drawable.menu_point);
                        menuBullet1.setImageResource(R.drawable.menu_selected_point);
                        return;
                    case 2:
                        menuBullet1.setImageResource(R.drawable.menu_point);
                        menuBullet3.setImageResource(R.drawable.menu_point);
                        menuBullet2.setImageResource(R.drawable.menu_selected_point);
                        return;
                    case 3:
                        menuBullet2.setImageResource(R.drawable.menu_point);
                        menuBullet4.setImageResource(R.drawable.menu_point);
                        menuBullet3.setImageResource(R.drawable.menu_selected_point);
                        return;
                    case 4:
                        menuBullet3.setImageResource(R.drawable.menu_point);
                        menuBullet4.setImageResource(R.drawable.menu_selected_point);
                        return;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return rootView;
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private static final int NUM_PAGES = 5;

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
                    return MissionSubFragment.newInstance();
                case 1:
                    return FamilySubFragment.newInstance();
                case 2:
                    return HomeSubFragment.newInstance();
                case 3:
                    return RolesSubFragment.newInstance();
                case 4:
                    return ResourcesSubFragment.newInstance();
                default:
                    return null;
            }
        }
    }

    @Override
    public void onScrollTop() {
        menu.setVisibility(View.VISIBLE);
    }

    @Override
    public void onScrollDown() {
        menu.setVisibility(View.GONE);
    }


}
