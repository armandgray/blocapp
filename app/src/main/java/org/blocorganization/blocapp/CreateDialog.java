package org.blocorganization.blocapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.blocorganization.blocapp.bloc.FamilySubFragment;
import org.blocorganization.blocapp.bloc.MissionSubFragment;

public class CreateDialog extends DialogFragment {

    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.create_dialog, container, false);

        TextView tvSubmitDialog = (TextView) rootView.findViewById(R.id.tvSubmitDialog);
        tvSubmitDialog.setText(R.string.create_resource);

        ViewPager vpPager = getViewPager(rootView);
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return rootView;
    }

    @NonNull
    private ViewPager getViewPager(View rootView) {
        ViewPager vpPager = (ViewPager) rootView.findViewById(R.id.vpCreateDialog);
        MyPagerAdapter adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(1);
        return vpPager;
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private static final int NUM_PAGES = 3;

        MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            CreateDialog.this.fragmentManager = fragmentManager;
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
                    return org.blocorganization.blocapp.bloc.HomeSubFragment.newInstance();
                default:
                    return null;
            }
        }
    }

}
