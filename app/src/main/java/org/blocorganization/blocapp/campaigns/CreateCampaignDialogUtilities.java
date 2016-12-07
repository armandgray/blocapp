package org.blocorganization.blocapp.campaigns;

import android.app.Activity;
import android.content.Intent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.blocorganization.blocapp.BlocApp;
import org.blocorganization.blocapp.models.Campaign;

class CreateCampaignDialogUtilities {

    private static final String CAMPAIGNS = "campaigns";

    static void saveCampaignToDatabaseWith(Activity activity, Campaign campaign) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(CAMPAIGNS);
        mDatabase.child(String.valueOf(
                BlocApp.getInstance().getCampaignPosition(campaign)))
                .setValue(campaign);
    }

    static void startDetailActivityWith(Activity activity, Campaign campaign, boolean isNewCampaign) {
        if (!isNewCampaign) {
            activity.onBackPressed();
        }
        activity.onBackPressed();
        Intent intent = new Intent(activity, CampaignDetailActivity.class);
        intent.putExtras(campaign.toBundle());
        activity.startActivity(intent);
    }

}
