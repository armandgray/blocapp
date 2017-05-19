package org.blocorganization.blocapp;


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

import com.squareup.picasso.Picasso;

import org.blocorganization.blocapp.campaigns.CampaignDetailActivity;
import org.blocorganization.blocapp.models.Campaign;
import org.blocorganization.blocapp.utils.CampaignsItemAdapter;
import org.blocorganization.blocapp.utils.RecyclerItemClickListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment
        implements FirebaseCampaignsHelper.FirebaseCampaignsListener {

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private View rootView;
    private ViewFlipper viewFlipper;
    private GestureDetector detector;

    private RecyclerView rvCampaigns;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        setupMoreButtons(rootView);

        viewFlipper = (ViewFlipper) rootView.findViewById(R.id.view_flipper);
        setupViewFlipperImages();
        setupViewFlipper();

        setupRvCampaignVisibilityOptions();
        setupRvCampaigns(rvCampaigns, getFirebaseCampaigns());

        return rootView;
    }

    private void setupViewFlipperImages() {
        ImageView image;
        int[] imageIdArray = {
                R.drawable.logo_negative,
                R.drawable.blocbbq2016_ianwbx,
                R.drawable.theme_consent,
                R.drawable.dinner_jmnuad,
                R.drawable.theme_donate
        };
        for (int i = 0; i < viewFlipper.getChildCount(); i++) {
            image = (ImageView) ((RelativeLayout) viewFlipper.getChildAt(i)).getChildAt(0);
            Picasso.with(getActivity()).load(imageIdArray[i]).into(image);
        }
    }

    @SuppressWarnings("deprecation")
    private void setupViewFlipper() {
        detector = new GestureDetector(new SwipeGestureDetector());
        viewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(7000);
        viewFlipper.startFlipping();
    }

    private void setupRvCampaignVisibilityOptions() {
        rvCampaigns = (RecyclerView) rootView.findViewById(R.id.rvCampaigns);

        LinearLayout showMoreButton = (LinearLayout) rootView.findViewById(R.id.more_container);
        showMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvCampaigns.scrollToPosition(rvCampaigns.getChildCount() - 1);
            }
        });

        RelativeLayout moreCampaignsContainer = (RelativeLayout) rootView.findViewById(R.id.moreCampaignsContainer);
        int visibility = getFirebaseCampaigns().size() > 0 ? View.VISIBLE : View.GONE;
        moreCampaignsContainer.setVisibility(visibility);
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

    private void setupRvCampaigns(RecyclerView rvCampaigns, final List<Campaign> campaigns) {
        CampaignsItemAdapter adapter = new CampaignsItemAdapter(getActivity(), false, campaigns);
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
                    viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.left_in));
                    viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.left_out));
                    viewFlipper.showNext();
                    viewFlipper.stopFlipping();
                    return true;
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.right_in));
                    viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.right_out));
                    viewFlipper.showPrevious();
                    viewFlipper.stopFlipping();
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
