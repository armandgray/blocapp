package org.blocorganization.blocapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import org.blocorganization.blocapp.campaigns.CampaignDetailActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static final String HOME_TAG = "HOME_TAG";

    // View Flipper constants & handlers
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private ViewFlipper mViewFlipper;
    private Context mContext;
    private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        /**
         *  Slideshow setup as ViewFlipper
         *  Source: http://stacktips.com/tutorials/android/android-viewflipper-example
         *  gesture listener below
         */
        mContext = getActivity();
        mViewFlipper = (ViewFlipper) rootView.findViewById(R.id.view_flipper);
        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });

        LinearLayout campaignBtn = (LinearLayout) rootView.findViewById(R.id.campaignItemContainer);
        campaignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CampaignDetailActivity.class);
                startActivity(intent);
            }
        });

        // Begin Slideshow; Stops auto flip on swipe below.
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(7000);
        mViewFlipper.startFlipping();

        // add animation listener to viewflipper
//        mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);

        return rootView;
    }

    /**
     *  ViewFlipper gesture listener
     */
    class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
                    mViewFlipper.showNext();
                    mViewFlipper.stopFlipping();
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
                    mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.right_out));
                    mViewFlipper.showPrevious();
                    mViewFlipper.stopFlipping();
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }

    //animation listener
//    Animation.AnimationListener mAnimationListener = new Animation.AnimationListener() {
//        public void onAnimationStart(Animation animation) {
//            //animation started event
//        }
//
//        public void onAnimationRepeat(Animation animation) {
//        }
//
//        public void onAnimationEnd(Animation animation) {
//            //TODO animation stopped event
//        }
//    };

}
