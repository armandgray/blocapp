package org.blocorganization.blocapp.models;

import android.os.Bundle;

import org.blocorganization.blocapp.R;

public class CommunityEngagementCampaign extends Campaign {

    public CommunityEngagementCampaign(String admin, String title, String description, String benefits, String ambition, int campaignPhoto, String planOfExecution, String itemizedBudget, String venue, String date, String time, String extras) {
        super(RecordType.COMMUNITY_ENGAGEMENT, R.drawable.ic_earth_black_48dp, admin, extras, description, title, benefits, ambition, campaignPhoto, planOfExecution, itemizedBudget, venue, date, time);
    }

    public CommunityEngagementCampaign(Bundle b) {
        super(b);
    }
}
