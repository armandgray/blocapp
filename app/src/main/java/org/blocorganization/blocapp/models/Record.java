package org.blocorganization.blocapp.models;

import android.os.Bundle;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

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
    String title;
    String description;
    int icon;

    private List<Remark> likes = new ArrayList<>();
    private List<Remark> comments = new ArrayList<>();
    private String extras;
    private String timestamp;

    Record() {
        this.recordType = "";
        this.extras = "";
        this.admin = "";
        this.title = "";
        this.description = "";
        this.icon = 0;
        this.timestamp = "";

    }

    Record(Bundle b) {
        if (b != null) {
            this.recordType = b.getString(RECORD_TYPE_KEY);
            this.extras = b.getString(EXTRAS_KEY);
            this.admin = b.getString(ADMIN_KEY);
            this.title = b.getString(TITLE_KEY);
            this.description = b.getString(DESCRIPTION_KEY);
            this.icon = b.getInt(ICON_KEY);
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
        b.putInt(ICON_KEY, this.icon);
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

    public int getIconId() {
        return icon;
    }

    public void setIconId(int iconId) {
        this.icon = iconId;
    }

    public List<Remark> getLikes() {
        return likes;
    }

    public void setLikes(List<Remark> likes) {
        this.likes = likes;
    }

    public void addLike(Remark like) {
        this.likes.add(like);
    }

    public List<Remark> getComments() {
        return comments;
    }

    public void setComments(List<Remark> comments) {
        this.comments = comments;
    }

    public void addComment(Remark comment) {
        this.comments.add(comment);
    }

}