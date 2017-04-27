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

import org.blocorganization.blocapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlocFragment extends Fragment implements HomeSubFragment.HomeScrollListener {

    private FragmentPagerAdapter adapterViewPager;
    private ImageView menuBullet0;

    public BlocFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bloc, container, false);

        ViewPager vpPager = (ViewPager) rootView.findViewById(R.id.bloc_vpager);
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(0);

        menuBullet0 = (ImageView) rootView.findViewById(R.id.menu_bullet0);
        menuBullet0.setImageResource(R.drawable.menu_selected_point);

        return rootView;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        private static final int NUM_PAGES = 1;

        MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
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
                    return HomeSubFragment.newInstance();
                default:
                    return null;
            }
        }
    }

    @Override
    public void onScrollTop() {
        menuBullet0.setVisibility(View.VISIBLE);
    }

    @Override
    public void onScrollDown() {
        menuBullet0.setVisibility(View.GONE);
    }


}
