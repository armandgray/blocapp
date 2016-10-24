package org.blocorganization.blocapp.models;

import android.os.Bundle;

public class Campaign extends Record {

    //	constants for field references
    public static final String BENEFITS_KEY = "BENEFITS_KEY";
    public static final String AMBITION_KEY = "AMBITION_KEY";
    public static final String CAMPAIGN_PHOTO_KEY = "CAMPAIGN_PHOTO_KEY";
    public static final String PLAN_OF_EXECUTION_KEY = "PLAN_OF_EXECUTION_KEY";
    public static final String ITEMIZED_BUDGET_KEY = "ITEMIZED_BUDGET_KEY";
    public static final String VENUE_KEY = "VENUE_KEY";
    public static final String DATE_KEY = "DATE_KEY";
    public static final String TIME_KEY = "TIME_KEY";

    //	private fields
    private String benefits;
    private String ambition;
    private int campaignPhoto;
    private String planOfExecution;
    private String itemizedBudget;
    private String venue;
    private String date;
    private String time;

    public Campaign(RecordType type, int icon, String admin, String extras, String description, String title,
                    String benefits,
                    String ambition,
                    int campaignPhoto,
                    String planOfExecution,
                    String itemizedBudget,
                    String venue,
                    String date,
                    String time) {
        super(type, admin, extras, description, title, icon);
        this.benefits = benefits;
        this.ambition = ambition;
        this.campaignPhoto = campaignPhoto;
        this.planOfExecution = planOfExecution;
        this.itemizedBudget = itemizedBudget;
        this.venue = venue;
        this.date = date;
        this.time = time;
    }

    //	Create from a bundle
    public Campaign(Bundle b) {
        super(b);
        if (b != null) {
            this.benefits = b.getString(BENEFITS_KEY);
            this.ambition = b.getString(AMBITION_KEY);
            this.campaignPhoto = b.getInt(CAMPAIGN_PHOTO_KEY);
            this.planOfExecution = b.getString(PLAN_OF_EXECUTION_KEY);
            this.itemizedBudget = b.getString(ITEMIZED_BUDGET_KEY);
            this.venue = b.getString(VENUE_KEY);
            this.date = b.getString(DATE_KEY);
            this.time = b.getString(TIME_KEY);

        }
    }

    //	Package data for transfer between activities

    /**
     *  Should this be at override???????
     * @return
     */
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b = super.toBundle(b); // careful about copy vs. pointer; maybe b = is not necessary
        b.putString(BENEFITS_KEY, this.benefits);
        b.putString(AMBITION_KEY, this.ambition);
        b.putInt(CAMPAIGN_PHOTO_KEY, this.campaignPhoto);
        b.putString(PLAN_OF_EXECUTION_KEY, this.planOfExecution);
        b.putString(ITEMIZED_BUDGET_KEY, this.itemizedBudget);
        b.putString(VENUE_KEY, this.venue);
        b.putString(DATE_KEY, this.date);
        b.putString(TIME_KEY, this.time);

        return b;
    }

    //	getters and setters
    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getAmbition() {
        return ambition;
    }

    public void setAmbition(String ambition) {
        this.ambition = ambition;
    }

    public int getCampaignPhoto() {
        return campaignPhoto;
    }

    public void setCampaignPhoto(int campaignPhoto) {
        this.campaignPhoto = campaignPhoto;
    }

    public String getPlanOfExecution() {
        return planOfExecution;
    }

    public void setPlanOfExecution(String planOfExecution) {
        this.planOfExecution = planOfExecution;
    }

    public String getItemizedBudget() {
        return itemizedBudget;
    }

    public void setItemizedBudget(String itemizedBudget) {
        this.itemizedBudget = itemizedBudget;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
