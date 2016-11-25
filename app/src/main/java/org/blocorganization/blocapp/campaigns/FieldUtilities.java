package org.blocorganization.blocapp.campaigns;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

class FieldUtils {

    static void loadUrlIntoViewWithActivity(String url, ImageView ivReference, Activity activity) {
        Picasso.with(activity).load(url).into(ivReference);
        ivReference.setVisibility(View.VISIBLE);
    }

    static void setImageViewResourceWithId(ImageView ivReference, int resourceId) {
        ivReference.setImageResource(resourceId);
        ivReference.setVisibility(View.VISIBLE);
    }

}
