package org.blocorganization.blocapp.utils;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class DateTimeHandler {

    public static final int SECONDS_ZERO = 0;
    public static final int YEAR = 0;
    public static final int MONTH = 1;
    public static final int DAY = 2;
    public static final int HOUR = 3;
    public static final int MINUTE = 4;

    public static DateTime getDateTimeReferenceFrom(ArrayList<Integer> dateElementsList) {
        Integer year = getValueIfExistsFrom(dateElementsList.get(YEAR));
        Integer month = getValueIfExistsFrom(dateElementsList.get(MONTH));
        Integer day = getValueIfExistsFrom(dateElementsList.get(DAY));
        Integer hour = getValueIfExistsFrom(dateElementsList.get(HOUR));
        Integer minute = getValueIfExistsFrom(dateElementsList.get(MINUTE));
        return new DateTime(year, month, day, hour, minute, SECONDS_ZERO, SECONDS_ZERO);
    }

    private static Integer getValueIfExistsFrom(Integer dateElement) {
        if (dateElement != null && dateElement != 0) {
            return dateElement;
        }
        return 1;
    }

    public static String designateAmPmFrom(Integer hour) {
        if (hour >= 12) {
            return "pm";
        }
        return "am";
    }

}
