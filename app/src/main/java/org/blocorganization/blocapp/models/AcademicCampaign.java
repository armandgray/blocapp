package org.blocorganization.blocapp.models;

import android.os.Bundle;

public class AcademicCampaign extends Campaign {
    public AcademicCampaign() {
        setRecordType(RecordType.ACADEMICS.toString());
//        setCampaignTheme(R.drawable.ic_book_open_variant_black_48dp);
    }

    public AcademicCampaign(Bundle b) {
        super(b);
    }
}
