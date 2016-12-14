package org.blocorganization.blocapp.models;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class ToDoItem extends Record {

    private static final String DEADLINE = "DEADLINE";
    private static final String STATUS = "STATUS";
    private static final String SOURCE_RECORD = "SOURCE_RECORD";
    private static final String NEW = "New";

    private ArrayList<Integer> deadline;
    private String status;
    @Nullable private Resource resource;
    private String sourceRecord;

    public ToDoItem() {
        this.deadline = new ArrayList<>();
        this.status = NEW;
        this.sourceRecord = "";
        setTimestamp();
    }

    public ToDoItem(Bundle b) {
        super(b);
        if (b != null) {
            this.deadline = b.getIntegerArrayList(DEADLINE);
            this.status = b.getString(STATUS);
            this.sourceRecord = b.getString(SOURCE_RECORD);
        }
    }

    public Bundle toBundle() {
        Bundle b = super.toBundle();
        b.putIntegerArrayList(DEADLINE, this.deadline);
        b.putString(STATUS, this.status);
        b.putString(SOURCE_RECORD, this.sourceRecord);
        return b;
    }

    public ArrayList<Integer> getDeadline() {
        return deadline;
    }

    public void setDeadline(ArrayList<Integer> deadline) {
        this.deadline = deadline;
    }

    @Nullable
    public Resource getResource() {
        return resource;
    }

    public void setResource(@Nullable Resource resource) {
        this.resource = resource;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSourceRecord() {
        return sourceRecord;
    }

    public void setSourceRecord(String sourceRecord) {
        this.sourceRecord = sourceRecord;
    }
}
