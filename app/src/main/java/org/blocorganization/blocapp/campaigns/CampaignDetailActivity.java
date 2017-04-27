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
        TextView tvAdmin = (TextView) findViewById(R.id.tvAdmin);
        TextView tvDesc = (TextView) findViewById(R.id.tvDesc);
        TextView tvAmbition = (TextView) findViewById(R.id.tvAmbition);
        TextView tvBenefits = (TextView) findViewById(R.id.tvBenefits);
        TextView tvPlan = (TextView) findViewById(R.id.tvPlan);
        TextView tvBudget = (TextView) findViewById(R.id.tvBudget);

        final List<View> views = new ArrayList<>();
        views.add(itemInfoThemeContainer);
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
            tvAdmin.setText("Admin: " + campaign.getAdmin());
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
    }

}
