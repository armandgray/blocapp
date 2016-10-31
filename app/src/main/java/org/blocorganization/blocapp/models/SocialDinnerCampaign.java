package org.blocorganization.blocapp.models;

import android.os.Bundle;

public class SocialDinnerCampaign extends Campaign {
    public SocialDinnerCampaign() {
        setRecordType(RecordType.SOCIAL_DINNER.toString());
//        setCampaignTheme(R.drawable.ic_food_variant_black_48dp);
    }

    public SocialDinnerCampaign(Bundle b) {
        super(b);
    }
}
