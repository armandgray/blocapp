package org.blocorganization.blocapp.models;

import android.os.Bundle;

public class Record {

    //	constants for field references
    public static final RecordType RECORD_TYPE = RecordType.DEFAULT;
    public static final String ADMIN = "admin";
    public static final String IMAGE_RESOURCE = "imageResource";
    public static final String DESCRIPTION = "description";
    public static final String TIMESTAMP = "timestamp";

    //	private fields
    private String recordType;
    private int imageResource;
    private String admin;
    private String timestamp;
    private String description;

    //	Constructor used when creating the data object
    public Record(RecordType type, String admin, int imageResource, String description, String timestamp) {
        this.recordType = type.toString();
        this.imageResource = imageResource;
        this.timestamp = timestamp;
        this.admin = admin;
        this.description = description;
    }

    //	Create from a bundle
    public Record(Bundle b) {
        if (b != null) {
            this.recordType = b.getString(RECORD_TYPE.toString());
            this.imageResource = b.getInt(IMAGE_RESOURCE);
            this.timestamp = b.getString(TIMESTAMP);
            this.admin = b.getString(ADMIN);
            this.description = b.getString(DESCRIPTION);
        }
    }

    //	Package data for transfer between activities
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(RECORD_TYPE.toString(), this.recordType);
        b.putInt(IMAGE_RESOURCE, this.imageResource);
        b.putString(TIMESTAMP, this.timestamp);
        b.putString(ADMIN, this.admin);
        b.putString(DESCRIPTION, this.description);
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

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
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

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}

