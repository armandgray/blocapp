package org.blocorganization.blocapp.campaigns;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Campaign;
import org.blocorganization.blocapp.utils.SaveChangesDialogFragment;

import java.util.ArrayList;
import java.util.List;

import static org.blocorganization.blocapp.campaigns.CampaignsSubFragment.NEW_CAMPAIGN_TAG;

public class CampaignDetailActivity extends AppCompatActivity
        implements CreateInfoDialog.DialogEndedListener {

    public static final String DIALOG = "DIALOG";

    DisplayMetrics metrics;
    Boolean campaignEdited = false;
    ImageView ivCampaignImage;
    ImageView ivTheme;
    TextView tvTitle;
    TextView tvType;
    TextView tvDate;

    ImageView ivAdminImage;
    TextView tvAdminHeader;
    TextView tvAdminName;
    TextView tvAdminRole;

    TextView tvDesc;
    TextView tvAmbition;
    TextView tvBenefits;
    TextView tvPlan;
    TextView tvBudget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_detail);

        LinearLayout itemInfoThemeContainer = (LinearLayout) findViewById(R.id.itemInfoThemeContainer);
        ivCampaignImage = (ImageView) findViewById(R.id.ivCampaignImage);
        ivTheme = (ImageView) findViewById(R.id.ivTheme);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvType = (TextView) findViewById(R.id.tvType);
        tvDate = (TextView) findViewById(R.id.tvDate);

        LinearLayout itemAdminContainer = (LinearLayout) findViewById(R.id.itemAdminContainer);
        ivAdminImage = (ImageView) findViewById(R.id.ivAdminImage);
        tvAdminHeader = (TextView) findViewById(R.id.tvAdminHeader);
        tvAdminName = (TextView) findViewById(R.id.tvAdminName);
        tvAdminRole = (TextView) findViewById(R.id.tvAdminRole);

        tvDesc = (TextView) findViewById(R.id.tvDesc);
        tvAmbition = (TextView) findViewById(R.id.tvAmbition);
        tvBenefits = (TextView) findViewById(R.id.tvBenefits);
        tvPlan = (TextView) findViewById(R.id.tvPlan);
        tvBudget = (TextView) findViewById(R.id.tvBudget);

        final List<View> views = new ArrayList<>();
        views.add(itemInfoThemeContainer);
        views.add(itemAdminContainer);
        views.add(tvDesc);
        views.add(tvAmbition);
        views.add(tvBenefits);
        views.add(tvPlan);
        views.add(tvBudget);

        if (getIntent().getExtras() != null) {
            if (getIntent().getBooleanExtra(NEW_CAMPAIGN_TAG, false)) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .add(android.R.id.content, new CreateInfoDialog())
                        .addToBackStack(null)
                        .commit();
                Toast.makeText(this, "NEW CAMPAIGN", Toast.LENGTH_SHORT).show();
            } else {
                Campaign campaign = new Campaign(getIntent().getExtras());
                Picasso.with(this).load(campaign.getCampaignPhoto()).into(ivCampaignImage);
                Picasso.with(this).load(campaign.getCampaignTheme()).into(ivTheme);
                tvTitle.setText(campaign.getTitle());
                tvType.setText(campaign.getRecordType());
                tvDate.setText(campaign.getDate());
                tvDesc.setText(campaign.getDescription());
                tvAmbition.setText(campaign.getAmbition());
                tvBenefits.setText(campaign.getBenefits());
                tvPlan.setText(campaign.getPlanOfExecution());
                tvBudget.setText(campaign.getItemizedBudget());
            }
        }

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        final ImageView editBtn = (ImageView) findViewById(R.id.ivCampaignEdit);
        editBtn.setOnClickListener(new View.OnClickListener() {
            boolean clicked;
            int translationX = 100;

            @Override
            public void onClick(View view) {
                if (clicked) {
                    if (campaignEdited) {
                        new SaveChangesDialogFragment()
                                .show(getSupportFragmentManager(), DIALOG);
                        campaignEdited = false;
                    }
                    clicked = !clicked;
                } else {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .add(android.R.id.content, new CreateInfoDialog())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        final LinearLayout interestedBtn = (LinearLayout) findViewById(R.id.btn_container_red);
        final ImageView interestedBtnImage = (ImageView) interestedBtn.getChildAt(0);
        interestedBtnImage.setImageResource(R.drawable.ic_star_white_48dp);
        final TextView interestedBtnText = (TextView) interestedBtn.getChildAt(1);
        interestedBtnText.setText("Interested");
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
                    iv.animate().rotationX(360).rotationY(0).setDuration(1000);
                    clicked = !clicked;
                } else {
                    details.setVisibility(View.VISIBLE);
                    tv.setText("Less");
                    iv.animate().rotationX(180).rotationY(0).setDuration(1000);
                    clicked = !clicked;
                }
            }
        });
    }

    public static int getDPI(int size, DisplayMetrics metrics){
        return (size * metrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT;
    }

    @Override
    public void onDialogEnded(String title) {
        tvTitle.setText(title);
    }
}
