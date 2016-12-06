package org.blocorganization.blocapp.models;

public enum RecordType {
    DEFAULT("Other"), ACADEMIC("Academic"), COMMUNITY_ENGAGEMENT("Community Engagement"), SOCIAL_DINNER("Social Dinner"), SOCIAL_PARTY("Social Party"), MEETING("Meeting"), RESOURCE("Resource"), TIPSANDTRICKS("Tips and Tricks"), LIFESTYLE("Lifestyle"), LIKE("Like"), COMMENT("Comment");

    private String type;

    RecordType(String type) {
        this.type = type;
    }

    @Override
    public String toString() { return type; }
}
