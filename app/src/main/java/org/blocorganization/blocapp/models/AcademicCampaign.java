package org.blocorganization.blocapp.models;

import android.os.Bundle;

import org.blocorganization.blocapp.R;

public class AcademicCampaign extends Campaign {
    public AcademicCampaign(String admin, String extras, String description, String title, String benefits, String ambition, int campaignPhoto, String planOfExecution, String itemizedBudget, String venue, String date, String time) {
        super(RecordType.ACADEMICS, R.drawable.ic_book_open_variant_black_48dp ,admin, extras, description, title, benefits, ambition, campaignPhoto, planOfExecution, itemizedBudget, venue, date, time);
    }

    public AcademicCampaign(Bundle b) {
        super(b);
    }
}
