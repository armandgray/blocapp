package org.blocorganization.blocapp.models;

import android.os.Bundle;

import org.blocorganization.blocapp.R;

public class SocialDinnerCampaign extends Campaign {
    public SocialDinnerCampaign(String admin, String extras, String description, String title, String benefits, String ambition, int campaignPhoto, String planOfExecution, String itemizedBudget, String venue, String date, String time) {
        super(RecordType.SOCIAL_DINNER, R.drawable.ic_food_variant_black_48dp, admin, extras, description, title, benefits, ambition, campaignPhoto, planOfExecution, itemizedBudget, venue, date, time);
    }

    public SocialDinnerCampaign(Bundle b) {
        super(b);
    }
}
