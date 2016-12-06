package org.blocorganization.blocapp.models;

import android.os.Bundle;

public class Comment extends Remark {

    private static final String TEXT = "TEXT";
    private String subtype = "";
    private String user = "";
    private String text = "";

    public Comment(String user, String text) {
        super(user);
        this.subtype = RecordType.COMMENT.toString();
        this.text = text;
    }

    public String getSubtype() {
        return subtype;
    }
    public String getText() {
        return text;
    }

    Comment(Bundle b) {
        super(b);
        if (b != null) {
            this.text = b.getString(TEXT);
        }
    }

    Bundle toBundle() {
        Bundle b = super.toBundle();
        b.putString(TEXT, this.text);
        return b;
    }
}
