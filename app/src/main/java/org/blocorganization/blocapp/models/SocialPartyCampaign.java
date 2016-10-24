package org.blocorganization.blocapp.models;

import android.os.Bundle;

import org.blocorganization.blocapp.R;

public class SocialPartyCampaign extends Campaign {
    public SocialPartyCampaign(String admin, String extras, String description, String title, String benefits, String ambition, int campaignPhoto, String planOfExecution, String itemizedBudget, String venue, String date, String time) {
        super(RecordType.SOCIAL_PARTY, R.drawable.ic_radio_black_48dp, admin, extras, description, title, benefits, ambition, campaignPhoto, planOfExecution, itemizedBudget, venue, date, time);
    }

    public SocialPartyCampaign(Bundle b) {
        super(b);
    }
}
