package org.blocorganization.blocapp.models;

public class CommunityEngagementCampaign extends Campaign {

    public CommunityEngagementCampaign() {
        this.abbreviation = null;
        this.benefits = null;
        this.ambition = null;
        this.campaignPhoto = null;
        this.planOfExecution = null;
        this.itemizedBudget = null;
        this.venue = null;
        this.month = null;
        this.fromDate = null;
        this.time = null;
        this.campaignTheme = null;

        // From Record
        this.recordType = null;
        this.extras = null;
        this.admin = null;
        this.title = null;
        this.description = null;

        Long tsLong = System.currentTimeMillis()/1000;
        this.timestamp = tsLong.toString();
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

    public String getCampaignPhoto() {
        return campaignPhoto;
    }

    public void setCampaignPhoto(String campaignPhoto) {
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
    public String getFromDate() {
        return fromDate;
    }

    @Override
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
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

    public String getCampaignTheme() {
        return campaignTheme;
    }

    public void setCampaignTheme(String campaignTheme) {
        this.campaignTheme = campaignTheme;
    }
}
