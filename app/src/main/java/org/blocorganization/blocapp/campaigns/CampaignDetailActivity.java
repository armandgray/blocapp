package org.blocorganization.blocapp.campaigns;

import android.os.Bundle;
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
            Campaign campaign = new Campaign(getIntent().getExtras());
            Toast.makeText(this, campaign.getCampaignTheme(), Toast.LENGTH_SHORT).show();
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

        final ImageView cbInfo = (ImageView) findViewById(R.id.cbInfo);
        final ImageView cbAdmin = (ImageView) findViewById(R.id.cbAdmin);
        final ImageView cbDesc = (ImageView) findViewById(R.id.cbDesc);
        final ImageView cbAmbition = (ImageView) findViewById(R.id.cbAmbition);
        final ImageView cbBenefits = (ImageView) findViewById(R.id.cbBenefits);
        final ImageView cbPlan = (ImageView) findViewById(R.id.cbPlan);
        final ImageView cbBudget = (ImageView) findViewById(R.id.cbBudget);

        final List<ImageView> checkboxes = new ArrayList<>();
        checkboxes.add(cbInfo);
        checkboxes.add(cbAdmin);
        checkboxes.add(cbDesc);
        checkboxes.add(cbAmbition);
        checkboxes.add(cbBenefits);
        checkboxes.add(cbPlan);
        checkboxes.add(cbBudget);

        for (ImageView cb : checkboxes) {
            cb.setVisibility(View.GONE);
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
                    editBtn.setImageResource(R.drawable.ic_pencil_box_outline_white_48dp);

                    for (View v : views) { v.animate().translationXBy(-translationX); }
                    for (ImageView cb : checkboxes) { cb.setVisibility(View.GONE); }
                    if (campaignEdited) {
                        new SaveChangesDialogFragment()
                                .show(getSupportFragmentManager(), DIALOG);
                        for (ImageView cb : checkboxes) { cb.setImageResource(R.drawable.circle_checkbox); }
                        campaignEdited = false;
                    }
                    clicked = !clicked;
                } else {
                    editBtn.setImageResource(R.drawable.ic_check_white_48dp);

                    for (View v : views) { v.animate().translationXBy(translationX); }
                    for (ImageView cb : checkboxes) { cb.setVisibility(View.VISIBLE); }

                    clicked = !clicked;
                }
            }
        });

        cbInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbInfo.setImageResource(R.drawable.circle_checkbox_checked);
                new CreateInfoDialog()
                        .show(getSupportFragmentManager(), DIALOG);
                campaignEdited = true;
            }
        });

        cbAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbAdmin.setImageResource(R.drawable.circle_checkbox_checked);
                campaignEdited = true;
            }
        });

        cbDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbDesc.setImageResource(R.drawable.circle_checkbox_checked);
                campaignEdited = true;
            }
        });

        cbAmbition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbAmbition.setImageResource(R.drawable.circle_checkbox_checked);
                campaignEdited = true;
            }
        });

        cbBenefits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbBenefits.setImageResource(R.drawable.circle_checkbox_checked);
                campaignEdited = true;
            }
        });

        cbPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbPlan.setImageResource(R.drawable.circle_checkbox_checked);
                campaignEdited = true;
            }
        });

        cbBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbBudget.setImageResource(R.drawable.circle_checkbox_checked);
                campaignEdited = true;
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
