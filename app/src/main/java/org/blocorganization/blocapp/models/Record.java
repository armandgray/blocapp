package org.blocorganization.blocapp.models;

import android.os.Bundle;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public abstract class Record {

    private static final String RECORD_TYPE_KEY = "RECORD_TYPE_KEY";
    private static final String ADMIN_KEY = "ADMIN_KEY";
    private static final String DESCRIPTION_KEY = "DESCRIPTION_KEY";
    private static final String TITLE_KEY = "TITLE_KEY";
    private static final String EXTRAS_KEY = "EXTRAS_KEY";
    private static final String ICON_KEY = "ICON_KEY";
    private static final String TIMESTAMP_KEY = "TIMESTAMP_KEY";
    private static final String IS_PUBLIC_KEY = "IS_PUBLIC_KEY";

    String recordType;
    String admin;
    String title;
    String description;
    boolean isPublic;
    int icon;

    private List<Remark> likes = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private String extras;
    private ArrayList<Integer> timestamp;

    Record() {
        this.recordType = "";
        this.extras = "";
        this.admin = "";
        this.title = "";
        this.description = "";
        this.timestamp = new ArrayList<>();

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

    public ArrayList<Integer> getTimestamp() {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public void setPublic(boolean bool) {
        this.isPublic = bool;
    }

}