package org.blocorganization.blocapp.models;

import android.os.Bundle;

import org.blocorganization.blocapp.R;

public class SocialPartyCampaign extends Campaign {
    public SocialPartyCampaign() {
        setRecordType(RecordType.SOCIAL_PARTY.toString());
        setIcon(R.drawable.ic_martini_black_48dp);
    }

    public SocialPartyCampaign(Bundle b) {
        super(b);
    }
}
