package org.blocorganization.blocapp.models;

import android.os.Bundle;

public class Remark {

    static final String SUBTYPE = "SUBTYPE";
    static final String USER = "USER";
    private String subtype = "";
    private String user = "";

    public Remark(String user) {
        this.subtype = RecordType.LIKE.toString();
        this.user = user;
    }

    public String getSubtype() {
        return subtype;
    }
    public String getUser() {
        return user;
    }

    public Remark(Bundle b) {
        if (b != null) {
            this.subtype = b.getString(SUBTYPE);
            this.user = b.getString(USER);
        }
    }

    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(SUBTYPE, this.subtype);
        b.putString(USER, this.user);
        return b;
    }

}
