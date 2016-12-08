package org.blocorganization.blocapp.utils;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FieldUtilities {

    public static final String DESCRIPTION = "Description\n\n\t\t";
    public static final String AMBITION = "Ambition\n\n\t\t";
    public static final String BENEFITS_TO_THE_COLLEGE = "Benefits to the College\n\n\t\t";

    public static void setSelectionForSpinnerFromList(List<String> spListItems, String text, Spinner spReference) {
        if (text != null && !text.equals("")) {
            findSpinnerItemIn(spListItems, text, spReference);
        }
    }

    private static void findSpinnerItemIn(List<String> spListItems, String text, Spinner spReference) {
        int itemIndex = 0;
        for (String item : spListItems) {
            if (text.equals(item)) {
                spReference.setSelection(itemIndex);
            }
            itemIndex++;
        }
    }

    public static boolean verify(EditText etReference) {
        String text = etReference.getText().toString();
        return !text.equals("");
    }

    public static String getTextFrom(EditText etReference) {
        return etReference.getText().toString();
    }

    public static void setTextForTextViewAndPrepend(String prependedText, String text, TextView tvReference) {
        if (text != null && !text.equals("")) {
            tvReference.setText(prependedText + text);
        }
    }

    public static void setTextForTextViewWith(String text, TextView tvReference) {
        if (text != null && !text.equals("")) {
            tvReference.setText(text);
        }
    }

    public static void setTextForEditTextWith(String text, EditText etReference) {
        if (text != null && !text.equals("")) {
            etReference.setText(text);
        }
    }

    public static void setTextForEditTextAndPrepend(String prependedText, String text, EditText etReference) {
        if (text != null && !text.equals("")) {
            etReference.setText(prependedText + text);
        }
    }

    public static void loadUrlIntoImageViewWithActivity(String url, ImageView ivReference, Activity activity) {
        if (url != null && !url.equals("")) {
            Picasso.with(activity).load(url).into(ivReference);
            ivReference.setVisibility(View.VISIBLE);
        }
    }

    public static void setResourceForImageViewWithId(int resourceId, ImageView ivReference) {
        ivReference.setImageResource(resourceId);
        ivReference.setVisibility(View.VISIBLE);
    }

}
