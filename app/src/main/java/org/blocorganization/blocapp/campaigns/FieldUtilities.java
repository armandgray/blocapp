package org.blocorganization.blocapp.campaigns;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.blocorganization.blocapp.BlocApp;
import org.blocorganization.blocapp.models.Campaign;

import java.util.List;

class FieldUtilities {

    public static final String DESCRIPTION = "Description\n\n\t\t";
    public static final String AMBITION = "Ambition\n\n\t\t";
    public static final String BENEFITS_TO_THE_COLLEGE = "Benefits to the College\n\n\t\t";

    private static final String CAMPAIGNS = "campaigns";

    static void setSelectionForSpinnerFromList(List<String> spListItems, String text, Spinner spReference) {
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

    static void setTextForEditTextWith(String text, EditText etReference) {
        if (text != null && !text.equals("")) {
            etReference.setText(text);
        }
    }

    static void setTextForEditTextAndPrepend(String appendedText, String text, EditText etReference) {
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

    static void saveCampaignToDatabaseWith(Activity activity, Campaign campaign) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child(CAMPAIGNS);
        mDatabase.child(String.valueOf(
                BlocApp.getInstance().getCampaignPosition(campaign)))
                .setValue(campaign);
    }

    static void startDetailActivityWith(Activity activity, Campaign campaign, boolean isNewCampaign) {
        if (!isNewCampaign) {
            activity.onBackPressed();
        }
        activity.onBackPressed();
        Intent intent = new Intent(activity, CampaignDetailActivity.class);
        intent.putExtras(campaign.toBundle());
        activity.startActivity(intent);
    }

}
