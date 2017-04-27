package org.blocorganization.blocapp.utils;

import android.view.View;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Locale;

class DateTimeFormatHandler {

    private static final int SECONDS_ZERO = 0;
    private static final int YEAR = 0;
    private static final int MONTH = 1;
    private static final int DAY = 2;
    private static final int HOUR = 3;
    private static final int MINUTE = 4;
    private static final String ON = "On";
    private static final String END = "End";

    static void setTextForDateWith(ArrayList<Integer> dateElementsList, TextView tvReference, boolean isStartDate) {
        if (dateElementsList != null
                && dateElementsList.size() > 0
                && dateElementsList.get(0) != 0
                && dateElementsList.get(0) != 1
                & tvReference != null) {
            DateTime date = getDateTimeReferenceFrom(dateElementsList);
            String onEnd = isStartDate ? ON : END;
            String amPm = designateAmPmFrom(dateElementsList.get(HOUR));
            String minutes = String.format(Locale.US, "%02d", date.getMinuteOfHour());
            String dateText = onEnd + ": "
                    + date.monthOfYear().getAsText() + " "
                    + date.getDayOfMonth() + ", "
                    + date.getYear() + " - "
                    + date.getHourOfDay() + ":"
                    + minutes + " "
                    + amPm;
            tvReference.setText(dateText);
            tvReference.setVisibility(View.VISIBLE);
        }
    }

    private static DateTime getDateTimeReferenceFrom(ArrayList<Integer> dateElementsList) {
        Integer year = getValueIfExistsFrom(dateElementsList.get(YEAR));
        Integer month = getValueIfExistsFrom(dateElementsList.get(MONTH));
        Integer day = getValueIfExistsFrom(dateElementsList.get(DAY));
        Integer hourListElement = get12HourFormatFrom(dateElementsList.get(HOUR));
        Integer hour = getValueIfExistsFrom(hourListElement);
        Integer minute = getValueIfExistsFrom(dateElementsList.get(MINUTE));
        return new DateTime(year, month, day, hour, minute, SECONDS_ZERO, SECONDS_ZERO);
    }

    private static Integer getValueIfExistsFrom(Integer dateElement) {
        if (dateElement != null && dateElement != 0) {
            return dateElement;
        }
        return 1;
    }

    private static Integer get12HourFormatFrom(Integer hour) {
        if (hour != 12) {
            return hour % 12;
        }
        return 12;
    }

    private static String designateAmPmFrom(Integer hour) {
        if (hour >= 12) {
            return "pm";
        }
        return "am";
    }

}
