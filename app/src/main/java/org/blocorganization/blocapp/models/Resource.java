package org.blocorganization.blocapp.models;

import android.os.Bundle;

import org.blocorganization.blocapp.R;

public class Resource extends Record {

    private static final String ACADEMIC = "Academic";
    private static final String LIFESTYLE = "Lifestyle";

    private boolean isPublic;
    private String subType = "";

    private Resource(Builder builder) {
        super.title = builder.title;
        super.description = builder.description;
        super.admin = builder.admin;
        super.recordType = RecordType.TIPSANDTRICKS.toString();
        super.setTimestamp();
        subType = builder.subType;
        isPublic = builder.isPublic;
        super.icon = getIconIdFromSubType();

    }

    private int getIconIdFromSubType() {
        switch (subType) {
            case ACADEMIC:
                return R.drawable.ic_book_open_variant_white_48dp;
            case LIFESTYLE:
                return R.drawable.ic_earth_white_48dp;
            default:
                return 0;
        }
    }

    public static class Builder {
        private String title = "";
        private String description = "";
        private String admin = "";
        private String subType = "";
        private boolean isPublic;

        public Builder(String s) {
            title = s;
        }

        public Builder description(String s) {
            description = s;
            return this;
        }

        public Builder admin(String s) {
            admin = s;
            return this;
        }

        public Builder subType(String s) {
            subType = s;
            return this;
        }

        public Resource build() {
            return new Resource(this);
        }


        public Builder isPublic(boolean b) {
            isPublic = b;
            return this;
        }
    }

    public Resource(Bundle b) {
        super(b);
    }

    @Override
    Bundle toBundle() {
        return super.toBundle();
    }

    public String getSubType() {
        return subType;
    }

    public boolean isPublic() {
        return isPublic;
    }
}
