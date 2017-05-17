package org.blocorganization.blocapp;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.blocorganization.blocapp.campaigns.CampaignDetailActivity;
import org.blocorganization.blocapp.models.Campaign;
import org.blocorganization.blocapp.utils.CampaignsItemAdapter;
import org.blocorganization.blocapp.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
        implements FirebaseCampaignsHelper.FirebaseCampaignsListener {

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    
    private View rootView;
    private ViewFlipper mViewFlipper;
    private Context mContext;
    private GestureDetector detector;

    private CampaignsItemAdapter adapter;
    private final ArrayList<Campaign> campaigns = new ArrayList<>();
    private RecyclerView rvCampaigns;

    @SuppressWarnings("deprecation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        setupMoreButtons(rootView);

        mContext = getActivity();
        detector = new GestureDetector(new SwipeGestureDetector());
        mViewFlipper = (ViewFlipper) rootView.findViewById(R.id.view_flipper);
        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(7000);
        mViewFlipper.startFlipping();

        rvCampaigns = (RecyclerView) rootView.findViewById(R.id.rvCampaigns);

        LinearLayout showMoreButton = (LinearLayout) rootView.findViewById(R.id.more_container);
        showMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvCampaigns.scrollToPosition(rvCampaigns.getChildCount() - 1);
            }
        });
        
        return rootView;
    }

    private void setupMoreButtons(View rootView) {
        ImageView moreButtonArrow = (ImageView) rootView.findViewById(R.id.ivChevron);
        ImageView missionMoreButton = (ImageView) rootView.findViewById(R.id.missionContainer)
                .findViewById(R.id.ivChevron);
        missionMoreButton.setColorFilter(Color.parseColor("#FF2A00"));
        moreButtonArrow.setColorFilter(Color.parseColor("#FF2A00"));
        moreButtonArrow.setRotation(270);

        TextView tvMore = (TextView) rootView.findViewById(R.id.tvMore);
        TextView tvMoreMission = (TextView) rootView.findViewById(R.id.missionContainer)
                .findViewById(R.id.tvMore);
        tvMore.setTextColor(Color.parseColor("#FF2A00"));
        tvMoreMission.setTextColor(Color.parseColor("#FF2A00"));
    }

    @Override
    public void onResume() {
        super.onResume();
        final RelativeLayout moreCampaignsContainer = (RelativeLayout) rootView.findViewById(R.id.moreCampaignsContainer);
        moreCampaignsContainer.setVisibility(View.GONE);
        moreCampaignsContainer.setVisibility(View.VISIBLE);

        setupRvCampaigns(rvCampaigns);
    }

    private void setupRvCampaigns(RecyclerView rvCampaigns) {
        adapter = new CampaignsItemAdapter(getActivity(), false, campaigns);
        rvCampaigns.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvCampaigns.setLayoutManager(layoutManager);

        rvCampaigns.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), CampaignDetailActivity.class);
                        intent.putExtras(campaigns.get(position).toBundle());
                        startActivity(intent);
                    }
                })
        );
    }

    @Override
    public List<Campaign> getFirebaseCampaigns() {
        return FirebaseCampaignsHelper.getInstance().getCampaigns();
    }

    @Override
    public int getFirebaseCampaignPosition(Campaign campaign) {
        return FirebaseCampaignsHelper.getInstance().getCampaignPosition(campaign);
    }

    private class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
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
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    // TODO add detector to rvNewsFeed and code below to handle flings
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }
    }

}
