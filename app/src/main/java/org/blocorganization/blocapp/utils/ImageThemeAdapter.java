package org.blocorganization.blocapp.utils;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import org.blocorganization.blocapp.R;

import java.util.List;

public class ImageThemeAdapter extends
        RecyclerView.Adapter<ImageThemeAdapter.ViewHolder> {

    private List<String> mThemes;
    private Activity mActivity;
    private int mLayoutParams;
    private String setTheme;

    // Pass in the themes List & getThemeImageUrl into the constructor
    public ImageThemeAdapter(Activity activity, List<String> themes, int layoutParams, String theme) {
        mThemes = themes;
        mActivity = activity;
        mLayoutParams = layoutParams;
        setTheme = theme;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.themes_listitem, parent, false));
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ImageThemeAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        String theme = mThemes.get(position);
        int layoutParams = GetDpMeasurement.getDPI(mActivity, mLayoutParams);

        ImageView ivCampaignTheme = viewHolder.ivCampaignTheme;
        ivCampaignTheme.setLayoutParams(new LinearLayout.LayoutParams(layoutParams, layoutParams));
        Picasso.with(mActivity).load(theme).into(ivCampaignTheme);

        // when loadCampaignData in CreateInfoDialog set selected theme to getThemeImageUrl
        if (setTheme != null && !setTheme.equals("")) {
            if (setTheme.equals(mThemes.get(position))) {
                // get Viewholder for row and change background
                LinearLayout setLayout = (LinearLayout) viewHolder.itemView;
                setLayout.setBackgroundResource(R.drawable.background_square_selected_shadow);
                ImageView setImg = (ImageView) setLayout.getChildAt(0);
                setImg.setColorFilter(Color.parseColor("#00000000"));
                setImg.setBackgroundColor(Color.parseColor("#00000000"));

                // TODO Add code to cause auto-scroll to position and selected-state
            }
        }

    }

    @Override
    public int getItemCount() {
        return mThemes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCampaignTheme;

        ViewHolder(View itemView) {
            super(itemView);
            ivCampaignTheme = (ImageView) itemView.findViewById(R.id.ivCampaignTheme);
        }
    }

}
