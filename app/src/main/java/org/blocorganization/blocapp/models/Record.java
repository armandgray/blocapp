package org.blocorganization.blocapp.models;

import android.os.Bundle;

import org.joda.time.DateTime;

abstract class Record {

    private static final String RECORD_TYPE_KEY = "RECORD_TYPE_KEY";
    private static final String ADMIN_KEY = "ADMIN_KEY";
    private static final String DESCRIPTION_KEY = "DESCRIPTION_KEY";
    private static final String TITLE_KEY = "TITLE_KEY";
    private static final String EXTRAS_KEY = "EXTRAS_KEY";
    private static final String ICON_KEY = "ICON_KEY";
    private static final String TIMESTAMP_KEY = "TIMESTAMP_KEY";

    String recordType;
    String admin;
    private String timestamp;
    String title;
    String description;
    String extras;
    String campaignTheme;

    Record() {
        this.recordType = "";
        this.extras = "";
        this.admin = "";
        this.title = "";
        this.description = "";
        this.campaignTheme = "";
        this.timestamp = "";
    }

    Record(Bundle b) {
        if (b != null) {
            this.recordType = b.getString(RECORD_TYPE_KEY);
            this.extras = b.getString(EXTRAS_KEY);
            this.admin = b.getString(ADMIN_KEY);
            this.title = b.getString(TITLE_KEY);
            this.description = b.getString(DESCRIPTION_KEY);
            this.campaignTheme = b.getString(ICON_KEY);
            this.timestamp = b.getString(TIMESTAMP_KEY);
        }
    }

    //	Package data for transfer between activities
    Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(RECORD_TYPE_KEY, this.recordType);
        b.putString(DESCRIPTION_KEY, this.description);
        b.putString(ADMIN_KEY, this.admin);
        b.putString(TITLE_KEY, this.title);
        b.putString(EXTRAS_KEY, this.extras);
        b.putString(ICON_KEY, this.campaignTheme);
        b.putString(TIMESTAMP_KEY, this.timestamp);
        return b;
    }

    //	Output flower data
    @Override
    public String toString() {
        return recordType;
    }

    //	getters and setters
    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getExtras() { return extras; }

    public void setExtras(String extras) { this.extras = extras; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThemeImageUrl() {
        return campaignTheme;
    }

    public void setThemeImageUrl(String campaignTheme) {
        this.campaignTheme = campaignTheme;
    }
}