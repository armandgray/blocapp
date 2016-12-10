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

    public static final String SELECTED_DIALOG_NUM = "SELECTED_DIALOG_NUM";
    private FragmentManager fragmentManager;
    private TextView tvSubmitDialog;
    private TextView tvSelectorDetails;
    private ImageView ivResourceSelector;
    private TextView tvResourceSelector;
    private ImageView ivMeetupSelector;
    private TextView tvMeetupSelector;
    private ImageView ivMessageSelector;
    private TextView tvMessageSelector;
    private ViewPager vpPager;

    public static CreateDialog newMessage() {

        Bundle args = new Bundle();
        args.putInt(SELECTED_DIALOG_NUM, 0);
        CreateDialog fragment = new CreateDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public static CreateDialog newMeetup() {

        Bundle args = new Bundle();
        args.putInt(SELECTED_DIALOG_NUM, 1);
        CreateDialog fragment = new CreateDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public static CreateDialog newResource() {

        Bundle args = new Bundle();
        args.putInt(SELECTED_DIALOG_NUM, 2);
        CreateDialog fragment = new CreateDialog();
        fragment.setArguments(args);
        return fragment;
    }

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
                        setupMeetupFragment();
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
        RelativeLayout meetupSelectorLayout = (RelativeLayout) rootView.findViewById(R.id.meetupSelectorLayout);
        ivMeetupSelector = (ImageView) meetupSelectorLayout.getChildAt(0);
        tvMeetupSelector = (TextView) meetupSelectorLayout.getChildAt(1);
        RelativeLayout messagesSelectorLayout = (RelativeLayout) rootView.findViewById(R.id.messagesSelectorLayout);
        ivMessageSelector = (ImageView) messagesSelectorLayout.getChildAt(0);
        tvMessageSelector = (TextView) messagesSelectorLayout.getChildAt(1);
        RelativeLayout resourcesSelectorLayout = (RelativeLayout) rootView.findViewById(R.id.resourcesSelectorLayout);
        ivResourceSelector = (ImageView) resourcesSelectorLayout.getChildAt(0);
        tvResourceSelector = (TextView) resourcesSelectorLayout.getChildAt(1);
    }

    @NonNull
    private ViewPager getViewPager(View rootView) {
        vpPager = (ViewPager) rootView.findViewById(R.id.vpCreateDialog);
        MyPagerAdapter adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        setVpFrom(getArguments());

        return vpPager;
    }

    private void setVpFrom(Bundle arguments) {
        if (arguments != null) {
            switch (arguments.getInt(SELECTED_DIALOG_NUM)) {
                case 0:
                    vpPager.setCurrentItem(0);
                    setupMessageFragment();
                    return;
                case 1:
                    vpPager.setCurrentItem(1);
                    setupMeetupFragment();
                    return;
                case 2:
                    vpPager.setCurrentItem(2);
                    setupResourceFragment();
                    return;
                default:
                    vpPager.setCurrentItem(1);
                    setupMeetupFragment();
            }
        } else {
            vpPager.setCurrentItem(1);
            setupMeetupFragment();
        }

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
                    return CreateMeetupFragment.newInstance();
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
        showSelectedColors(ivMessageSelector, tvMessageSelector);
        showDeselectedColors(ivResourceSelector, tvResourceSelector);
        showDeselectedColors(ivMeetupSelector, tvMeetupSelector);
    }

    private void setupMeetupFragment() {
        tvSubmitDialog.setText(R.string.create_meetup);
        tvSelectorDetails.setText(R.string.meetup_motto);
        showSelectedColors(ivMeetupSelector, tvMeetupSelector);
        showDeselectedColors(ivResourceSelector, tvResourceSelector);
        showDeselectedColors(ivMessageSelector, tvMessageSelector);
    }

    private void setupResourceFragment() {
        tvSubmitDialog.setText(R.string.create_resource);
        tvSelectorDetails.setText(R.string.resource_motto);
        showSelectedColors(ivResourceSelector, tvResourceSelector);
        showDeselectedColors(ivMessageSelector, tvMessageSelector);
        showDeselectedColors(ivMeetupSelector, tvMeetupSelector);
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
