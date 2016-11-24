package org.blocorganization.blocapp.models;

import android.os.Bundle;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class Campaign extends Record {

    //	constants for field references
    public static final String ABBREVIATION_KEY = "ABBREVIATION_KEY";
    public static final String BENEFITS_KEY = "BENEFITS_KEY";
    public static final String AMBITION_KEY = "AMBITION_KEY";
    public static final String CAMPAIGN_PHOTO_KEY = "CAMPAIGN_PHOTO_KEY";
    public static final String CAMPAIGN_THEME_KEY = "CAMPAIGN_THEME_KEY";
    public static final String PLAN_OF_EXECUTION_KEY = "PLAN_OF_EXECUTION_KEY";
    public static final String ITEMIZED_BUDGET_KEY = "ITEMIZED_BUDGET_KEY";
    public static final String VENUE_KEY = "VENUE_KEY";
    public static final String FROM_DATE_KEY = "FROM_DATE_KEY";
    public static final String TO_DATE_KEY = "TO_DATE_KEY";
    public static final String TIMESTAMP_KEY = "TIMESTAMP_KEY";

    //	private fields
    public String abbreviation;
    public String benefits;
    public String ambition;
    public String campaignPhoto;
    public String planOfExecution;
    public String itemizedBudget;
    public String venue;
    public ArrayList<Integer> fromDate;
    public ArrayList<Integer> toDate;
    public String timestamp;
    public String campaignTheme;

    public Campaign() {
        this.abbreviation = null;
        this.benefits = null;
        this.ambition = null;
        this.campaignPhoto = null;
        this.campaignTheme = null;
        this.planOfExecution = null;
        this.itemizedBudget = null;
        this.venue = null;
        this.fromDate = null;
        this.toDate = null;
        this.timestamp = null;
    }

    //	Create from a bundle
    public Campaign(Bundle b) {
        super(b);
        if (b != null) {
            this.abbreviation = b.getString(ABBREVIATION_KEY);
            this.benefits = b.getString(BENEFITS_KEY);
            this.ambition = b.getString(AMBITION_KEY);
            this.campaignPhoto = b.getString(CAMPAIGN_PHOTO_KEY);
            this.campaignTheme = b.getString(CAMPAIGN_THEME_KEY);
            this.planOfExecution = b.getString(PLAN_OF_EXECUTION_KEY);
            this.itemizedBudget = b.getString(ITEMIZED_BUDGET_KEY);
            this.venue = b.getString(VENUE_KEY);
            this.fromDate = b.getIntegerArrayList(FROM_DATE_KEY);
            this.toDate = b.getIntegerArrayList(TO_DATE_KEY);
            this.timestamp = b.getString(TIMESTAMP_KEY);
        }
    }

    //	Package data for transfer between activities

    public ArrayList<Integer> getToDate() {
        return toDate;
    }

    public void setToDate(ArrayList<Integer> toDate) {
        this.toDate = toDate;
    }

    public Bundle toBundle() {
        Bundle b = super.toBundle(); // careful about copy vs. pointer; maybe b = is not necessary
        b.putString(ABBREVIATION_KEY, this.abbreviation);
        b.putString(BENEFITS_KEY, this.benefits);
        b.putString(AMBITION_KEY, this.ambition);
        b.putString(CAMPAIGN_PHOTO_KEY, this.campaignPhoto);
        b.putString(CAMPAIGN_THEME_KEY, this.campaignTheme);
        b.putString(PLAN_OF_EXECUTION_KEY, this.planOfExecution);
        b.putString(ITEMIZED_BUDGET_KEY, this.itemizedBudget);
        b.putString(VENUE_KEY, this.venue);
        b.putIntegerArrayList(FROM_DATE_KEY, this.fromDate);
        b.putIntegerArrayList(TO_DATE_KEY, this.toDate);
        b.putString(TIMESTAMP_KEY, this.timestamp);

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

    public String getCampaignPhoto() {
        return campaignPhoto;
    }

    public void setCampaignPhoto(String campaignPhoto) {
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

    public ArrayList<Integer> getFromDate() {
        return fromDate;
    }

    public void setFromDate(ArrayList<Integer> fromDate) {
        this.fromDate = fromDate;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        DateTime timestamp = new DateTime();
        this.timestamp = String.valueOf(timestamp.getYear())
                + timestamp.getMonthOfYear()
                + timestamp.getDayOfMonth()
                + timestamp.getHourOfDay()
                + timestamp.getMinuteOfHour()
                + timestamp.getSecondOfMinute()
                + timestamp.getMillisOfSecond();
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCampaignTheme() {
        return campaignTheme;
    }

    public void setCampaignTheme(String campaignTheme) {
        this.campaignTheme = campaignTheme;
    }

}