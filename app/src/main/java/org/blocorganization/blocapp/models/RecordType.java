package org.blocorganization.blocapp.models;

public enum RecordType {
    // Don't forget to add to switch in adapter
    DEFAULT("default"), SYSTEM("System"), CONNECTION("Connection"), CAMPAIGN("Campaign"), RESOURCE("Resource");

    private String type;

    RecordType(String type) {
        this.type = type;
    }

    @Override
    public String toString() { return type; }
}
