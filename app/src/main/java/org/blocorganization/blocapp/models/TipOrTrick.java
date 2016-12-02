package org.blocorganization.blocapp.models;

import android.os.Bundle;

public class TipOrTrick extends Record {

    public boolean isPublic;

    private TipOrTrick(Builder builder) {
        super.title = builder.title;
        super.description = builder.description;
        super.admin = builder.admin;
        super.recordType = RecordType.TIPSANDTRICKS.toString();
        super.setTimestamp();

        isPublic = builder.isPublic;
        super.icon = builder.icon;
    }

    public static class Builder {
        private String title = "";
        private String description = "";
        private String admin = "";
        private int icon = 0;
        private String subType;
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

        public TipOrTrick build() {
            return new TipOrTrick(this);
        }


        public Builder isPublic(boolean b) {
            isPublic = b;
            return this;
        }
    }

    public TipOrTrick(Bundle b) {
        super(b);
    }

    @Override
    Bundle toBundle() {
        return super.toBundle();
    }
}
