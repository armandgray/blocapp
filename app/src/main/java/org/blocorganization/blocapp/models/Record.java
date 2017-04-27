package org.blocorganization.blocapp.models;

import android.os.Bundle;

import org.joda.time.DateTime;

import java.util.ArrayList;

public abstract class Record {

    private static final String RECORD_TYPE_KEY = "RECORD_TYPE_KEY";
    private static final String ADMIN_KEY = "ADMIN_KEY";
    private static final String DESCRIPTION_KEY = "DESCRIPTION_KEY";
    private static final String TITLE_KEY = "TITLE_KEY";
    private static final String EXTRAS_KEY = "EXTRAS_KEY";
    private static final String ICON_KEY = "ICON_KEY";
    private static final String TIMESTAMP_KEY = "TIMESTAMP_KEY";
    private static final String IS_PUBLIC_KEY = "IS_PUBLIC_KEY";

    private String recordType;
    private String admin;
    private String title;
    private String description;
    private boolean isPublic;
    private int icon;

    private String extras;
    private ArrayList<Integer> timestamp;

    Record() {
        this.recordType = "";
        this.extras = "";
        this.admin = "";
        this.title = "";
        this.description = "";
        this.timestamp = new ArrayList<>();
        setTimestamp();
    }

    Record(Bundle b) {
        if (b != null) {
            this.recordType = b.getString(RECORD_TYPE_KEY);
            this.extras = b.getString(EXTRAS_KEY);
            this.admin = b.getString(ADMIN_KEY);
            this.title = b.getString(TITLE_KEY);
            this.description = b.getString(DESCRIPTION_KEY);
            this.icon = b.getInt(ICON_KEY);
            this.timestamp = b.getIntegerArrayList(TIMESTAMP_KEY);
            this.isPublic = b.getBoolean(IS_PUBLIC_KEY);
        }
    }

    Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(RECORD_TYPE_KEY, this.recordType);
        b.putString(DESCRIPTION_KEY, this.description);
        b.putString(ADMIN_KEY, this.admin);
        b.putString(TITLE_KEY, this.title);
        b.putString(EXTRAS_KEY, this.extras);
        b.putInt(ICON_KEY, this.icon);
        b.putIntegerArrayList(TIMESTAMP_KEY, this.timestamp);
        b.putBoolean(IS_PUBLIC_KEY, this.isPublic);
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

    void setTimestamp() {
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

    public void setTimestamp(ArrayList<Integer> timestamp) { this.timestamp = timestamp; }

    public void setExtras(String extras) { this.extras = extras; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public void setPublic(boolean bool) {
        this.isPublic = bool;
    }

}