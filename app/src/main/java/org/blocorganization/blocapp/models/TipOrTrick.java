package org.blocorganization.blocapp.models;

import android.os.Bundle;

public class TipOrTrick extends Record {

    private TipOrTrick(Builder builder) {
        super.title = builder.title;
        super.description = builder.description;
        super.admin = builder.admin;
        super.campaignTheme = builder.theme;
        super.recordType = RecordType.TIPSANDTRICKS.toString();
        super.setTimestamp();
    }

    public static class Builder {
        private String title = "";
        private String description = "";
        private String admin = "";
        private String theme = "";

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

        public Builder theme(String s) {
            theme = s;
            return this;
        }

        public TipOrTrick build() {
            return new TipOrTrick(this);
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
