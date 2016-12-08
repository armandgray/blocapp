package org.blocorganization.blocapp.campaigns;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Campaign;

import java.util.ArrayList;
import java.util.List;

import static org.blocorganization.blocapp.utils.DateTimeFormatHandler.setTextForDateWith;

public class CampaignDetailActivity extends AppCompatActivity {

    private Campaign campaign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_detail);

        LinearLayout itemInfoThemeContainer = (LinearLayout) findViewById(R.id.itemInfoThemeContainer);
        ImageView ivCampaignImage = (ImageView) findViewById(R.id.ivCampaignImage);
        ImageView ivTheme = (ImageView) findViewById(R.id.ivTheme);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        TextView tvType = (TextView) findViewById(R.id.tvType);
        TextView tvFromDate = (TextView) findViewById(R.id.tvFromDate);
        TextView tvToDate = (TextView) findViewById(R.id.tvToDate);
        tvToDate.setVisibility(View.INVISIBLE);

        LinearLayout itemAdminContainer = (LinearLayout) findViewById(R.id.itemAdminContainer);
        ImageView ivAdminImage = (ImageView) findViewById(R.id.ivAdminImage);
        TextView tvAdminHeader = (TextView) findViewById(R.id.tvAdminHeader);
        TextView tvAdminName = (TextView) findViewById(R.id.tvAdminName);
        TextView tvAdminRole = (TextView) findViewById(R.id.tvAdminRole);

        TextView tvDesc = (TextView) findViewById(R.id.tvDesc);
        TextView tvAmbition = (TextView) findViewById(R.id.tvAmbition);
        TextView tvBenefits = (TextView) findViewById(R.id.tvBenefits);
        TextView tvPlan = (TextView) findViewById(R.id.tvPlan);
        TextView tvBudget = (TextView) findViewById(R.id.tvBudget);

        final List<View> views = new ArrayList<>();
        views.add(itemInfoThemeContainer);
        views.add(itemAdminContainer);
        views.add(tvDesc);
        views.add(tvAmbition);
        views.add(tvBenefits);
        views.add(tvPlan);
        views.add(tvBudget);

        if (getIntent().getExtras() != null) {
            campaign = new Campaign(getIntent().getExtras());
            Picasso.with(this).load(campaign.getPhotoUrl()).into(ivCampaignImage);
            Picasso.with(this).load(campaign.getThemeImageUrl()).into(ivTheme);
            tvTitle.setText(campaign.getTitle());
            tvType.setText(campaign.getRecordType());
            setTextForDateWith(campaign.getFromDate(), tvFromDate, true);
            setTextForDateWith(campaign.getFromDate(), tvToDate, false);
            tvDesc.setText(campaign.getDescription());
            tvAmbition.setText(campaign.getAmbition());
            tvBenefits.setText(campaign.getBenefits());
            tvPlan.setText(campaign.getPlanOfExecution());
            tvBudget.setText(campaign.getItemizedBudget());
        }

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        final ImageView editBtn = (ImageView) findViewById(R.id.ivCampaignEdit);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (campaign != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .add(android.R.id.content, CreateCampaignDialog.withCampaign(campaign))
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        final LinearLayout interestedBtn = (LinearLayout) findViewById(R.id.btn_container_red);
        final ImageView interestedBtnImage = (ImageView) interestedBtn.getChildAt(0);
        interestedBtnImage.setImageResource(R.drawable.ic_star_white_48dp);
        final TextView interestedBtnText = (TextView) interestedBtn.getChildAt(1);
        interestedBtnText.setText("Maybe");
        interestedBtn.setOnClickListener(new View.OnClickListener() {
            boolean clicked;

            @Override
            public void onClick(View view) {
                interestedBtnText.setText("Going");
                if (clicked) {
                    interestedBtnImage.setImageResource(R.drawable.ic_check_white_48dp);
                    clicked = !clicked;
                } else {
                    interestedBtnImage.setImageResource(R.drawable.ic_plus_white_48dp);
                    clicked = !clicked;
                }
            }
        });

        LinearLayout shareBtn = (LinearLayout) findViewById(R.id.btn_container_gray);
        final ImageView shareBtnImage = (ImageView) shareBtn.getChildAt(0);
        shareBtnImage.setImageResource(R.drawable.ic_share_grey600_48dp);
        final TextView shareBtnText = (TextView) shareBtn.getChildAt(1);
        shareBtnText.setText("Share");

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareBtnImage.setImageResource(R.drawable.ic_check_grey600_48dp);
            }
        });

        final LinearLayout more = (LinearLayout) findViewById(R.id.more_container);
        final LinearLayout details = (LinearLayout) findViewById(R.id.campaign_details_container);
        details.setVisibility(View.GONE);
        more.setOnClickListener(new View.OnClickListener() {
            boolean clicked;
            TextView tv = (TextView) more.getChildAt(0);
            ImageView iv = (ImageView) more.getChildAt(1);

            @Override
            public void onClick(View view) {
                if (clicked) {
                    details.setVisibility(View.GONE);
                    tv.setText("More");
                    iv.animate().rotationX(360).rotationY(0).setDuration(250);
                    clicked = !clicked;
                } else {
                    details.setVisibility(View.VISIBLE);
                    tv.setText("Less");
                    iv.animate().rotationX(180).rotationY(0).setDuration(250);
                    clicked = !clicked;
                }
            }
        });
    }

}
