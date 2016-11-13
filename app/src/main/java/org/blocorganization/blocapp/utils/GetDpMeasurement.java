package org.blocorganization.blocapp.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

public class GetDpMeasurement {

    private GetDpMeasurement() {
    }

    public static int getDPI(final Activity activity, final int size) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return (size * metrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT;
    }
}
