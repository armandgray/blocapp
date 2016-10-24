package org.blocorganization.blocapp.models;

public enum RecordType {
    // Don't forget to add to switch in adapter
    DEFAULT("default"), ACADEMICS("Academics"), COMMUNITY_ENGAGEMENT("Community Engagement"), SOCIAL_DINNER("Social Dinner"), SOCIAL_PARTY("Social Party"), MEETING("Meeting"), RESOURCE("Resource");

    private String type;

    RecordType(String type) {
        this.type = type;
    }

    @Override
    public String toString() { return type; }
}
