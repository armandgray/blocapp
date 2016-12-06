package org.blocorganization.blocapp.models;

public class Remark {

    private Object subtype;
    private Object user;

    public Remark(String s) {
        this.subtype = "";
        this.user = "";
    }

    public Object getSubtype() {
        return subtype;
    }

    public Object getUser() {
        return user;
    }
}
