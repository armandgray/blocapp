package org.blocorganization.blocapp.models;

import android.os.Bundle;

public abstract class Record {

    //	constants for field references
    public static final String RECORD_TYPE_KEY = "RECORD_TYPE_KEY";
    public static final String ADMIN_KEY = "ADMIN_KEY";
    public static final String DESCRIPTION_KEY = "DESCRIPTION_KEY";
    public static final String TITLE_KEY = "TITLE_KEY";
    public static final String EXTRAS_KEY = "EXTRAS_KEY";
    public static final String ICON_KEY = "ICON_KEY";

    //	private fields
    private String recordType;
    private String admin;
    private String timestamp;
    private String title;
    private String description;
    private String extras;
    private int icon;

    //	Constructor used when creating the data object
    public Record(RecordType type, String admin, String extras, String description, String title, int icon) {
        this.recordType = type.toString();
        this.extras = extras;
        this.admin = admin;
        this.title = title;
        this.description = description;
        this.icon = icon;

        Long tsLong = System.currentTimeMillis()/1000;
        this.timestamp = tsLong.toString();
    }

    //	Create from a bundle
    public Record(Bundle b) {
        if (b != null) {
            this.recordType = b.getString(RECORD_TYPE_KEY);
            this.extras = b.getString(EXTRAS_KEY);
            this.admin = b.getString(ADMIN_KEY);
            this.title = b.getString(TITLE_KEY);
            this.description = b.getString(DESCRIPTION_KEY);
            this.icon = b.getInt(ICON_KEY);
        }
    }

    //	Package data for transfer between activities
    public Bundle toBundle(Bundle b) {
        b.putString(RECORD_TYPE_KEY, this.recordType);
        b.putString(DESCRIPTION_KEY, this.description);
        b.putString(ADMIN_KEY, this.admin);
        b.putString(TITLE_KEY, this.title);
        b.putString(EXTRAS_KEY, this.extras);
        b.putInt(ICON_KEY, this.icon);
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

    public void setRecordType(RecordType recordType) {
        this.recordType = recordType.toString();
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getTimestamp() {
        return timestamp;
    }

    public String getExtras() { return extras; }

    public void setExtras(String extras) { this.extras = extras; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}