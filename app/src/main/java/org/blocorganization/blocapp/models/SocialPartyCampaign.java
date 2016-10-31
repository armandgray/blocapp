package org.blocorganization.blocapp.models;

import android.os.Bundle;

public class SocialPartyCampaign extends Campaign {
    public SocialPartyCampaign() {
        setRecordType(RecordType.SOCIAL_PARTY.toString());
//        setCampaignTheme(R.drawable.ic_martini_black_48dp);
    }

    public SocialPartyCampaign(Bundle b) {
        super(b);
    }
}
