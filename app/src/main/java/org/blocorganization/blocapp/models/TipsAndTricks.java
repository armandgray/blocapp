package org.blocorganization.blocapp.models;

import android.os.Bundle;

public class TipsAndTricks extends Record {

    public TipsAndTricks(String description) {

    }

    //	Create from a bundle
    public TipsAndTricks(Bundle b) {
        super(b);
    }

    public Bundle toBundle() {
        return super.toBundle();
    }
}
