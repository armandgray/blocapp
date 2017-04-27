package org.blocorganization.blocapp.models;

import android.os.Bundle;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class Campaign extends Record {

    //	constants for field references
    private static final String ABBREVIATION_KEY = "ABBREVIATION_KEY";
    private static final String BENEFITS_KEY = "BENEFITS_KEY";
    private static final String AMBITION_KEY = "AMBITION_KEY";
    private static final String CAMPAIGN_PHOTO_KEY = "CAMPAIGN_PHOTO_KEY";
    private static final String CAMPAIGN_THEME_KEY = "CAMPAIGN_THEME_KEY";
    private static final String PLAN_OF_EXECUTION_KEY = "PLAN_OF_EXECUTION_KEY";
    private static final String ITEMIZED_BUDGET_KEY = "ITEMIZED_BUDGET_KEY";
    private static final String VENUE_KEY = "VENUE_KEY";
    private static final String FROM_DATE_KEY = "FROM_DATE_KEY";
    private static final String TO_DATE_KEY = "TO_DATE_KEY";
    private static final String TIMESTAMP_KEY = "TIMESTAMP_KEY";

    //	private fields
    private String abbreviation;
    private String benefits;
    private String ambition;
    private String campaignPhoto;
    private String planOfExecution;
    private String itemizedBudget;
    private String venue;
    private ArrayList<Integer> fromDate;
    private ArrayList<Integer> toDate;
    private ArrayList<Integer> timestamp;
    private String campaignTheme;

    public Campaign() {
        this.abbreviation = "";
        this.benefits = "";
        this.ambition = "";
        this.campaignPhoto = "";
        this.campaignTheme = "";
        this.planOfExecution = "";
        this.itemizedBudget = "";
        this.venue = "";
        this.fromDate = new ArrayList<>();
        this.toDate = new ArrayList<>();
        this.timestamp = new ArrayList<>();
    }

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
            this.timestamp = b.getIntegerArrayList(TIMESTAMP_KEY);
        }
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
        b.putIntegerArrayList(TIMESTAMP_KEY, this.timestamp);

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

    public String getPhotoUrl() {
        return campaignPhoto;
    }

    public void setPhotoUrl(String campaignPhoto) {
        this.campaignPhoto = campaignPhoto;
    }

    public void setPlanOfExecution(String planOfExecution) {
        this.planOfExecution = planOfExecution;
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

    public ArrayList<Integer> getToDate() {
        return toDate;
    }

    public void setToDate(ArrayList<Integer> toDate) {
        this.toDate = toDate;
    }

    public ArrayList<Integer> getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        DateTime timestamp = new DateTime();
        ArrayList<Integer> dateList = new ArrayList<>();
        dateList.add(timestamp.getYear());
        dateList.add(timestamp.getMonthOfYear());
        dateList.add(timestamp.getDayOfMonth());
        dateList.add(timestamp.getHourOfDay());
        dateList.add(timestamp.getMinuteOfHour());
        dateList.add(timestamp.getSecondOfMinute());
        dateList.add(timestamp.getMillisOfSecond());

        this.timestamp = dateList;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getThemeImageUrl() {
        return campaignTheme;
    }

    public void setThemeImageUrl(String campaignTheme) {
        this.campaignTheme = campaignTheme;
    }

}
