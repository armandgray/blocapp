package org.blocorganization.blocapp;

import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CreateDialog extends DialogFragment {

    private FragmentManager fragmentManager;
    private TextView tvSubmitDialog;
    private TextView tvSelectorDetails;
    private RelativeLayout campaignSelectorLayout;
    private RelativeLayout messagesSelectorLayout;
    private RelativeLayout resourcesSelectorLayout;
    private ImageView ivResourceSelector;
    private TextView tvResourceSelector;
    private ImageView ivCampaignSelector;
    private TextView tvCampaignSelector;
    private ImageView ivMessageSelector;
    private TextView tvMessageSelector;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.create_dialog, container, false);

        assignLayoutsViewsFrom(rootView);

        ViewPager vpPager = getViewPager(rootView);
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setupMessageFragment();
                        return;
                    case 1:
                        setupCampaignFragment();
                        return;
                    case 2:
                        setupResourceFragment();
                        return;
                    default:
                        setupResourceFragment();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return rootView;
    }

    private void assignLayoutsViewsFrom(View rootView) {
        tvSubmitDialog = (TextView) rootView.findViewById(R.id.tvSubmitDialog);
        tvSelectorDetails = (TextView) rootView.findViewById(R.id.tvSelectorDetails);
        campaignSelectorLayout = (RelativeLayout) rootView.findViewById(R.id.campaignSelectorLayout);
        ivCampaignSelector = (ImageView) campaignSelectorLayout.getChildAt(0);
        tvCampaignSelector = (TextView) campaignSelectorLayout.getChildAt(1);
        messagesSelectorLayout = (RelativeLayout) rootView.findViewById(R.id.messagesSelectorLayout);
        ivMessageSelector = (ImageView) messagesSelectorLayout.getChildAt(0);
        tvMessageSelector = (TextView) messagesSelectorLayout.getChildAt(1);
        resourcesSelectorLayout = (RelativeLayout) rootView.findViewById(R.id.resourcesSelectorLayout);
        ivResourceSelector = (ImageView) resourcesSelectorLayout.getChildAt(0);
        tvResourceSelector = (TextView) resourcesSelectorLayout.getChildAt(1);
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
                    return CreateMessageFragment.newInstance();
                case 1:
                    return CreateCampaignFragment.newInstance();
                case 2:
                    return CreateResourceFragment.newInstance();
                default:
                    return null;
            }
        }
    }

    private void setupMessageFragment() {
        tvSubmitDialog.setText(R.string.create_talking_point);
        tvSelectorDetails.setText(R.string.messages_motto);
    }

    private void setupCampaignFragment() {
        tvSubmitDialog.setText(R.string.create_new_campaign);
        tvSelectorDetails.setText(R.string.campaign_motto);
    }

    private void setupResourceFragment() {
        tvSubmitDialog.setText(R.string.create_resource);
        tvSelectorDetails.setText(R.string.resource_motto);
    }

    private void showDeselectedColors(ImageView imageView, TextView textView) {
        imageView.setColorFilter(Color.parseColor("#333333"));
        imageView.setBackgroundResource(R.drawable.create_dialog_circle);
        textView.setTextColor(Color.parseColor("#333333"));
    }

    private void showSelectedColors(ImageView imageView, TextView textView) {
        imageView.setColorFilter(Color.parseColor("#FF2A00"));
        imageView.setBackgroundResource(R.drawable.create_dialog_circle_selected);
        textView.setTextColor(Color.parseColor("#FF2A00"));

    }

}
