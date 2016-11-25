package org.blocorganization.blocapp.campaigns;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

class FieldUtilities {

    public static final String DESCRIPTION = "Description\n\n\t\t";
    public static final String AMBITION = "Ambition\n\n\t\t";
    public static final String BENEFITS_TO_THE_COLLEGE = "Benefits to the College\n\n\t\t";

    static void setTextForEditTextWith(String text, EditText etReference) {
        if (text != null && !text.equals("")) {
            etReference.setText(text);
        }
    }

    static void setTextForEditTextAndAppend(String appendedText, String text, EditText etReference) {
        if (text != null && !text.equals("")) {
            etReference.setText(appendedText + text);
        }
    }

    static void loadUrlIntoImageViewWithActivity(String url, ImageView ivReference, Activity activity) {
        if (url != null && !url.equals("")) {
            Picasso.with(activity).load(url).into(ivReference);
            ivReference.setVisibility(View.VISIBLE);
        }
    }

    static void setResourceForImageViewWithId(int resourceId, ImageView ivReference) {
        ivReference.setImageResource(resourceId);
        ivReference.setVisibility(View.VISIBLE);
    }

}
