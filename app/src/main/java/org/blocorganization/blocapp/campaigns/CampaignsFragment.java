package org.blocorganization.blocapp.campaigns;


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
public class CampaignsFragment extends Fragment {

    public CampaignsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_campaigns, container, false);

        ViewPager vpPager = (ViewPager) rootView.findViewById(R.id.campaigns_vpager);
        FragmentPagerAdapter adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(0);

        ImageView menuBullet0 = (ImageView) rootView.findViewById(R.id.menu_bullet0);
        menuBullet0.setImageResource(R.drawable.menu_selected_point);

        return rootView;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        private static final int NUM_PAGES = 1;

        MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

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

}
