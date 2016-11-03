package org.blocorganization.blocapp;


import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.blocorganization.blocapp.campaigns.CampaignDetailActivity;
import org.blocorganization.blocapp.models.Campaign;
import org.blocorganization.blocapp.models.CommunityEngagementCampaign;
import org.blocorganization.blocapp.utils.CampaignsItemAdapter;
import org.blocorganization.blocapp.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Map;

import static org.blocorganization.blocapp.campaigns.CampaignsSubFragment.CAMPAIGNS_CHILD;

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
    private DatabaseReference mCampaignsDatabaseReference;
    private CampaignsItemAdapter adapter;

    ArrayList<Campaign> campaigns;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        TextView tvMore = (TextView) rootView.findViewById(R.id.tvMore);
        tvMore.setTextColor(getResources().getColor(R.color.ToolBarColor));
        ImageView ivChevron = (ImageView) rootView.findViewById(R.id.ivChevron);
        ivChevron.setColorFilter(getResources().getColor(R.color.ToolBarColor));
        ivChevron.setRotation(270);

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

        // Begin Slideshow; Stops auto flip on swipe below.
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(7000);
        mViewFlipper.startFlipping();

        // add animation listener to viewflipper
//        mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);

        final RecyclerView rvCampaigns = (RecyclerView) rootView.findViewById(R.id.rvCampaigns);
        campaigns = new ArrayList<>();

        LinearLayout moreContainer = (LinearLayout) rootView.findViewById(R.id.more_container);
        moreContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvCampaigns.scrollToPosition(rvCampaigns.getChildCount() - 1);
            }
        });

        mCampaignsDatabaseReference = FirebaseDatabase.getInstance().getReference(CAMPAIGNS_CHILD);
        mCampaignsDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, String> campaignsSnapshot = (Map) dataSnapshot.getValue();
                final CommunityEngagementCampaign campaign = new CommunityEngagementCampaign();

                campaign.setAbbreviation(campaignsSnapshot.get("abbreviation"));
                campaign.setTitle(campaignsSnapshot.get("title"));
                campaign.setAdmin(campaignsSnapshot.get("admin"));
                campaign.setDescription(campaignsSnapshot.get("description"));
                campaign.setBenefits(campaignsSnapshot.get("benefits"));
                campaign.setAmbition(campaignsSnapshot.get("admin"));
                campaign.setPlanOfExecution(campaignsSnapshot.get("planOfExecution"));
                campaign.setItemizedBudget(campaignsSnapshot.get("itemizedBudget"));
                campaign.setVenue(campaignsSnapshot.get("venue"));
                campaign.setDate(campaignsSnapshot.get("date"));
                campaign.setTime(campaignsSnapshot.get("time"));
                campaign.setRecordType(campaignsSnapshot.get("recordType"));
                campaign.setExtras(campaignsSnapshot.get("extras"));
                campaign.setMonth(campaignsSnapshot.get("month"));
                campaign.setCampaignPhoto(campaignsSnapshot.get("campaignPhoto"));
                campaign.setCampaignTheme(campaignsSnapshot.get("campaignTheme"));

                campaigns.add(0, campaign);
                adapter.notifyItemInserted(0);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        adapter = new CampaignsItemAdapter(getActivity(), false, campaigns);
        rvCampaigns.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvCampaigns.setLayoutManager(layoutManager);
        
        rvCampaigns.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), CampaignDetailActivity.class);
                        intent.putExtras(campaigns.get(position).toBundle());
                        startActivity(intent);
                    }
                })
        );

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
