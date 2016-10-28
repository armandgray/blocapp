package org.blocorganization.blocapp.models;

import android.os.Bundle;

import org.blocorganization.blocapp.R;

public class AcademicCampaign extends Campaign {
    public AcademicCampaign() {
        setRecordType(RecordType.ACADEMICS.toString());
        setIcon(R.drawable.ic_book_open_variant_black_48dp);
    }

    public AcademicCampaign(Bundle b) {
        super(b);
    }
}
