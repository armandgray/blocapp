package org.blocorganization.blocapp.models;

import android.os.Bundle;

public class Campaign extends Record {

    //	constants for field references
    public static final String ABBREVIATION_KEY = "ABBREVIATION_KEY";
    public static final String BENEFITS_KEY = "BENEFITS_KEY";
    public static final String AMBITION_KEY = "AMBITION_KEY";
    public static final String CAMPAIGN_PHOTO_KEY = "CAMPAIGN_PHOTO_KEY";
    public static final String PLAN_OF_EXECUTION_KEY = "PLAN_OF_EXECUTION_KEY";
    public static final String ITEMIZED_BUDGET_KEY = "ITEMIZED_BUDGET_KEY";
    public static final String VENUE_KEY = "VENUE_KEY";
    public static final String MONTH_KEY = "MONTH_KEY";
    public static final String DATE_KEY = "DATE_KEY";
    public static final String TIME_KEY = "TIME_KEY";

    //	private fields
    public String abbreviation;
    public String benefits;
    public String ambition;
    public int campaignPhoto;
    public String planOfExecution;
    public String itemizedBudget;
    public String venue;
    public String month;
    public String date;
    public String time;

    public Campaign() {
        this.abbreviation = null;
        this.benefits = null;
        this.ambition = null;
        this.campaignPhoto = 0;
        this.planOfExecution = null;
        this.itemizedBudget = null;
        this.venue = null;
        this.month = null;
        this.date = null;
        this.time = null;
    }

    //	Create from a bundle
    public Campaign(Bundle b) {
        super(b);
        if (b != null) {
            this.abbreviation = b.getString(ABBREVIATION_KEY);
            this.benefits = b.getString(BENEFITS_KEY);
            this.ambition = b.getString(AMBITION_KEY);
            this.campaignPhoto = b.getInt(CAMPAIGN_PHOTO_KEY);
            this.planOfExecution = b.getString(PLAN_OF_EXECUTION_KEY);
            this.itemizedBudget = b.getString(ITEMIZED_BUDGET_KEY);
            this.venue = b.getString(VENUE_KEY);
            this.month = b.getString(MONTH_KEY);
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
        b.putString(ABBREVIATION_KEY, this.abbreviation);
        b.putString(BENEFITS_KEY, this.benefits);
        b.putString(AMBITION_KEY, this.ambition);
        b.putInt(CAMPAIGN_PHOTO_KEY, this.campaignPhoto);
        b.putString(PLAN_OF_EXECUTION_KEY, this.planOfExecution);
        b.putString(ITEMIZED_BUDGET_KEY, this.itemizedBudget);
        b.putString(VENUE_KEY, this.venue);
        b.putString(DATE_KEY, this.month);
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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
