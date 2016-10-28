package org.blocorganization.blocapp.models;

import android.os.Bundle;

import org.blocorganization.blocapp.R;

public class CommunityEngagementCampaign extends Campaign {

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

    // From Record
    public String recordType;
    public String admin;
    public String timestamp;
    public String title;
    public String description;
    public String extras;
    public int icon;

    public CommunityEngagementCampaign() {
        setRecordType(RecordType.COMMUNITY_ENGAGEMENT.toString());
        setIcon(R.drawable.ic_earth_grey600_48dp);
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

        // From Record
        this.recordType = null;
        this.extras = null;
        this.admin = null;
        this.title = null;
        this.description = null;
        this.icon = 0;

        Long tsLong = System.currentTimeMillis()/1000;
        this.timestamp = tsLong.toString();
    }

    public CommunityEngagementCampaign(Bundle b) {
        super(b);
    }

    @Override
    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public String getBenefits() {
        return benefits;
    }

    @Override
    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    @Override
    public String getAmbition() {
        return ambition;
    }

    @Override
    public void setAmbition(String ambition) {
        this.ambition = ambition;
    }

    @Override
    public int getCampaignPhoto() {
        return campaignPhoto;
    }

    @Override
    public void setCampaignPhoto(int campaignPhoto) {
        this.campaignPhoto = campaignPhoto;
    }

    @Override
    public String getPlanOfExecution() {
        return planOfExecution;
    }

    @Override
    public void setPlanOfExecution(String planOfExecution) {
        this.planOfExecution = planOfExecution;
    }

    @Override
    public String getItemizedBudget() {
        return itemizedBudget;
    }

    @Override
    public void setItemizedBudget(String itemizedBudget) {
        this.itemizedBudget = itemizedBudget;
    }

    @Override
    public String getVenue() {
        return venue;
    }

    @Override
    public void setVenue(String venue) {
        this.venue = venue;
    }

    @Override
    public String getMonth() {
        return month;
    }

    @Override
    public void setMonth(String month) {
        this.month = month;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String getRecordType() {
        return recordType;
    }

    @Override
    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    @Override
    public String getAdmin() {
        return admin;
    }

    @Override
    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getExtras() {
        return extras;
    }

    @Override
    public void setExtras(String extras) {
        this.extras = extras;
    }

    @Override
    public int getIcon() {
        return icon;
    }

    @Override
    public void setIcon(int icon) {
        this.icon = icon;
    }
}
